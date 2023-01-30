package com.example.aste;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AlmacenPuntuacionesGSon implements AlmacenPuntuaciones {
    private String string; //Almacena puntuaciones en formato JSON
    private Gson gson = new Gson();
    private Type type = new TypeToken<List<Puntuacion>>() {}.getType();
    public AlmacenPuntuacionesGSon() {
        guardarPuntuacion(45000,"Mi nombre");
        guardarPuntuacion(31000,"Otro nombre");
    }
    @Override
    public void guardarPuntuacion(int puntos, String nombre) {
        //string = leerString();
        ArrayList<Puntuacion> puntuaciones;
        if (string == null) {
            puntuaciones = new ArrayList<>();
        } else {
            puntuaciones = gson.fromJson(string, type);
        }
        puntuaciones.add(new Puntuacion(puntos, nombre));
        string = gson.toJson(puntuaciones, type);
        //guardarString(string);
    }
    @Override
    public List<String> listaPuntuaciones(int cantidad) {
        //string = leerString();
        ArrayList<Puntuacion> puntuaciones;
        if (string == null) {
            puntuaciones = new ArrayList<>();
        } else {
            puntuaciones = gson.fromJson(string, type);
        }
        List<String> salida = new ArrayList<>();
        for (Puntuacion puntuacion : puntuaciones) {
            salida.add(puntuacion.getPuntos()+" "+puntuacion.getNombre());
        }
        return salida;
    }
}
