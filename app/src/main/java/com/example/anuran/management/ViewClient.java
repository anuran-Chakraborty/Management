package com.example.anuran.management;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

public class ViewClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_client);
        TextView name=(TextView)findViewById(R.id.name);
        //TextView uname=(TextView)findViewById(R.id.uname);
        TextView mo1=(TextView)findViewById(R.id.mo1);
        TextView ccid=(TextView)findViewById(R.id.ccid);


        Bundle bundle = getIntent().getExtras();
        final int cid = bundle.getInt("id");
       // Toast.makeText(getApplicationContext(),Integer.toString(cid), Toast.LENGTH_LONG).show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_VIEWCLIENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject obj=new JSONObject(response);
                    //Toast.makeText(getApplicationContext(),obj.getString("uname"), Toast.LENGTH_LONG).show();
                    TextView name=(TextView)findViewById(R.id.name);
                    //TextView uname=(TextView)findViewById(R.id.uname);
                    TextView mo1=(TextView)findViewById(R.id.mo1);
                    TextView ccid=(TextView)findViewById(R.id.ccid);
                    name.setText(obj.getString("name"));
                    //uname.setText(obj.getString("uname"));
                    mo1.setText(obj.getString("mo1"));
                    ccid.setText(Integer.toString(100000+obj.getInt("id")));

                    }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Exception", Toast.LENGTH_LONG).show();
                    Log.e("exception:",e.getMessage());
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
                params.put("id",Integer.toString(cid));
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
