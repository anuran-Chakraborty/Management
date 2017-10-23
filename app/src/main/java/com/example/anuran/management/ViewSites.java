package com.example.anuran.management;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
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

public class ViewSites extends AppCompatActivity {
    RecyclerView sites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sites);

        Bundle bundle = getIntent().getExtras();
        final String cid=bundle.getString("cid");
        Toast.makeText(getApplicationContext(),cid, Toast.LENGTH_LONG).show();
        sites=(RecyclerView)findViewById(R.id.rv);
        sites.setHasFixedSize(true);
        sites.setLayoutManager(new LinearLayoutManager(this));

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_VIEW_SITES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),"gsgf", Toast.LENGTH_LONG).show();
                SiteDesp site;
                List<SiteDesp> siteList=new ArrayList<SiteDesp>();
                Log.e("log_tag", "fesfd");
                try {

                    JSONArray jArray = new JSONArray(response);
                    Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                    Log.e("log_tag", response);
                    if(jArray.length()>1) {
                        Log.e("log_tag", "fesfd3");
                        for (int i = 1; i < jArray.length(); i++) {
                            Log.e("log_tag", "fesfd6");
                            site =new SiteDesp();
                            JSONObject json_data = jArray.getJSONObject(i);
                            site.sid=json_data.getString("id");
                            site.client_id=json_data.getString("userid");
                            site.sitename=json_data.getString("site");
                            siteList.add(site);
                            }

                        Log.e("log_tag", "fesfd4");
                        ProductAdapter adapter = new ProductAdapter(ViewSites.this, siteList);
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("id",cid);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
