package com.example.telefutbol.Fragmentos;

import android.app.AlertDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.telefutbol.Entity.Posiciones;
import com.example.telefutbol.Entity.PosicionesDto;
import com.example.telefutbol.Entity.Resultados;
import com.example.telefutbol.Entity.ResultadosDto;
import com.example.telefutbol.PosicionesAdapter;
import com.example.telefutbol.ResultadosAdapter;
import com.example.telefutbol.Service.FutbolService;
import com.example.telefutbol.databinding.FragmentResultadosBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentResultados extends Fragment implements SensorEventListener {

    FragmentResultadosBinding binding;
    FutbolService futbolService;
    private ResultadosAdapter adapter;
    private List<Resultados> listaResultados;
    private List<List<Resultados>> historialResultados;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private boolean isAccelerometerActive = false;
    private static final float SHAKE_THRESHOLD = 20.0f; // Umbral de 20m/s
    private long lastUpdate = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultadosBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Inicializar la lista de resultados
        listaResultados = new ArrayList<>();
        historialResultados = new ArrayList<>();

        // Configurar el RecyclerView
        binding.recyclerViewListaResultados.setLayoutManager(new LinearLayoutManager(getActivity()));

        createRetrofitService();

        binding.buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = binding.buscar1.getText().toString();
                String temporada = binding.buscar2.getText().toString();
                String ronda = binding.buscar3.getText().toString();
                if(id.isEmpty() && temporada.isEmpty() && ronda.isEmpty()){
                    Toast.makeText(getActivity(), "Los campos estan vacíos", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!id.isEmpty()) {
                        if (!temporada.isEmpty()) {
                            fetchResultados(id, temporada, ronda);
                        } else {
                            Toast.makeText(getActivity(), "Campo temporada esta vacío", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "Campo idLiga esta vacío", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Inicializar el sensor de acelerómetro
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        return view;

    }

    public void createRetrofitService() {
        futbolService = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FutbolService.class);
    }

    private void fetchResultados(String idLiga, String temporada, String ronda) {
        futbolService.getResultados(idLiga,ronda,temporada).enqueue(new Callback<ResultadosDto>() {
            @Override
            public void onResponse(Call<ResultadosDto> call, Response<ResultadosDto> response) {
                if (response.isSuccessful()) {
                    List<Resultados> nuevosResultados = response.body().getListaResultados();
                    if (nuevosResultados != null && !nuevosResultados.isEmpty()) {
                        // Añadir los nuevos resultados al historial de listas
                        historialResultados.add(nuevosResultados);
                        // Añadir los nuevos resultados a la lista global
                        listaResultados.addAll(nuevosResultados);


                        // Si el adaptador aún no está inicializado
                        if (adapter == null) {
                            adapter = new ResultadosAdapter(listaResultados);
                            binding.recyclerViewListaResultados.setAdapter(adapter);
                        } else {
                            // Notificamos al adaptador que los datos han cambiado
                            adapter.notifyDataSetChanged();
                        }
                    }


                } else {
                    Toast.makeText(getActivity(), "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultadosDto> call, Throwable t) {
                Toast.makeText(getActivity(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Registrar el sensor cuando el Fragmento está visible
    @Override
    public void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            isAccelerometerActive = true;
        }
    }

    // Detener el sensor cuando el Fragmento no está visible
    @Override
    public void onPause() {
        super.onPause();
        if (isAccelerometerActive) {
            sensorManager.unregisterListener(this);
            isAccelerometerActive = false;
        }
    }

    // Implementación de SensorEventListener: sugerencia Copilot
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            if ((curTime - lastUpdate) > 100) {
                long diffTime = curTime - lastUpdate;
                lastUpdate = curTime;

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                // Magnitud de la aceleración
                float acceleration = (float) Math.sqrt(x * x + y * y + z * z);

                // Si la aceleración es mayor que el umbral, abrir diálogo
                if (acceleration > SHAKE_THRESHOLD) {
                    openDialog();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No se necesita en este caso
    }

    // Mostrar diálogo de confirmación
    private void openDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Confirmar acción de borrado")
                .setMessage("¿Deseas eliminar los últimos resultados?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    if (!listaResultados.isEmpty()) {
                        // Eliminar el último bloque de resultados del historial
                        List<Resultados> ultimosResultados = historialResultados.remove(historialResultados.size() - 1);
                        // Eliminar esos resultados de la lista global
                        listaResultados.removeAll(ultimosResultados);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Último resultado eliminado", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }
}