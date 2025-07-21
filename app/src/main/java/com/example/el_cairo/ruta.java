package com.example.el_cairo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.view.ViewGroup;
import android.graphics.Color;
import android.view.View; // Necesario para View.GRAVITY_CENTER

import java.util.ArrayList;
import java.util.List;

public class ruta extends AppCompatActivity {

    private ViewFlipper routeSlider;
    private List<String> rutas = new ArrayList<>();
    private int rutaActual = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta);

        routeSlider = findViewById(R.id.routeSlider);

        ImageView plusIcon = findViewById(R.id.plusIcon);
        plusIcon.setOnClickListener(view -> showAddRouteDialog());

        Button btnVolver = findViewById(R.id.btnVolver);
        Button btnSiguiente = findViewById(R.id.btnSiguiente);

        btnVolver.setOnClickListener(v -> mostrarRuta(-1));
        btnSiguiente.setOnClickListener(v -> mostrarRuta(1));
    }

    private void showAddRouteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Por favor ingrese el nombre de la ruta:");

        final EditText input = new EditText(this);
        input.setHint("Nombre de la ruta");
        builder.setView(input);

        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            String routeName = input.getText().toString().trim();
            if (!routeName.isEmpty()) {
                addRoute(routeName);
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void addRoute(String routeName) {
        // Opcional: Evitar duplicados si no quieres que se añadan rutas con el mismo nombre
        if (rutas.contains(routeName)) {
            // Toast.makeText(this, "La ruta '" + routeName + "' ya existe.", Toast.LENGTH_SHORT).show();
            return;
        }

        rutas.add(routeName);

        TextView routeView = new TextView(this);
        routeView.setText("Ruta: " + routeName);
        routeView.setTextSize(18);
        routeView.setPadding(30, 30, 30, 30);
        routeView.setTextColor(Color.WHITE);
        routeView.setBackgroundColor(Color.parseColor("#607D8B"));


        // Los LayoutParams para las vistas hijas de un ViewFlipper deben ser ViewFlipper.LayoutParams
        ViewFlipper.LayoutParams params = new ViewFlipper.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT // Permitir que el TextView se ajuste a su contenido
        );
        params.setMargins(20, 20, 20, 0); // Margen para el TextView dentro del ViewFlipper
        routeView.setLayoutParams(params);

        routeSlider.addView(routeView);

        // Después de agregar una nueva ruta, asegúrate de que se muestre y actualiza el índice actual.
        rutaActual = rutas.size() - 1;
        routeSlider.setDisplayedChild(rutaActual);
    }

    private void mostrarRuta(int cambio) {
        if (rutas.isEmpty()) {
            // Opcional: puedes mostrar un mensaje si no hay rutas
            // Toast.makeText(this, "No hay rutas para mostrar.", Toast.LENGTH_SHORT).show();
            return;
        }

        int proximaRuta = rutaActual + cambio;

        // Lógica para que el "slider" sea cíclico (volver al inicio/fin)
        if (proximaRuta < 0) {
            proximaRuta = rutas.size() - 1; // Si va hacia atrás desde la primera, va a la última
        } else if (proximaRuta >= rutas.size()) {
            proximaRuta = 0; // Si va hacia adelante desde la última, va a la primera
        }

        // Solo cambia la vista si es una ruta diferente
        if (proximaRuta != rutaActual) {
            // Cargar animaciones personalizadas (necesitas crear los archivos XML en res/anim)
            if (cambio > 0) { // Siguiente ruta
                routeSlider.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_right));
                routeSlider.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_left));
            } else { // Ruta anterior
                routeSlider.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_in_left));
                routeSlider.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_out_right));
            }
            routeSlider.setDisplayedChild(proximaRuta);
            rutaActual = proximaRuta;
        }
    }
}