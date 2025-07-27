package com.example.el_cairo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

public class add_clients extends AppCompatActivity {

    MaterialButton show;
    BottomSheetDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_clients);

        show = findViewById(R.id.show_bottom2);
        dialog = new BottomSheetDialog(this);

        show_values();
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }

    public void show_values() {
        View view = getLayoutInflater().inflate(R.layout.bottom_dialog,null,false);
        dialog.setContentView(view);
    }

    public void Clientes(View view){
        Intent cliente = new Intent(this, ControlClientes.class);
        startActivity(cliente);
    }

    public void Rutas_Add(View view){
        Intent ruta = new Intent(this, rutas.class);
        startActivity(ruta);
    }

}