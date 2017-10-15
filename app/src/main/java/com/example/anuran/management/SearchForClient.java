package com.example.anuran.management;

import android.content.Intent;
import android.support.annotation.InterpolatorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class SearchForClient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_client);
        final EditText lid = (EditText) (findViewById(R.id.leadid));

        final Button search = (Button) findViewById(R.id.search);
        //if (lid.getText().toString().compareTo("") != 0) {
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int llid = Integer.parseInt(lid.getText().toString());
                    Toast.makeText(getApplicationContext(), Integer.toString(llid), Toast.LENGTH_LONG).show();

                    if (view == search)
                        SearchLead(llid);
                }


            });
        }
    //}



    private void SearchLead(int id){
        final String lid=Integer.toString(id);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_SEARCHLEAD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject obj=new JSONObject(response);
                    Toast.makeText(getApplicationContext(),Integer.toString(obj.getInt("status")), Toast.LENGTH_LONG).show();
                    Log.e("Tag",Integer.toString(obj.getInt("status")));
                    if(obj.getInt("status")==1) {
                        Intent intent=new Intent(getApplicationContext(),LeadDetails.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", Integer.parseInt(lid));
                        bundle.putString("name",obj.getString("name"));
                        bundle.putString("esname",obj.getString("ename"));
                        bundle.putString("mo1",obj.getString("mob1"));
                        bundle.putString("mo2",obj.getString("mob2"));
                        bundle.putString("mo3",obj.getString("mob3"));
                        bundle.putString("land",obj.getString("land"));
                        bundle.putString("city",obj.getString("city"));
                        bundle.putString("district",obj.getString("district"));


                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Invalid Client id", Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("Tag",e.getMessage());
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
                params.put("id",lid);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}

