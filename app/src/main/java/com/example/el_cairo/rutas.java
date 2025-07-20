package com.example.el_cairo;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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

    MaterialButton show;
    BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rutas);

        show = findViewById(R.id.show_bottom);

        dialog = new BottomSheetDialog(this);

        recyclerRutas = findViewById(R.id.recyclerRutas);
        //ImageView bellIcon = findViewById(R.id.bell_icon);

        listaRutas = new ArrayList<>();
        listaRutas.add("Ruta A");
        listaRutas.add("Ruta B");
        listaRutas.add("Ruta C");

        rutaAdapter = new RutaAdapter(this, listaRutas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerRutas.setLayoutManager(layoutManager);
        recyclerRutas.setAdapter(rutaAdapter);

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


}

