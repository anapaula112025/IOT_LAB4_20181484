package com.example.telefutbol.Fragmentos;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.telefutbol.Entity.Liga;
import com.example.telefutbol.Entity.LigaDto;
import com.example.telefutbol.Entity.LigaDto2;
import com.example.telefutbol.LigaAdapter;
import com.example.telefutbol.R;
import com.example.telefutbol.Service.FutbolService;
import com.example.telefutbol.databinding.FragmentLigasBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentLigas extends Fragment {

    FragmentLigasBinding binding;
    FutbolService futbolService;
    private LigaAdapter adapter;
    private List<Liga> listaLigas;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLigasBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        // Configurar el RecyclerView
        binding.recyclerViewListaLigas.setLayoutManager(new LinearLayoutManager(getActivity()));

        createRetrofitService();
        // Configurar el botón de búsqueda
        binding.buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = binding.buscar.getText().toString();
                if (country.isEmpty()) {
                    fetchAllLeagues();
                }else {
                    fetchLeaguesByCountry(country);
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

    private void fetchAllLeagues() {
        futbolService.getLigasGeneral().enqueue(new Callback<LigaDto>() {
            @Override
            public void onResponse(Call<LigaDto> call, Response<LigaDto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Liga> ligas = response.body().getListaLigas();
                    adapter = new LigaAdapter(ligas);
                    binding.recyclerViewListaLigas.setAdapter(adapter);

                } else {
                    Toast.makeText(getActivity(), "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LigaDto> call, Throwable t) {
                Toast.makeText(getActivity(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchLeaguesByCountry(String country) {
        futbolService.getLigasPorPais(country).enqueue(new Callback<LigaDto2>() {
            @Override
            public void onResponse(Call<LigaDto2> call, Response<LigaDto2> response) {
                if (response.isSuccessful()) {
                    List<Liga> ligas = response.body().getListaLigas();
                    if (ligas != null && !ligas.isEmpty()) {
                        adapter = new LigaAdapter(ligas);
                        binding.recyclerViewListaLigas.setAdapter(adapter);
                    } else {
                        // Manejar el caso donde no hay ligas encontradas
                        Toast.makeText(getActivity(), "No se encontraron ligas para el país especificado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LigaDto2> call, Throwable t) {
                Toast.makeText(getActivity(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

}