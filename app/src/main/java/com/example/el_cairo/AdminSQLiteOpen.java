package com.example.el_cairo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class AdminSQLiteOpen extends SQLiteOpenHelper{


    // Constructor
    public AdminSQLiteOpen(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /*metodo onCreate, crea la tabla en la basae de datos la primera vez que se abre el archivo*/
    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("PRAGMA foreign_keys=ON;");
        BaseDeDatos.execSQL("CREATE TABLE Usuario(IdUsuario INTEGER PRIMARY KEY, name TEXT, password TEXT)");
        //insertando usuario directamente:

        BaseDeDatos.execSQL("INSERT INTO Usuario(name,password) VALUES('Emanuel','1234')");


    }

    // Método onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes agregar lógica para actualizar la base de datos si cambias la versión
        db.execSQL("INSERT INTO Usuario(name,password) VALUES('Estrada','1234')");
        db.execSQL("INSERT INTO Usuario(name,password) VALUES('Elias','1234')");
        db.execSQL("INSERT INTO Usuario(name,password) VALUES('Veronica','1234')");
        db.execSQL("INSERT INTO Usuario(name,password) VALUES('Vanesa','1234')");
    }
     //insesrt into clientes (nombre, telefono, ubicacion) values ('Gloxi',456789,'los robles')
}


