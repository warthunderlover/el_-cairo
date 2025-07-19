package com.example.el_cairo;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ruta extends AppCompatActivity {

    private LinearLayout routeContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ruta);

        Button btnAddRoute = findViewById(R.id.btnAddRoute);
        routeContainer = findViewById(R.id.routeContainer);

        btnAddRoute.setOnClickListener(v -> showAddRouteDialog());
    }

    private void showAddRouteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Por Favor Ingrese el Nombre de la Ruta:");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            String routeName = input.getText().toString();
            if (!routeName.isEmpty()) {
                addRouteCard(routeName);
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void addRouteCard(String routeName) {
        TextView card = new TextView(this);
        card.setText("Ruta: " + routeName);
        card.setTextSize(18);
        card.setPadding(30, 30, 30, 30);
        card.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        card.setTextColor(getResources().getColor(android.R.color.white));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 10, 0, 10);

        card.setLayoutParams(params);
        routeContainer.addView(card);
    }
}
