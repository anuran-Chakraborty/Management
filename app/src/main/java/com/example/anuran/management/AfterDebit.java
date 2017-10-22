package com.example.anuran.management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AfterDebit extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2,conf;
    EditText amt;
    ProgressDialog progressDialog;
    public int li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_debit);
        TextView lid=(TextView)findViewById(R.id.lid);
        TextView nam=(TextView)findViewById(R.id.name);
        Bundle bundle = getIntent().getExtras();
        final String name=bundle.getString("name");
        final String mo1=bundle.getString("mo1");
        final String mo2=bundle.getString("mo2");
        final String mo3=bundle.getString("mo3");
        final String ename=bundle.getString("esname");
        final String land=bundle.getString("land");
        final String city=bundle.getString("city");
        final String district=bundle.getString("district");
        final int leadid=bundle.getInt("id");
        li=leadid+100000;
        lid.setText(Integer.toString(100000+bundle.getInt("id")));
        nam.setText(bundle.getString("name"));


        amt=(EditText)findViewById(R.id.leadid);
        conf=(Button)findViewById(R.id.confirm);
        b1=(Button)findViewById(R.id.button4);
        b2=(Button)findViewById(R.id.button5);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);


        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LedgerReport.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("id", li);
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onClick(View view) {
        if(view==b1)
            credit("1");
        if(view==b2)
            credit("0");




    }
    public void credit(final String x)
    {
        final String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
         final String amount=amt.getText().toString().trim();
        if(amount.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please enter proper amount", Toast.LENGTH_LONG).show();
        }
        else
        {
            progressDialog = new ProgressDialog(AfterDebit.this);
            progressDialog.setMessage("Transaction being processed....");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_DEBIT, new Response.Listener<String>() {
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

                    params.put("amount", amount);
                    params.put("status", x);
                    params.put("date",currentDate);
                    params.put("clid",Integer.toString(li));
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
    }

}