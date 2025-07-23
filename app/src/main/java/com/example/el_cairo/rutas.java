package com.example.el_cairo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class rutas extends AppCompatActivity {

    RecyclerView recyclerRutas;
    RutaAdapter rutaAdapter;
    List<String> listaRutas;

    MaterialButton show, btn_add_rute;

    BottomSheetDialog dialog;
    Dialog dialog_card;

    //Button btn_agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutas);

        //Notificaciones
        show = findViewById(R.id.show_bottom);
        dialog = new BottomSheetDialog(this);
        recyclerRutas = findViewById(R.id.recyclerRutas);

        //carta de agregar nueva ruta
        dialog_card = new Dialog(rutas.this);
        dialog_card.setContentView(R.layout.agregar);
        dialog_card.setCancelable(true);

        //boton
        btn_add_rute = findViewById(R.id.btn_agregar);

        //aqui seguiria el btn agregar, para guardar los datos ingresados en la base de datos


        //rutas del slider
        listaRutas = new ArrayList<>();
        listaRutas.add("Ruta A");
        listaRutas.add("Ruta B");
        listaRutas.add("Ruta C");

        rutaAdapter = new RutaAdapter(this, listaRutas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerRutas.setLayoutManager(layoutManager);
        recyclerRutas.setAdapter(rutaAdapter);

        //card
        mostrarDialogoAgregarRuta();

        btn_add_rute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_card.show();
                mostrarDialogoAgregarRuta();
            }
        });

        //bottom
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
    public void Main(View view){
        Intent main = new Intent(this, menu_principal.class);
        startActivity(main);
    }

    private void mostrarDialogoAgregarRuta() {
        EditText editNombreRuta = dialog_card.findViewById(R.id.edit_nombre_ruta);
        Button btnGuardarRuta = dialog_card.findViewById(R.id.btn_guardar_ruta);

        btnGuardarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreRuta = editNombreRuta.getText().toString().trim();
                if (!nombreRuta.isEmpty()) {
                    // Aquí puedes guardar la ruta o hacer lo que necesites
                    Toast.makeText(rutas.this, "Ruta guardada: " + nombreRuta, Toast.LENGTH_SHORT).show();
                    listaRutas.add(nombreRuta);
                    rutaAdapter.notifyItemInserted(listaRutas.size() - 1);
                    dialog_card.dismiss();
                } else {
                    Toast.makeText(rutas.this, "Por favor, ingrese un nombre.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}

