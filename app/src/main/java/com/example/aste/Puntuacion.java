package com.example.aste;

public class Puntuacion {
    private int puntos;
    private String nombre;

    public Puntuacion(int puntos, String nombre) {
        this.puntos = puntos;
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
