package com.example.anuran.management;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class SupportList extends AppCompatActivity {
    RecyclerView sites;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_list);

        //Bundle bundle = getIntent().getExtras();
        //final String cid=bundle.getString("cid");
        //Toast.makeText(getApplicationContext(),cid, Toast.LENGTH_LONG).show();
        sites=(RecyclerView)findViewById(R.id.rv);
        sites.setHasFixedSize(true);
        sites.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(SupportList.this);
        progressDialog.setMessage("Processing....");
        progressDialog.show();


        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_VIEW_SUPPORT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                //Toast.makeText(getApplicationContext(),"gsgf", Toast.LENGTH_LONG).show();
                SupportDesp site;
                List<SupportDesp> siteList=new ArrayList<SupportDesp>();
                Log.e("log_tag", "fesfd");
                try {

                    JSONArray jArray = new JSONArray(response);
                    Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                    Log.e("log_tag", response);
                    if(jArray.length()>1) {
                        Log.e("log_tag", "fesfd3");
                        for (int i = 0; i < jArray.length()-1; i++) {
                            Log.e("log_tag", "fesfd6");
                            site =new SupportDesp();
                            JSONObject json_data = jArray.getJSONObject(i);
                            site.supid=json_data.getString("id");
                            site.nm=json_data.getString("uname");
                            siteList.add(site);
                            }

                        Log.e("log_tag", "fesfd4");
                        ProductAdapterSupport adapter = new ProductAdapterSupport(SupportList.this, siteList);
                        sites.setAdapter(adapter);
                        Log.e("log_tag", "fesfd5");

                        //sites.setVisibility(View.VISIBLE);
                    }

                    else{
                        Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_LONG).show();
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
        });
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
