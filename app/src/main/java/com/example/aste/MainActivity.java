package com.example.aste;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Comentado para apartados tema 9 - Almacenamiento de datos
    // public static AlmacenPuntuaciones almacen= new AlmacenPuntuacionesArray();
    public static AlmacenPuntuaciones almacen;
    MediaPlayer mp;
    static final int ACTIV_JUEGO = 0;
    String cadena="Eventos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(cadena, "Estoy en el evento onCreate");

        //Tema 10 - Ejer utilizando el servicio Web PHP desde Asteroides
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().
                permitNetwork().build());

        //Para punto 9.3 - Preferencias
        //almacen = new AlmacenPuntuacionesPreferencias(this);
        //Para punto 9.4.1 - Almacenamiento fichero Interno -- FUNCIONA EN EMULADOR (muestra ruta almacenamiento en app)
        //almacen = new AlmacenPuntuacionesFicheroInterno(this);
        //Para punto 9.4.2 - Almacenamiento fichero Externo -- FUNCIONA, EN TERMINAL REAL (NO EN EMULADOR) (muestra ruta almacenamiento en app)
        //almacen = new AlmacenPuntuacionesFicheroExterno(this);
        //Para punto 9.4.2b - Almacenamiento fichero Externo en fichero específico
        //almacen = new AlmacenPuntuacionesFicheroExtApl(this);
        //Para punto 9.5 - Almacenamiento en fichero XML (SAX y DOM) -- FUNCIONA EN EMULADOR (almacena en /data/data/org.example.asteroides/files/puntuaciones.xml
        //almacen = new AlmacenPuntuacionesXML_SAX(this);
        //almacen = new AlmacenPuntuacionesXML_DOM(this);
        //Para punto 9.6 - Almacenamiento en JSON -- FUNCIONA BIEN EN EMULADOR
        //almacen = new AlmacenPuntuacionesGSon();
        //Para punto 9.6 - Almacenamiento en JSON
        //almacen = new AlmacenPuntuacionesJSon();
        //Para punto 9.7 - Base de datos SQLite --FUNCIONA BIEN EN EMULADOR
       //almacen = new AlmacenPuntuacionesSQLite(this);
        //Para punto 9.7 - Base de datos SQLite relacional --FUNCIONA BIEN EN EMULADOR
        //  almacen = new AlmacenPuntuacionesSQLiteRel(this);
        //Para punto 10.4.3 - Servidor Web con Apache y MySQL
        almacen = new AlmacenPuntuacionesSW_PHP();

        //Para probar ciclo de vida aplicación - Tema 6.1
       // Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

        TextView titulo = (TextView)findViewById(R.id.titulo);
        Animation animaTexto = AnimationUtils.loadAnimation(this, R.anim.giro_con_zoom);
        titulo.startAnimation(animaTexto);

        Button boton1 = (Button)findViewById(R.id.Button01);
        Animation anima1 = AnimationUtils.loadAnimation(this, R.anim.aparecer);
        boton1.startAnimation(anima1);

        Button boton2 = (Button)findViewById(R.id.Button02);
        Animation anima2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_derecha);
        boton2.startAnimation(anima2);

        Button boton3 = (Button)findViewById(R.id.Button03);
        Animation anima3 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_izq_giro);
        boton3.startAnimation(anima3);

        Button boton4 = (Button)findViewById(R.id.Button04);
        Animation anima4 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        boton4.startAnimation(anima4);

        mp=MediaPlayer.create(this,R.raw.audio);
        mp.start();


 }

    @Override protected void onSaveInstanceState(Bundle estadoGuardado){
        super.onSaveInstanceState(estadoGuardado);
        if (mp != null) {
            int pos = mp.getCurrentPosition();
            estadoGuardado.putInt("posicion", pos);
        }
    }

    @Override protected void onRestoreInstanceState(Bundle estadoGuardado){
        super.onRestoreInstanceState(estadoGuardado);
        if (estadoGuardado != null && mp != null) {
            int pos = estadoGuardado.getInt("posicion");
            mp.seekTo(pos);
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; /** true -> el menú ya está visible */
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            lanzarPreferencias(null);
            return true;
        }
        if (id == R.id.acercaDe) {
            lanzarAcercaDe(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void lanzarAcercaDe(View view){
        Button boton3 = (Button)findViewById(R.id.Button03);
        Animation anima3 = AnimationUtils.loadAnimation(this, R.anim.giro_con_zoom);
        boton3.startAnimation(anima3);

        Intent i = new Intent(this, AcercaDeActivity.class);
        startActivity(i);
    }

    public void lanzarPreferencias(View view){
        Intent i = new Intent(this, PreferenciasActivity.class);
        startActivity(i);
    }

    public void lanzarPuntuaciones(View view) {
        Intent i = new Intent(this, Puntuaciones.class);
        startActivity(i);
    }

    public void mostrarPreferencias(View view){
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String s = "música: "+ pref.getBoolean("musica",true)
                +", gráficos: " + pref.getString("graficos","?")
                +", fragmentos: " + pref.getString("fragmentos", "1")
                +", activar multijugador: " + pref.getBoolean("activar_multijudador", true)
                +", máximo número jugadores: " +pref.getString("max_jugadores", "1")
                +", conexión: " + pref.getString("tipo_conexion", "?");
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void lanzarJuego(View view) {

        Intent i = new Intent(this, Juego.class);
        //Antes del tema 9
        //startActivity(i);
        //A partir del tema 9
        startActivityForResult(i, ACTIV_JUEGO);
    }

    //Para probar ciclo de vida de la aplicación - Tema 6.1
   @Override protected void onStart() {
        super.onStart();
        Log.d(cadena, "Estoy en el evento onStart");
        //Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onResume() {
        super.onResume();
        Log.d(cadena, "Estoy en el evento onResume");
        //Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onPause() {
      //  Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
        Log.d(cadena, "Estoy en el evento onPause");
        mp.pause();
    }

    @Override protected void onStop() {
     //  Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
        Log.d(cadena, "Estoy en el evento onStop");
        mp.pause();
    }

    @Override protected void onRestart() {
        super.onRestart();
        Log.d(cadena, "Estoy en el evento onRestart");
       // Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
        mp.start();
    }

    @Override protected void onDestroy() {
        //  Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        Log.d(cadena, "Estoy en el evento onDestroy");
    }


    @Override protected void onActivityResult (int requestCode,
                                               int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ACTIV_JUEGO && resultCode==RESULT_OK && data!=null) {
            int puntuacion = data.getExtras().getInt("puntuacion");
            String nombre = "Yo";
            // Mejor leer nombre desde un AlertDialog.Builder o preferencias
            almacen.guardarPuntuacion(puntuacion, nombre);
            lanzarPuntuaciones(null);
        }
    }

}
