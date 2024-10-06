package com.example.telefutbol.Fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.telefutbol.Entity.Liga;
import com.example.telefutbol.Entity.LigaDto;
import com.example.telefutbol.Entity.Posiciones;
import com.example.telefutbol.Entity.PosicionesDto;
import com.example.telefutbol.LigaAdapter;
import com.example.telefutbol.PosicionesAdapter;
import com.example.telefutbol.Service.FutbolService;
import com.example.telefutbol.databinding.FragmentPosicionesBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentPosiciones extends Fragment {

    FragmentPosicionesBinding binding;
    FutbolService futbolService;
    private PosicionesAdapter adapter;
    private List<Posiciones> listaPosiciones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPosicionesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Configurar el RecyclerView
        binding.recyclerViewListaPosiciones.setLayoutManager(new LinearLayoutManager(getActivity()));

        createRetrofitService();
        // Configurar el botón de búsqueda
        binding.buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = binding.buscar1.getText().toString();
                String temporada = binding.buscar2.getText().toString();
                if(id.isEmpty() && temporada.isEmpty()){
                    Toast.makeText(getActivity(), "Los campos estan vacíos", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!id.isEmpty()) {
                        if (!temporada.isEmpty()) {
                            fetchPositions(id, temporada);
                        } else {
                            Toast.makeText(getActivity(), "Campo temporada esta vacío", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getActivity(), "Campo idLiga esta vacío", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    public void createRetrofitService() {
        futbolService = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FutbolService.class);
    }

    private void fetchPositions(String idLiga, String temporada) {
        Log.d("RetrofitCall", "Iniciando llamada a la API");
        futbolService.getPosiciones(idLiga,temporada).enqueue(new Callback<PosicionesDto>() {
            @Override
            public void onResponse(Call<PosicionesDto> call, Response<PosicionesDto> response) {

                if (response.isSuccessful()) {
                    List<Posiciones> posiciones = response.body().getListaPosiciones();
                    if (posiciones != null && !posiciones.isEmpty()) {
                        adapter = new PosicionesAdapter(posiciones);
                        binding.recyclerViewListaPosiciones.setAdapter(adapter);
                    } else {

                        Toast.makeText(getActivity(), "No se encontraron posiciones para el idLiga o temporada especificados", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    if (response.code() == 404) {
                        Toast.makeText(getActivity(), "No se encontró información para el idLiga o la temporada especificados", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Error en la respuesta", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<PosicionesDto> call, Throwable t) {
                Toast.makeText(getActivity(), "No se encontraron posiciones para el idLiga o temporada especificados", Toast.LENGTH_SHORT).show();
            }
        });
    }


}