package com.example.anuran.management;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class Amc extends Fragment implements View.OnClickListener {
    Button confirm,ledger;
    EditText clientid;
    public int flag;
    public Amc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_amc, container, false);
        confirm = (Button) rootView.findViewById(R.id.search);
        ledger = (Button) rootView.findViewById(R.id.ledger);
      //  addamc = (Button) rootView.findViewById(R.id.addamc);
        clientid=(EditText)rootView.findViewById(R.id.cid);


        confirm.setOnClickListener(this);
        ledger.setOnClickListener(this);


        return rootView;
    }
    @Override
    public void onClick(View view) {
        if (view == ledger) {
            ledgerc();

        }

        if (view == confirm) {

            seeamc();

        }
                }
    private void seeamc()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_AMC_CLIENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                int flag=0;
                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject obj = new JSONObject(response);
                    //Toast.makeText(getApplicationContext(),obj.getString("uname"), Toast.LENGTH_LONG).show();

                    if (obj.getInt("empty") == 1) {
                        Toast.makeText(getActivity(),"No such client exist",Toast.LENGTH_LONG).show();

                        //flag = 1;

                        //Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();

                    }
                    else if(obj.getInt("empty") == 2)
                    {
                        Intent intent=new Intent(getActivity(),ViewAmc.class);
                        intent.putExtra("id",clientid.getText().toString().trim());
                        intent.putExtra("status","No");
                        startActivity(intent);
                        //Toast.makeText(getActivity(),"Debugging",Toast.LENGTH_LONG).show();
                    }

                    else {
                        //flag = 2;
                        Toast.makeText(getActivity(),"exist",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getActivity(),ViewAmc.class);
                        intent.putExtra("id",clientid.getText().toString().trim());
                        intent.putExtra("status","Yes");
                        intent.putExtra("start",obj.getString("start"));
                        intent.putExtra("end",obj.getString("expiry"));
                        startActivity(intent);


                        //Toast.makeText(getApplicationContext(),"Invalid", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Exception", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Server error", Toast.LENGTH_LONG).show();
                error.printStackTrace();
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("clid", clientid.getText().toString().trim());
                return params;
            }
        };
        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }
    private void ledgerc()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_AMC_CLIENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                int flag=0;
                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject obj = new JSONObject(response);
                    //Toast.makeText(getApplicationContext(),obj.getString("uname"), Toast.LENGTH_LONG).show();

                    if (obj.getInt("empty") == 1) {
                        Toast.makeText(getActivity(),"No such client exist",Toast.LENGTH_LONG).show();

                        //flag = 1;

                        //Toast.makeText(getApplicationContext(),"Success", Toast.LENGTH_LONG).show();

                    } else {
                        //flag = 2;
                        Toast.makeText(getActivity(),"exist",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), LedgerReport.class);
                        intent.putExtra("id", 100000+(Integer.parseInt(clientid.getText().toString().trim())));
                        startActivity(intent);


                        //Toast.makeText(getApplicationContext(),"Invalid", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Exception", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Server error", Toast.LENGTH_LONG).show();
                error.printStackTrace();
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("clid", clientid.getText().toString().trim());
                return params;
            }
        };
        RequestHandler.getInstance(getActivity()).addToRequestQueue(stringRequest);

        }





    }

