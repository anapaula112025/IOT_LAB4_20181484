package com.example.telefutbol.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PosicionesDto {
    @SerializedName("table")
    private List<Posiciones> listaPosiciones;

    public List<Posiciones> getListaPosiciones() {
        return listaPosiciones;
    }

    public void setListaPosiciones(List<Posiciones> listaPosiciones) {
        this.listaPosiciones = listaPosiciones;
    }
}
