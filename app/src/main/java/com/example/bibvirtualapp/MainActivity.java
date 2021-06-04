package com.example.bibvirtualapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtUserName;
    private EditText txtPassword;
    private EditText txtEmail;
    private EditText txtTelefono;

    private Button btnEnviar;

    RequestQueue requestQueue;
    private static final String url = "https://2.2.2.229/android/user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        initComponet();
        this.btnEnviar.setOnClickListener(this);
    }

    private void initComponet() {
        this.txtUserName = findViewById(R.id.txtUserName);
        this.txtPassword = findViewById(R.id.txtPassword);
        this.txtEmail = findViewById(R.id.txtEmail);
        this.txtTelefono = findViewById(R.id.txtTelefono);

        this.btnEnviar = findViewById(R.id.btnEnviar);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        System.out.println("el id es:  " + id);

        if(id == R.id.btnEnviar){
            String userName = this.txtUserName.getText().toString().trim();
            String password = this.txtPassword.getText().toString().trim();
            String email = this.txtEmail.getText().toString().trim();
            String telefono = this.txtTelefono .getText().toString().trim();

            crearUsuario(userName, password, email, telefono);
        }
    }

    private void crearUsuario(final String userName, final String password, final String email, final String telefono){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", userName);
                params.put("password", password);
                params.put("email", email);
                params.put("telefono", telefono);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}