package com.example.anuran.management;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {

    private EditText t1, t2, t3;
    private Button b1;
    ProgressDialog progressDialog;
    public String old1="";
    public String id="";
    public String newpass="";
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        t1 = (EditText) findViewById(R.id.fullName);
        t2 = (EditText) findViewById(R.id.password);
        t3 = (EditText) findViewById(R.id.confirmPassword);
        b1 = (Button) findViewById(R.id.signUpBtn);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == b1) {
            change();
        }

    }
    public void change()
    {
        final String old=t1.getText().toString().trim();
        newpass=t2.getText().toString().trim();
        final String conpass=t3.getText().toString().trim();
        Bundle bundle= getIntent().getExtras();
         old1=bundle.getString("old");
        id=bundle.getString("id");
        if(old.isEmpty()|| newpass.isEmpty() || conpass.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"All fields must be filled...", Toast.LENGTH_LONG).show();

        }
        else if(old.equals(old1)== false)
            Toast.makeText(getApplicationContext(),"Old password entered is incorrect...", Toast.LENGTH_LONG).show();
        else if(newpass.equals(conpass)==false)
            Toast.makeText(getApplicationContext(),"New password and confirm password do not match...", Toast.LENGTH_LONG).show();
        else if(old.equals(newpass)== true)
            Toast.makeText(getApplicationContext(),"Old password and new password cannot be same...", Toast.LENGTH_LONG).show();
        else
        {
            progressDialog = new ProgressDialog(ChangePassword.this);
            progressDialog.setMessage("Changing Password....");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CHANGE, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        SharedPrefManagerClient.getInstance(ChangePassword.this).logout();
                        Intent intent=new Intent(ChangePassword.this,clientLogin.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
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


                    params.put("id",id);
                    params.put("new", newpass);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }


    }
    }
