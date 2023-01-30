package com.example.aste;

import java.util.List;

public interface AlmacenPuntuaciones {
    public void guardarPuntuacion(int puntos, String nombre);
    public List<String> listaPuntuaciones(int cantidad);
}
