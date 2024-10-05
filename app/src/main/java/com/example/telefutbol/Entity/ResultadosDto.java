package com.example.telefutbol.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultadosDto {
    @SerializedName("events")
    private List<Resultados> listaResultados;

    public List<Resultados> getListaResultados() {
        return listaResultados;
    }

    public void setListaResultados(List<Resultados> listaResultados) {
        this.listaResultados = listaResultados;
    }
}
