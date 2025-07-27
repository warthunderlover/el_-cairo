package com.example.el_cairo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

public class add_clients extends AppCompatActivity {
    EditText edNombre, edApellido, edTelefono, edDescripcion;

    Button siguiente;
    MaterialButton show;
    BottomSheetDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_clients);

        siguiente = findViewById(R.id.btnSiguiente);

        show = findViewById(R.id.show_bottom2);
        dialog = new BottomSheetDialog(this);

        intiui();

        show_values();

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar_cliente();
                Intent ruta = new Intent(add_clients.this, rutas.class);
                startActivity(ruta);
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }

    public void intiui(){
        edNombre = findViewById(R.id.txt_nombre_cliente);
        edApellido = findViewById(R.id.txt_apellido_cliente);
        edTelefono = findViewById(R.id.txt_telefono_cliente);
        edDescripcion = findViewById(R.id.txt_direccion_cliente);
    }

    public void show_values() {
        View view = getLayoutInflater().inflate(R.layout.bottom_dialog,null,false);
        dialog.setContentView(view);
    }

    public void Clientes(View view){
        Intent cliente = new Intent(this, ControlClientes.class);
        startActivity(cliente);
    }

    public void guardar_cliente() {
        //guardando los datos del cliente
        String nombre = edNombre.getText().toString().trim();
        String apellido = edApellido.getText().toString().trim();
        String telefono = edTelefono.getText().toString().trim();
        String direccion = edDescripcion.getText().toString().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            Toast.makeText(add_clients.this, "Error, datos no ingresados", Toast.LENGTH_LONG).show();

        } else {
            try {
                AdminSQLiteOpen adminSQLiteOpen = new AdminSQLiteOpen(add_clients.this, "Admin", null, 14);
                SQLiteDatabase db = adminSQLiteOpen.getWritableDatabase();

                /*ContentValues values = new ContentValues();
                values.put("nombrecliente", nombre);
                values.put("apellidocliente", apellido);
                values.put("telefonocliente", telefono);
                values.put("direccioncliente", direccion);
*/

                ContentValues valores = new ContentValues();
                valores.put("nombreCliente",nombre);
                valores.put("ApellidoCliente",apellido);
                valores.put("TelefonoCliente",telefono);
                valores.put("DireccionCliente",direccion);

                long result = db.insert("clientes", null, valores);
                db.close();

                if (result != -1) {
                    Toast.makeText(add_clients.this, "cliente guardado: " + nombre, Toast.LENGTH_SHORT).show();
                    Log.d("guardando datos","cliente guardado: "+nombre);
                } else {
                    Toast.makeText(add_clients.this, "Error al guardar cliente...", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                Toast.makeText(add_clients.this, "Error al gaurdar cliente", Toast.LENGTH_LONG).show();
            }
        }
    }
    }

