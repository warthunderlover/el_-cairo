package com.example.el_cairo;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class agregar_pedido extends AppCompatActivity {

    RecyclerView recyclerClientes;
    ClienteAdapter clienteAdapter;
    List<String> listaPedido;

    MaterialButton show, btn_add_order;
    BottomSheetDialog dialog;
    Dialog dialog_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_pedido);

        //Notificaciones
        show = findViewById(R.id.show_bottom);
        dialog = new BottomSheetDialog(this);
        recyclerClientes = findViewById(R.id.recyclerClientes);

        //carta de agregar nuevo pedido
        dialog_card = new Dialog(agregar_pedido.this);
        dialog_card.setContentView(R.layout.agregar);
        dialog_card.setCancelable(true);

        //boton
        btn_add_order = findViewById(R.id.btn_agregar);

        //slider

        listaPedido = new ArrayList<>();
        clienteAdapter = new ClienteAdapter(this, listaPedido);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerClientes.setLayoutManager(layoutManager);
        recyclerClientes.setAdapter(clienteAdapter);

        cargarPedidosDesdeBD();

        mostrarDialogoAgregarPedido();

        btn_add_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_card.show();
                mostrarDialogoAgregarPedido();
            }
        });

        show_values();

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }

    private void mostrarDialogoAgregarPedido() {
        EditText descripcion = dialog_card.findViewById(R.id.edit_nombre_ruta);
        Button btnGuardarpedido = dialog_card.findViewById(R.id.btn_guardar_ruta);

        btnGuardarpedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descripcionPedido = descripcion.getText().toString().trim();
                if (!descripcionPedido.isEmpty()) {
                    //ingresandolos datos a la base de datos:

                    AdminSQLiteOpen adminSQLiteOpen = new AdminSQLiteOpen(agregar_pedido.this,"Admin",null,9);
                    SQLiteDatabase db = adminSQLiteOpen.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put("DescripcionPedido",descripcionPedido);

                    long result = db.insert("pedidos",null,values);
                    db.close();


                    cargarPedidosDesdeBD();

                    if(result!=-1){
                        // Guardando la ruta en el slider
                        Toast.makeText(agregar_pedido.this, "pedido guardado: " + descripcionPedido, Toast.LENGTH_SHORT).show();
                        listaPedido.add(descripcionPedido);
                        dialog_card.dismiss();

                    }else{
                        Toast.makeText(agregar_pedido.this,"Error al guardar pedido",Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(agregar_pedido.this, "Por favor, ingrese un pedido.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void cargarPedidosDesdeBD() {
        listaPedido.clear();  // Limpiamos antes de cargar nuevas
        AdminSQLiteOpen adminSQLiteOpen = new AdminSQLiteOpen(agregar_pedido.this,"Admin",null,9);
        SQLiteDatabase db = adminSQLiteOpen.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT DescripcionPedido FROM pedidos", null);
        if (cursor.moveToFirst()) {
            do {
                String nombrepedido = cursor.getString(0);
                listaPedido.add(nombrepedido);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        clienteAdapter.notifyDataSetChanged();  // Notifica al adapter
    }

    public void show_values() {
        View view = getLayoutInflater().inflate(R.layout.bottom_dialog,null,false);
        dialog.setContentView(view);
    }

    public void Main(View view){
        Intent main = new Intent(this, menu_principal.class);
        startActivity(main);
    }

}