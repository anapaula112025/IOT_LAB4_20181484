package com.example.telefutbol.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Liga implements Serializable {
    @SerializedName("idLeague")
    private String id;
    @SerializedName("strLeague")
    private String nombreLiga;
    @SerializedName("strSport")
    private String nombreDeporte;
    @SerializedName("strLeagueAlternate")
    private String nombreLigaAlt;

    // Constructor
    public Liga(String id, String nombreLiga, String nombreDeporte, String nombreLigaAlt) {
        this.id = id;
        this.nombreLiga = nombreLiga;
        this.nombreDeporte = nombreDeporte;
        this.nombreLigaAlt = nombreLigaAlt;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreLiga() {
        return nombreLiga;
    }

    public void setNombreLiga(String nombreLiga) {
        this.nombreLiga = nombreLiga;
    }

    public String getNombreDeporte() {
        return nombreDeporte;
    }

    public void setNombreDeporte(String nombreDeporte) {
        this.nombreDeporte = nombreDeporte;
    }

    public String getNombreLigaAlt() {
        return nombreLigaAlt;
    }

    public void setNombreLigaAlt(String nombreLigaAlt) {
        this.nombreLigaAlt = nombreLigaAlt;
    }

    @Override
    public String toString() {
        return "Liga{" +
                "idLeague='" + id + '\'' +
                ", name='" + nombreLiga + '\'' +
                ", sport='" + nombreDeporte + '\'' +
                ", alternateName='" + nombreLigaAlt + '\'' +
                '}';
    }

}
