package com.example.aste;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AlmacenPuntuacionesSQLiteRel extends SQLiteOpenHelper implements AlmacenPuntuaciones {

    public AlmacenPuntuacionesSQLiteRel(Context context) {
        super(context, "puntuaciones", null, 2);
    }

    //Métodos de SQLiteOpenHelper
    @Override public void onCreate (SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios ("+ "usu_id INTEGER PRIMARY KEY AUTOINCREMENT, "+ "nombre TEXT, correo TEXT)");
        db.execSQL("CREATE TABLE puntuaciones2 ("+ "punt_id INTEGER PRIMARY KEY AUTOINCREMENT, "+ "puntos INTEGER, usuario INTEGER,"+ "FOREIGN KEY (usuario) REFERENCES usuarios (usu_id))");
    }

    @Override public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion==1 && newVersion==2) {
            //Crea las nuevas tablas
            onCreate(db);
            //Recorre la tabla antigua
            Cursor cursor = db.rawQuery("SELECT puntos, nombre" + "FROM puntuaciones", null);
            while (cursor.moveToNext()) {
                //Crea los nuevos registros
                guardarPuntuacion(db, cursor.getInt(0), cursor.getString(1));
            }
            cursor.close();
            db.execSQL("DROP TABLE puntuaciones"); //Elimina la tabla antigua
        }
    }

    //Métodos de AlmacenPuntuaciones
    public List<String> listaPuntuaciones (int cantidad){
        List<String> result = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT puntos, nombre FROM " + "puntuaciones2, usuarios WHERE usuario = usu_id ORDER BY " + "puntos DESC LIMIT " + cantidad, null);
        while (cursor.moveToNext()) {
            result.add(cursor.getInt(0)+ " " + cursor.getString(1));
        }
        cursor.close();
        db.close();
        return result;
    }

    public void guardarPuntuacion (int puntos, String nombre)
    {
        SQLiteDatabase db = getWritableDatabase();
        guardarPuntuacion(db, puntos, nombre);
        db.close();
    }

    public void guardarPuntuacion (SQLiteDatabase db, int puntos, String nombre) {
        int usuario = buscaInserta (db, nombre);
        db.execSQL("PRAGMA foreign_keys = ON");
        db.execSQL("INSERT INTO puntuaciones2 VALUES (null, " + puntos + ", " + usuario + ")");
    }

    private int buscaInserta (SQLiteDatabase db, String nombre) {
        Cursor cursor = db.rawQuery("SELECT usu_id FROM usuarios " + "WHERE nombre='" + nombre +"'", null);
        if (cursor.moveToNext()) {
            int result = cursor.getInt( 0);
            cursor.close();
            return result;
        } else {
            cursor.close();
            db.execSQL("INSERT INTO usuarios VALUES (null, '" + nombre + "', 'correo@dominio.es')");
            return buscaInserta(db, nombre);
        }

    }
}

