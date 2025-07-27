package com.example.el_cairo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class AdminSQLiteOpen extends SQLiteOpenHelper{


    // Constructor
    public AdminSQLiteOpen(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "admin", null, 14);
    }

    /*metodo onCreate, crea la tabla en la basae de datos la primera vez que se abre el archivo*/
    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {


    }

    /*
    * BaseDeDatos.execSQL("PRAGMA foreign_keys=ON;");
        BaseDeDatos.execSQL("CREATE TABLE Usuario(IdUsuario INTEGER PRIMARY KEY, name TEXT, password TEXT)");
        //insertando usuario directamente:

        BaseDeDatos.execSQL("INSERT INTO Usuario(name,password) VALUES('Emanuel','1234')");

    * db.execSQL("CREATE TABLE clientes(" +
                "IdCliente INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombreCliente TEXT," +
                "ApellidoCliente TEXT," +
                "TelefonoCliente TEXT," +
                "DireccionCliente TEXT," +
                "IdUsuario INT," +
                "FOREIGN KEY (IdUsuario) references Usuario(IdUsuario))");
        Log.d("SQLite", "Tabla Cliente creada.");
    *
    * */

    // Método onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Aquí puedes agregar lógica para actualizar la base de datos si cambias la versión
        db.execSQL("CREATE TABLE RUTAS(" +
                "IdRutas INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre_ruta TEXT," +
                "descripcion_ruta TEXT)");
        onCreate(db);



    }
     //insesrt into clientes (nombre, telefono, ubicacion) values ('Gloxi',456789,'los robles')
}


