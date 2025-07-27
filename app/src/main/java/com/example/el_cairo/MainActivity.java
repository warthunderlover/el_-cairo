package com.example.el_cairo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txt_nombre, txt_contra;
    Button btn_acceder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init_ui();

        /*btn_acceder.setOnClickListener(view->{
            Login();
        });*/

    }

    public void init_ui(){
        txt_nombre = findViewById(R.id.txt_nombre);
        txt_contra = findViewById(R.id.txt_contra);
        btn_acceder = findViewById(R.id.btn_login);
    }

    public void Login(View view){
        AdminSQLiteOpen adminSQLiteOpen = new AdminSQLiteOpen(this, "Admin", null, 13);
        SQLiteDatabase db = adminSQLiteOpen.getReadableDatabase();

        String name = txt_nombre.getText().toString().trim();
        String pass = txt_contra.getText().toString().trim();

        try {
            Cursor row = db.rawQuery( "SELECT * FROM usuario WHERE name=? AND password=?",new String[]{name, pass});

            if(row.moveToFirst()){
                Toast.makeText(this,"Ingresando",Toast.LENGTH_LONG).show();
                Intent menu = new Intent(this, menu_principal.class);
                startActivity(menu);
            }else{
                Toast.makeText(this,"Usuario o Contraseña inmcorrectos",Toast.LENGTH_LONG).show();
            }
            row.close();
            db.close();

        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Failed Connection", Toast.LENGTH_SHORT).show();
        }


    }

    public void Recuperacion(View view){
        Intent main = new Intent(this, recuperacion_.class);
        startActivity(main);
    }


    @Override
    public void onClick(View v) {

       }

}
