package com.example.anuran.management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class Issue extends AppCompatActivity implements View.OnClickListener {

    EditText sub,des;
    //public String su,ds;
    ImageButton attach;
    Button confirm;
    public String cid;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);
        sub=(EditText)findViewById(R.id.subject);
        des=(EditText)findViewById(R.id.message);
        attach=(ImageButton)findViewById(R.id.send_button);
        confirm=(Button)findViewById(R.id.button6);
        confirm.setOnClickListener(this);
        attach.setOnClickListener(this);
        Bundle bundle=getIntent().getExtras();
        cid=bundle.getString("id");

    }
    @Override
    public void onClick(View view) {
        if(view==confirm)
        {
       //     flag=1;
         confirmme();
        }

        if(view==attach)
        {
            attachme();
            //if(flag==0)
              //  Toast.makeText(getApplicationContext(),"Atleast 1 site must be added", Toast.LENGTH_LONG).show();
           // else
            //    confirmme();
        }
    }
    private void attachme()
    {
  final String su=sub.getText().toString().trim();
  final String ds=des.getText().toString().trim();

        Toast.makeText(getApplicationContext(),su, Toast.LENGTH_LONG).show();
        Intent intent=new Intent(Issue.this,MainActivity.class);
        intent.putExtra("id",cid);
        intent.putExtra("sub",su);
        intent.putExtra("des",ds);
        finish();
        startActivity(intent);

    }
    private void confirmme()
    {
        //su=sub.getText().toString().trim();
        //ds=des.getText().toString().trim();
        final String su=sub.getText().toString().trim();
        final String ds=des.getText().toString().trim();
        if(su.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Subject cannot be left blank...", Toast.LENGTH_LONG).show();
        }
        else {
            progressDialog = new ProgressDialog(Issue.this);
            progressDialog.setMessage("Raising issue....");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CONFIRM3, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    try {
                    /*    Toast.makeText(getApplicationContext(),"Issue raised successfully", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Issue.this,Client.class);
                        finish();
                        startActivity(intent);*/

                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                       /* Intent intent=new Intent(Issue.this,Client.class);
                        finish();
                        startActivity(intent);*/
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
                    //JSONObject jsonObject=new JSONObject();
                   params.put("id",cid);
                    params.put("sub", su);
                    params.put("des", ds);
                    return params;
                }
            };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }

    }
}
