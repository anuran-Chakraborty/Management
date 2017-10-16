package com.example.anuran.management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class FinanceStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_status);

        TextView lid=(TextView)findViewById(R.id.lid);
        TextView price=(TextView)findViewById(R.id.price);
        TextView rem=(TextView)findViewById(R.id.rem);
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
        lid.setText("CLIENT ID: "+Integer.toString(100000+bundle.getInt("id")));

        Button debit=(Button)findViewById(R.id.debit);


        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Confirm.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("id", leadid);
                bundle2.putString("name",name);
                bundle2.putString("ename",ename);
                bundle2.putString("mo1",mo1);
                bundle2.putString("mo2",mo2);
                bundle2.putString("mo3",mo3);
                bundle2.putString("lan",land);
                bundle2.putString("c",city);
                bundle2.putString("dist",district);
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });

    }
    void insertIntoFinance(int price,int id){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject obj=new JSONObject(response);
                    //Toast.makeText(getApplicationContext(),obj.getString("uname"), Toast.LENGTH_LONG).show();
                }
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
}
