package com.example.aste;

import java.util.ArrayList;
import java.util.List;

public class AlmacenPuntuacionesArray implements AlmacenPuntuaciones{
    private List<String> puntuaciones;

    public AlmacenPuntuacionesArray() {
        puntuaciones= new ArrayList<String>();
        puntuaciones.add("123000 Manuela Domingez");
        puntuaciones.add("111100 Pedro Martinez");
        puntuaciones.add("111000 Paco Pérez");
        puntuaciones.add("108000 María García");
        puntuaciones.add("107000 Paula Fernández");
        puntuaciones.add("101000 Paco González");
        puntuaciones.add("100700 Fernando Gutiérrez");
        puntuaciones.add("100550 Noelia Martinez");
        puntuaciones.add("100200 Beatriz Prieto");
        puntuaciones.add("99000 Manuel Fernández");
        puntuaciones.add("95000 Patricia Ramos");
        puntuaciones.add("75000 Nerea Gómez");
        puntuaciones.add("69000 Manuela Fernández");
        puntuaciones.add("65000 Román Ramos");
        puntuaciones.add("65000 Ramiro Gómez");
    }
    @Override public void guardarPuntuacion(int puntos, String nombre) {
        puntuaciones.add(0, puntos + " " + nombre);
    }
    @Override public List<String> listaPuntuaciones(int cantidad){
        return puntuaciones;
    }
}
