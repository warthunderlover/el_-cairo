package com.example.el_cairo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class menu_principal extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView nv_side;
    TextView tv_snv;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    MaterialButton show;
    BottomSheetDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_principal);

        drawerLayout = findViewById(R.id.main);
        nv_side = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv_snv = findViewById(R.id.txt_emanuel);
        /*

            el error que tenia era por que a la hrioa de llamar a show,
            no lo estaba inicializando.

            hacia un "show.findview" en vez de un "show = findview"
            por eso el error de "nullpointer".

        */
        show = findViewById(R.id.show_bottom_);

        dialog = new BottomSheetDialog(this);

        //drawer toggle
        toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        nv_side.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.nav_Logout) {
                    finishAffinity();
                }
                return true;
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Rutas(View view){
        Intent rutas = new Intent(this, rutas.class);
        startActivity(rutas);
    }

    public void show_values() {
        View view = getLayoutInflater().inflate(R.layout.bottom_dialog,null,false);
        dialog.setContentView(view);
    }

    public void Cliente(View view){
        Intent cliente = new Intent(this, agregar_pedido.class);
        startActivity(cliente);
    }

    public void Cliente_add(View view){
        Intent cliente_add = new Intent(this, ControlClientes.class);
        startActivity(cliente_add);
    }
}