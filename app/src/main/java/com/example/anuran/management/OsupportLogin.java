package com.example.anuran.management;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OsupportLogin extends AppCompatActivity {
    private EditText username,pass;
    private Button buttonLogin;
    SharedPreferences sharedpreferences2;
    public static final String mypreference2 = "mysharedpref16";
    public static String idofsupport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_osupport_login);
        if (SharedPrefManagerOos.getInstance(this).isLoggedIn()) {
            finish();
            Intent intent = new Intent(OsupportLogin.this, OrdinarySupport.class);
            sharedpreferences2 = getSharedPreferences(mypreference2,
                    Context.MODE_PRIVATE);
            idofsupport=sharedpreferences2.getString("supportid", "");
            String y=sharedpreferences2.getString("old","");
            // Toast.makeText(getApplicationContext(),SharedPrefManagerClient.KEY_USER_USERNAME_CLIENT, Toast.LENGTH_LONG).show();

            //intent.putExtra("id",x);
            //intent.putExtra("old",y);

            startActivity(intent);
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

        Map<String,String> params= new HashMap<>();
        params.put("username",uname);
        params.put("password",password);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_LOGIN_SUPPORTLOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject obj=new JSONObject(response);
                    //Toast.makeText(getApplicationContext(),obj.getString("uname"), Toast.LENGTH_LONG).show();

                    if(!obj.getBoolean("error")){
                        sharedpreferences2 = getSharedPreferences(mypreference2,
                                Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences2.edit();
                        editor.putString("supportid",uname);
                        editor.putString("old",password);
                        editor.commit();
                        idofsupport=obj.getString("username");
                        SharedPrefManagerOos.getInstance(OsupportLogin.this).userLogin(obj.getString("username"));
                        Toast.makeText(getApplicationContext(),"Success"+idofsupport, Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),OrdinarySupport.class));
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Invalid", Toast.LENGTH_LONG).show();
                    }}
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Exception", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Server error",Toast.LENGTH_LONG).show();
                error.printStackTrace();
                Log.e("VOLLEY", error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("username",uname);
                params.put("password",pass.getText().toString().trim());
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

