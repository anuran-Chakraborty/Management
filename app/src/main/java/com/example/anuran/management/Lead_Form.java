package com.example.anuran.management;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Lead_Form extends AppCompatActivity implements View.OnClickListener{
     EditText editTextname, district, editTextEname, mobi1, mobi2, mobi3, land, city;
     Button buttonRegister,buttonConfirm;
     ProgressDialog progressDialog;
    //private TextView TextButtonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead__form);





        editTextname = (EditText) findViewById(R.id.editText1);
        district = (EditText) findViewById(R.id.editText9);
        editTextEname = (EditText) findViewById(R.id.editText10);
        mobi1 = (EditText) findViewById(R.id.editText4);
        mobi2 = (EditText) findViewById(R.id.editText5);
        mobi3 = (EditText) findViewById(R.id.editText6);
        land = (EditText) findViewById(R.id.editText7);
        city = (EditText) findViewById(R.id.editText3);
        buttonRegister = (Button) findViewById(R.id.btn4);
        buttonConfirm = (Button) findViewById(R.id.btn3);
        progressDialog = new ProgressDialog(this);


        buttonRegister.setOnClickListener(this);
        buttonConfirm.setOnClickListener(this);

    }

    private void registerUser() {
        final String name = editTextname.getText().toString().trim();
        final String dist = district.getText().toString().trim();
        final String ename = editTextEname.getText().toString().trim();
        final String mo1 = mobi1.getText().toString().trim();
        final String mo2 = mobi2.getText().toString().trim();
        final String mo3 = mobi3.getText().toString().trim();
        final String lan = land.getText().toString().trim();
        final String c = city.getText().toString().trim();
        if (name.isEmpty() || ename.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Fields marked with * are mandatory", Toast.LENGTH_LONG).show();
        }
        else {
           // progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Registering user....");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REGISTER, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", name);
                    params.put("esname", ename);
                    params.put("mo1", mo1);
                    params.put("mo2", mo2);
                    params.put("mo3", mo3);
                    params.put("lan", lan);
                    params.put("city", c);
                    params.put("dist", dist);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
    }
    private void confirmUser() {
        final String name = editTextname.getText().toString().trim();
        final String dist = district.getText().toString().trim();
        final String ename = editTextEname.getText().toString().trim();
        final String mo1 = mobi1.getText().toString().trim();
        final String mo2 = mobi2.getText().toString().trim();
        final String mo3 = mobi3.getText().toString().trim();
        final String lan = land.getText().toString().trim();
        final String c = city.getText().toString().trim();
        if (name.isEmpty() || ename.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fields marked with * are mandatory", Toast.LENGTH_LONG).show();
        }
        else {
            Intent intent = new Intent(Lead_Form.this, Confirm.class);
            intent.putExtra("name", name);
            intent.putExtra("dist", dist);
            intent.putExtra("ename", ename);
            intent.putExtra("mo1", mo1);
            intent.putExtra("mo2", mo2);
            intent.putExtra("mo3", mo3);
            intent.putExtra("lan", lan);
            intent.putExtra("c", c);
            startActivity(intent);

        }
    }


            @Override
    public void onClick(View view) {
        if(view==buttonRegister)
            registerUser();
        if(view==buttonConfirm)
            confirmUser();



    }


}

