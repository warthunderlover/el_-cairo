package com.example.el_cairo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;
import java.util.List;

public class ruta extends AppCompatActivity {

    private LinearLayout routeContainer;
    private List<String> rutas = new ArrayList<>();
    private int rutaActual = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruta);

        ImageView plusIcon = findViewById(R.id.plusIcon);
        routeContainer = new LinearLayout(this);
        routeContainer.setOrientation(LinearLayout.VERTICAL);

        // Agregamos el contenedor de rutas debajo del botón
        LinearLayout mainLayout = findViewById(R.id.addRouteBox).getRootView().findViewById(android.R.id.content);
        mainLayout = (LinearLayout) mainLayout.getChildAt(0); // Accede al layout raíz
        mainLayout.addView(routeContainer); // Lo insertamos al final

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
        rutas.add(routeName);
        rutaActual = rutas.size() - 1;
        actualizarRutaMostrada();
    }

    private void mostrarRuta(int cambio) {
        rutaActual += cambio;

        if (rutaActual < 0) rutaActual = 0;
        if (rutaActual >= rutas.size()) rutaActual = rutas.size() - 1;

        actualizarRutaMostrada();
    }

    private void actualizarRutaMostrada() {
        routeContainer.removeAllViews();

        if (rutaActual >= 0 && rutaActual < rutas.size()) {
            String nombreRuta = rutas.get(rutaActual);

            TextView routeView = new TextView(this);
            routeView.setText("Ruta: " + nombreRuta);
            routeView.setTextSize(18);
            routeView.setPadding(30, 30, 30, 30);
            routeView.setTextColor(Color.WHITE);
            routeView.setBackgroundColor(Color.parseColor("#607D8B"));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(20, 20, 20, 0);
            routeView.setLayoutParams(params);

            routeContainer.addView(routeView);
        }
    }

    private void addRouteView(String routeName) {
        TextView routeView = new TextView(this);
        routeView.setText("Ruta: " + routeName);
        routeView.setTextSize(18);
        routeView.setPadding(30, 30, 30, 30);
        routeView.setTextColor(Color.WHITE);
        routeView.setBackgroundColor(Color.parseColor("#607D8B")); // gris azulado
        

        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20, 20, 20, 0);
        routeView.setLayoutParams(params);

        routeContainer.addView(routeView);

        
    }
}
