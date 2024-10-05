package com.example.telefutbol.Service;


import com.example.telefutbol.Entity.LigaDto;
import com.example.telefutbol.Entity.LigaDto2;
import com.example.telefutbol.Entity.PosicionesDto;
import com.example.telefutbol.Entity.ResultadosDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FutbolService {
    @GET("api/v1/json/3/all_leagues.php")
    Call<LigaDto> getLigasGeneral();

    @GET("api/v1/json/3/search_all_leagues.php")
    Call<LigaDto2> getLigasPorPais(@Query("c") String country);

    @GET("api/v1/json/3/lookuptable.php")
    Call<PosicionesDto> getPosiciones(@Query("l") String idLiga, @Query("s") String temporada);

    @GET("api/v1/json/3/eventsround.php")
    Call<ResultadosDto> getResultados(@Query("id") String idLiga, @Query("r") String ronda, @Query("s") String temporada);

}
