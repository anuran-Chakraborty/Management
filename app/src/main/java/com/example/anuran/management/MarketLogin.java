package com.example.anuran.management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MarketLogin extends AppCompatActivity {
    private EditText username,pass;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_login);
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, TestActivity.class));
            return;
        }
        username = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        buttonLogin = (Button) findViewById(R.id.login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == buttonLogin)
                    userLogin();
            }

            ;
        });
    }

    private void userLogin(){
            final String uname=username.getText().toString().trim();
            final String password=pass.getText().toString().trim();



            StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj=new JSONObject(response);


                        if(obj.getBoolean("error")){
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(obj.getInt("id"),obj.getString("uname"),obj.getString("pass"));

                            startActivity(new Intent(getApplicationContext(),TestActivity.class));
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"Invalid", Toast.LENGTH_LONG).show();
                        }}
                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params= new HashMap<>();
                    params.put("username",uname);
                    params.put("password",password);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }

        public void onClick(View view) {
            if(view==buttonLogin)
                userLogin();

        }


    }

