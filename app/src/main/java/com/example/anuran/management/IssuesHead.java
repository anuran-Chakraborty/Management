package com.example.anuran.management;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class IssuesHead extends Fragment {
    RecyclerView sites;
    ProgressDialog progressDialog;
    public IssuesHead() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_issues, container, false);
        sites=(RecyclerView)rootView.findViewById(R.id.rv);
        sites.setHasFixedSize(true);
        sites.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        progressDialog = new ProgressDialog(rootView.getContext());
        progressDialog.setMessage("Processing....");
        progressDialog.show();


        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_VIEWISSUES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                //Toast.makeText(getApplicationContext(),"gsgf", Toast.LENGTH_LONG).show();
                IssueDesp issue;
                List<IssueDesp> issueList= new ArrayList<>();
                Log.e("log_tag", "fesfd");
                try {

                    JSONArray jArray = new JSONArray(response);
                    //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                    Log.e("log_tag", response);
                    if(jArray.length()>1) {
                        Log.e("log_tag", "fesfd3");
                        for (int i = 0; i < jArray.length()-1; i++) {
                            Log.e("log_tag", "fesfd6");
                            issue =new IssueDesp();
                            JSONObject json_data = jArray.getJSONObject(i);
                            issue.issueid=json_data.getString("id");
                            issue.description=json_data.getString("description");
                            issue.client_id=json_data.getString("clientid");
                            issueList.add(issue);
                        }

                        Log.e("log_tag", "fesfd4");
                        ProductAdapterIssue adapter = new ProductAdapterIssue(rootView.getContext(), issueList);
                        sites.setAdapter(adapter);
                        Log.e("log_tag", "fesfd5");

                        //sites.setVisibility(View.VISIBLE);
                    }

                    else{
                        //Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_LONG).show();
                        Log.e("log_tag", "fesfd2");
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("exception:",e.getMessage());
                    //Toast.makeText(getApplicationContext(),"Exception", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(),"Server error",Toast.LENGTH_LONG).show();
                error.printStackTrace();
                Log.e("VOLLEY", error.toString());
            }
        });
        RequestHandler.getInstance(rootView.getContext()).addToRequestQueue(stringRequest);

        return rootView;
    }

}


