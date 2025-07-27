package com.example.el_cairo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ControlClientes extends AppCompatActivity {

    SearchView buscar;
    BusquedaAdapter busquedaAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.clientes);

        buscar = findViewById(R.id.search_bar);
        recyclerView = findViewById(R.id.recyclerBuscar); // Asegurate que este ID sea correcto
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

// Inicializar el adapter con una lista vacía
        busquedaAdapter = new BusquedaAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(busquedaAdapter);

        buscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                buscarClientes(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                buscarClientes(newText);

                return false;
            }
        });
    }

    //buscando clientes

    private void buscarClientes(String texto) {
        List<String> resultados = new ArrayList<>();

//      hacioendo la bsuquda
        AdminSQLiteOpen adminSQLiteOpen = new AdminSQLiteOpen(ControlClientes.this, "Admin", null, 14);
        SQLiteDatabase db = adminSQLiteOpen.getReadableDatabase();


        Cursor cursor = db.rawQuery(
                "SELECT nombreCliente FROM clientes WHERE nombreCliente LIKE ? OR ApellidoCliente LIKE ?",
                new String[]{"%" + texto + "%", "%" + texto + "%"}
        );

        //viendo lso resultados de la busqueda

        if (cursor.moveToFirst()) {
            do {
                resultados.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.d("SEARCHVIEW", "Resultados encontrados: " + resultados.size());
        busquedaAdapter.actualizarLista(resultados);
    }

    public void Cliente_crear(View view){
        Intent cliente_create = new Intent(this, add_clients.class);
        startActivity(cliente_create);
    }

}
