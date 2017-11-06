package com.example.anuran.management;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IssueDetails extends AppCompatActivity {

    public String iid;

    List<DataAdapter> ListOfdataAdapter;

    RecyclerView recyclerView;

    String HTTP_JSON_URL = "http://supernovasoftsys.com/Android/v1/ImageViewer.php";

    String Image_Name_JSON = "image_title";

    String Image_URL_JSON = "image_url";

    JsonArrayRequest RequestOfJSonArray ;

    RequestQueue requestQueue ;

    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;

    ArrayList<String> ImageTitleNameArrayListForClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_details);
        Bundle bundle = getIntent().getExtras();
        final String id = Integer.toString(bundle.getInt("id"));
        iid=id;
        Toast.makeText(getApplicationContext(),id, Toast.LENGTH_LONG).show();

        ImageTitleNameArrayListForClick = new ArrayList<>();

        ListOfdataAdapter = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);




        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_ISSUE_DET, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj=new JSONObject(response);
                    TextView iid=(TextView)findViewById(R.id.iid);
                    TextView sid=(TextView)findViewById(R.id.sid);
                    TextView cid=(TextView)findViewById(R.id.cid);
                    TextView sub=(TextView)findViewById(R.id.sub);
                    TextView idet=(TextView)findViewById(R.id.idet);
                    iid.setText("Issue id: "+obj.getString("id"));
                    sid.setText("Site id: "+obj.getString("siteid"));
                    cid.setText("Client id: "+obj.getString("clientid"));
                    sub.setText("Subject: "+obj.getString("sub"));
                    idet.setText("Description: "+obj.getString("description"));


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
                params.put("id",iid);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);










        //Image Viewing  #################################

        stringRequest=new StringRequest(Request.Method.POST, HTTP_JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array=new JSONArray(response);
                    for(int i = 0; i<array.length()-1; i++) {

                        DataAdapter GetDataAdapter2 = new DataAdapter();

                        JSONObject json = null;
                        try {

                            json = array.getJSONObject(i);

                            GetDataAdapter2.setImageUrl(json.getString("image_path"));

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                        ListOfdataAdapter.add(GetDataAdapter2);
                    }

                    recyclerViewadapter = new RecyclerViewAdapter(ListOfdataAdapter, IssueDetails.this);

                    recyclerView.setAdapter(recyclerViewadapter);

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
                params.put("id",iid);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        Button resolve=(Button)findViewById(R.id.resolve);
        Button give=(Button)findViewById(R.id.give);

        resolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resolve();
            }
        });

















    }

    public void Resolve(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_ISSUE_RESOLVE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj=new JSONObject(response);
                    if(obj.getString("success").compareTo("Success")==0){
                        Toast.makeText(getApplicationContext(),"Issue was resolved", Toast.LENGTH_LONG).show();
                    }




                    else{
                        Toast.makeText(getApplicationContext(), "Failed..try resending", Toast.LENGTH_LONG).show();
                        Log.e("log_tag", "fesfd2");
                    }
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
                params.put("id",iid);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}
