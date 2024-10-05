package com.example.telefutbol.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Resultados implements Serializable {
    @SerializedName("strEvent")
    private String nombre;
    @SerializedName("intRound")
    private String ronda;
    @SerializedName("strHomeTeam")
    private String eLocal;
    @SerializedName("strAwayTeam")
    private String eVisitante;
    @SerializedName("intHomeScore")
    private String scoreLocal;
    @SerializedName("intAwayScore")
    private String scoreVisitante;
    @SerializedName("dateEvent")
    private String fecha;
    @SerializedName("strLeagueBadge")
    private String logo;
    @SerializedName("intSpectators")
    private String espectadores;
    @SerializedName("idLeague")
    private String idLiga;
    @SerializedName("strSeason")
    private String temporada;

    public Resultados(String nombre, String ronda, String eLocal, String eVisitante, String scoreLocal, String scoreVisitante, String fecha, String logo, String espectadores, String idLiga, String temporada) {
        this.nombre = nombre;
        this.ronda = ronda;
        this.eLocal = eLocal;
        this.eVisitante = eVisitante;
        this.scoreLocal = scoreLocal;
        this.scoreVisitante = scoreVisitante;
        this.fecha = fecha;
        this.logo = logo;
        this.espectadores = espectadores;
        this.idLiga = idLiga;
        this.temporada = temporada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRonda() {
        return ronda;
    }

    public void setRonda(String ronda) {
        this.ronda = ronda;
    }

    public String geteLocal() {
        return eLocal;
    }

    public void seteLocal(String eLocal) {
        this.eLocal = eLocal;
    }

    public String geteVisitante() {
        return eVisitante;
    }

    public void seteVisitante(String eVisitante) {
        this.eVisitante = eVisitante;
    }

    public String getScoreLocal() {
        return scoreLocal;
    }

    public void setScoreLocal(String scoreLocal) {
        this.scoreLocal = scoreLocal;
    }

    public String getScoreVisitante() {
        return scoreVisitante;
    }

    public void setScoreVisitante(String scoreVisitante) {
        this.scoreVisitante = scoreVisitante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEspectadores() {
        return espectadores;
    }

    public void setEspectadores(String espectadores) {
        this.espectadores = espectadores;
    }

    public String getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(String idLiga) {
        this.idLiga = idLiga;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }
}
