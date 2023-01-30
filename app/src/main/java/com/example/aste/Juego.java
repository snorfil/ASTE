package com.example.aste;

import android.app.Activity;
import android.os.Bundle;

public class Juego extends Activity {

    private VistaJuego vistaJuego;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);

        vistaJuego=(VistaJuego)findViewById(R.id.VistaJuego);
        vistaJuego.setPadre(this);
    }

    @Override protected void onPause(){
        super.onPause();
        vistaJuego.getThread().pausar();
    }

    @Override protected void onResume(){
        super.onResume();
        vistaJuego.getThread().reanudar();
    }

    @Override protected void onDestroy() {
        vistaJuego.getThread().detener();
        super.onDestroy();
    }

}
