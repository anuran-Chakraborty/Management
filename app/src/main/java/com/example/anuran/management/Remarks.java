package com.example.anuran.management;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Remarks extends AppCompatActivity {
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remarks);
        Bundle bundle = getIntent().getExtras();
        final String id = Integer.toString(bundle.getInt("id"));
        Toast.makeText(getApplicationContext(),id, Toast.LENGTH_LONG).show();
        final EditText rem=(EditText)findViewById(R.id.remarks);

        Button submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String remarks=rem.getText().toString();
                insert(id,remarks);
            }
        });
    }
    public void insert(String id,String remarks){
        final String iid=id;
        Toast.makeText(getApplicationContext(),iid,Toast.LENGTH_LONG).show();

        final String irem=remarks;
        Toast.makeText(getApplicationContext(),irem,Toast.LENGTH_LONG).show();
        progressDialog = new ProgressDialog(Remarks.this);
        progressDialog.setMessage("Processing....");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_REMARKS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                //Toast.makeText(getApplicationContext(),"gsgf", Toast.LENGTH_LONG).show();
                Log.e("log_tag", "fesfd");
                try {
                    JSONObject obj=new JSONObject(response);

                    if(obj.getString("success").compareTo("Success")==0){
                        Toast.makeText(getApplicationContext(),"Remarks successfully sent.....Thank you for your feedback", Toast.LENGTH_LONG).show();
                    }




                    else{
                        Toast.makeText(getApplicationContext(), "Failed..try resending", Toast.LENGTH_LONG).show();
                        Log.e("log_tag", "fesfd2");
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("exception:",e.getMessage());
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
                params.put("id",iid);
                params.put("remarks",irem);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}

