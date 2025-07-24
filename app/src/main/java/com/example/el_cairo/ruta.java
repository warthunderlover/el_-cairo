package com.example.el_cairo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ruta extends AppCompatActivity {

    private LinearLayout sliderLayout;
    private List<String> rutas = new ArrayList<>();
    private int rutaActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta);

        // Referencia al contenedor de rutas (dentro del HorizontalScrollView)
        sliderLayout = findViewById(R.id.sliderLayout);

        // Botón "+" para agregar nueva ruta
        findViewById(R.id.plusIcon).setOnClickListener(v -> {
            mostrarDialogoAgregarRuta();
        });

        // (Opcional) Si tienes botones para avanzar y retroceder rutas:
        // findViewById(R.id.btnSiguiente).setOnClickListener(v -> mostrarRutaSiguiente());
        // findViewById(R.id.btnVolver).setOnClickListener(v -> mostrarRutaAnterior());
    }

    private void mostrarDialogoAgregarRuta() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ingrese el nombre de la ruta");

        final EditText input = new EditText(this);
        input.setHint("Ejemplo: Ruta A");
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String nombreRuta = input.getText().toString().trim();
            if (!nombreRuta.isEmpty()) {
                rutas.add(nombreRuta);
                agregarTarjetaRuta(nombreRuta);
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void agregarTarjetaRuta(String nombreRuta) {
        TextView tarjetaRuta = new TextView(this);
        tarjetaRuta.setText("Ruta: " + nombreRuta);
        tarjetaRuta.setTextColor(Color.WHITE);
        tarjetaRuta.setTextSize(16);
        tarjetaRuta.setGravity(Gravity.CENTER);
        tarjetaRuta.setPadding(40, 30, 40, 30);
        tarjetaRuta.setBackgroundResource(R.drawable.card_background);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(20, 20, 20, 20);
        tarjetaRuta.setLayoutParams(layoutParams);

        sliderLayout.addView(tarjetaRuta);
    }

    // Métodos opcionales si deseas usar botones para navegar entre tarjetas:

    private void mostrarRutaAnterior() {
        if (rutaActual > 0) {
            rutaActual--;
            scrollToRuta(rutaActual);
        }
    }

    private void mostrarRutaSiguiente() {
        if (rutaActual < rutas.size() - 1) {
            rutaActual++;
            scrollToRuta(rutaActual);
        }
    }

    private void scrollToRuta(int index) {
        View rutaView = sliderLayout.getChildAt(index);
        if (rutaView != null) {
            rutaView.getParent().requestChildFocus(rutaView, rutaView);
        }
    }
}
