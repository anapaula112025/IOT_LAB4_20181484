package com.example.telefutbol.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Posiciones implements Serializable {
    @SerializedName("idLeague")
    private String idLiga;
    @SerializedName("strSeason")
    private String temporada;
    @SerializedName("strTeam")
    private String nombre;
    @SerializedName("intRank")
    private String ranking;
    @SerializedName("strBadge")
    private String badge;
    @SerializedName("intWin")
    private String ganados;
    @SerializedName("intLoss")
    private String perdidos;
    @SerializedName("intDraw")
    private String empatados;
    @SerializedName("intGoalsFor")
    private String anotados;
    @SerializedName("intGoalsAgainst")
    private String concedidos;
    @SerializedName("intGoalDifference")
    private String diferencias;

    // Constructor


    public Posiciones(String idLiga, String temporada, String nombre, String ranking, String badge, String ganados, String perdidos, String empatados, String anotados, String concedidos, String diferencias) {
        this.idLiga = idLiga;
        this.temporada = temporada;
        this.nombre = nombre;
        this.ranking = ranking;
        this.badge = badge;
        this.ganados = ganados;
        this.perdidos = perdidos;
        this.empatados = empatados;
        this.anotados = anotados;
        this.concedidos = concedidos;
        this.diferencias = diferencias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getGanados() {
        return ganados;
    }

    public void setGanados(String ganados) {
        this.ganados = ganados;
    }

    public String getPerdidos() {
        return perdidos;
    }

    public void setPerdidos(String perdidos) {
        this.perdidos = perdidos;
    }

    public String getEmpatados() {
        return empatados;
    }

    public void setEmpatados(String empatados) {
        this.empatados = empatados;
    }

    public String getAnotados() {
        return anotados;
    }

    public void setAnotados(String anotados) {
        this.anotados = anotados;
    }

    public String getConcedidos() {
        return concedidos;
    }

    public void setConcedidos(String concedidos) {
        this.concedidos = concedidos;
    }

    public String getDiferencias() {
        return diferencias;
    }

    public void setDiferencias(String diferencias) {
        this.diferencias = diferencias;
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
