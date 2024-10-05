package com.example.telefutbol.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LigaDto {
    @SerializedName("leagues")
    private List<Liga> listaLigas;

    public List<Liga> getListaLigas() {
        return listaLigas;
    }

    public void setListaLigas(List<Liga> listaLigas) {
        this.listaLigas = listaLigas;
    }

}
