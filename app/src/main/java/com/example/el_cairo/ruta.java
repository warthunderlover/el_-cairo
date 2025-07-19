package com.example.el_cairo;

import static com.example.el_cairo.R.id.plusIcon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.LinearLayout.LayoutParams;

import com.example.el_cairo.R;

public class ruta extends AppCompatActivity {

    private LinearLayout routeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView plusIcon = findViewById(R.id.plusIcon);
        routeContainer = new LinearLayout(this);
        routeContainer.setOrientation(LinearLayout.VERTICAL);

        // Agregamos el contenedor de rutas debajo del botón
        LinearLayout mainLayout = findViewById(R.id.addRouteBox).getRootView().findViewById(android.R.id.content);
        mainLayout = (LinearLayout) mainLayout.getChildAt(0); // Accede al layout raíz
        mainLayout.addView(routeContainer); // Lo insertamos al final

        plusIcon.setOnClickListener(view -> showAddRouteDialog());
    }

    private void showAddRouteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Por favor ingrese el nombre de la ruta:");

        // Campo de texto
        final EditText input = new EditText(this);
        input.setHint("Nombre de la ruta");
        builder.setView(input);

        // Botón Aceptar
        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            String routeName = input.getText().toString().trim();
            if (!routeName.isEmpty()) {
                addRouteView(routeName);
            }
        });

        // Botón Cancelar
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
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
