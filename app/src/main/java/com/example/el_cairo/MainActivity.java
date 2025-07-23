package com.example.el_cairo;

import android.content.Intent;
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
    EditText txt_nombre, txt_contra;
    Button btn_acceder;
    String alias_user, password_user;
    RequestQueue requestQueue;
//    private static final ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init_ui();

       /*btn_acceder.setOnClickListener(view->{
            String nombre = txt_nombre.getText().toString().trim();
            String contraseña = txt_contra.getText().toString().trim();

           /* if (nombre.isEmpty() || contraseña.isEmpty()) {

                Toast.makeText(this, "Por favor completa los campos", Toast.LENGTH_SHORT).show();
                return;

            }
            // llamado al metodo de login
            //loginUsuario(nombre, contraseña);


        });*/

    }

    public void init_ui(){
        txt_nombre = findViewById(R.id.txt_nombre);
        txt_contra = findViewById(R.id.txt_contra);
        btn_acceder = findViewById(R.id.btn_login);
    }


    public void Recuperacion(View view){
        Intent main = new Intent(this, recuperacion_.class);
        startActivity(main);
    }
    public void Menu(View view){
        Intent menu = new Intent(this, menu_principal.class);
        startActivity(menu);
    }

  /*  private void loginUsuario(String alias, String contraseña) {
        String url = "http://192.168.0.16/elcairo/login.php"; // cambia esta URL

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");
                        String message = jsonObject.getString("message");

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        if (success) {
                            Intent menu = new Intent(this, menu_principal.class);
                            startActivity(menu);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(getApplicationContext(), "Error de red", Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("alias_usuario", alias);
                params.put("contraseña_usuario", contraseña);
                return params;
            }
        };

        // Agregamos la petición a la cola de Volley
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
*/

    @Override
    public void onClick(View v) {

        //int id = v.getId();

        //if(id == R.id.btnAcceder){

        //}
    }

}
