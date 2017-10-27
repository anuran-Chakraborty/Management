package com.example.anuran.management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
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

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {
    TextView editTextname,editTextEname;
    EditText district, mobi1, mobi2, mobi3, land, city;
    Button buttonRegister,buttonConfirm;
    ProgressDialog progressDialog;
    public  String cid,insby,insdet,lidet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        editTextname = (TextView) findViewById(R.id.editText1);
        district = (EditText) findViewById(R.id.editText9);
        editTextEname = (TextView) findViewById(R.id.editText10);
        mobi1 = (EditText) findViewById(R.id.editText4);
        mobi2 = (EditText) findViewById(R.id.editText5);
        mobi3 = (EditText) findViewById(R.id.editText6);
        land = (EditText) findViewById(R.id.editText7);
        city = (EditText) findViewById(R.id.editText3);
        buttonRegister = (Button) findViewById(R.id.btn4);
        Bundle bundle = getIntent().getExtras();
         cid = bundle.getString("id");
        // Toast.makeText(getApplicationContext(),cid, Toast.LENGTH_LONG).show();
      //  progressDialog = new ProgressDialog(EditProfile.this);
       // progressDialog.setMessage("Fetching data....");
        //progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_EDIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
          //      progressDialog.dismiss();

                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject obj=new JSONObject(response);
                    //Toast.makeText(getApplicationContext(),obj.getString("uname"), Toast.LENGTH_LONG).show();
                   // TextView name=(TextView)findViewById(R.id.name);
                    //TextView uname=(TextView)findViewById(R.id.uname);
                   // TextView mo1=(TextView)findViewById(R.id.mo1);
                    //TextView ccid=(TextView)findViewById(R.id.ccid);
                    if(!(obj.getString("name").isEmpty())) {
                        editTextname.setText("NAME : "+obj.getString("name"));
                        editTextname.setTextColor(Color.MAGENTA);
                        editTextname.setTextSize(20);
                    }
                    //uname.setText(obj.getString("uname"));
                    if(!(obj.getString("mo1").isEmpty())) {

                        mobi1.setText(obj.getString("mo1"));
                      mobi1.setTextColor(Color.MAGENTA);
                        mobi1.setTextSize(20);
                    }
                  //  ccid.setText(Integer.toString(100000+obj.getInt("id")));
                    //Toast.makeText(getApplicationContext(),"Hi", Toast.LENGTH_SHORT).show();
                    if(!(obj.getString("mo2").isEmpty())) {
                        mobi2.setText(obj.getString("mo2"));

                        mobi2.setTextColor(Color.MAGENTA);
                        mobi2.setTextSize(20);
                    }
                    //Toast.makeText(getApplicationContext(),"Hello", Toast.LENGTH_SHORT).show();
                    if(!(obj.getString("mo3").isEmpty())){
                    mobi3.setText(obj.getString("mo3"));
                        mobi3.setTextColor(Color.MAGENTA);
                        mobi3.setTextSize(20);
                    }
                    if(!(obj.getString("landline").isEmpty())){
                    land.setText(obj.getString("landline"));
                        land.setTextColor(Color.MAGENTA);
                        land.setTextSize(20);
                    }
                    if(!(obj.getString("district").isEmpty())){
                    district.setText(obj.getString("district"));
                        district.setTextColor(Color.MAGENTA);
                        district.setTextSize(20);
                    }
                    if(!(obj.getString("esname").isEmpty())){
                    editTextEname.setText("ESTABLISHMENT NAME : "+obj.getString("esname"));
                     editTextEname.setTextColor(Color.MAGENTA);
                        editTextEname.setTextSize(20);
                    }
                    if(!(obj.getString("city").isEmpty())) {
                        city.setText(obj.getString("city"));
                        city.setTextColor(Color.MAGENTA);
                        city.setTextSize(20);
                    }
                    insby=obj.getString("insby");
                    insdet=obj.getString("insdate");
                    lidet=obj.getString("license");


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
                params.put("id",cid);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        progressDialog = new ProgressDialog(this);
        buttonRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view==buttonRegister)
           nextgo();

    }
    public void nextgo()
    {
        final String name = editTextname.getText().toString().trim();
        final String dist = district.getText().toString().trim();
        final String ename = editTextEname.getText().toString().trim().substring(21);
        final String mo1 = mobi1.getText().toString().trim();
        final String mo2 = mobi2.getText().toString().trim();
        final String mo3 = mobi3.getText().toString().trim();
        final String lan = land.getText().toString().trim();
        final String c = city.getText().toString().trim();
    //if (name.isEmpty() || ename.isEmpty()) {
            //Toast.makeText(getApplicationContext(), insby, Toast.LENGTH_LONG).show();
       // }
       // else {
            Intent intent = new Intent(EditProfile.this, ConfirmProfile.class);
            intent.putExtra("id",cid);
            intent.putExtra("name", name);
            intent.putExtra("dist", dist);
            intent.putExtra("ename", ename);
            intent.putExtra("mo1", mo1);
            intent.putExtra("mo2", mo2);
            intent.putExtra("mo3", mo3);
            intent.putExtra("lan", lan);
            intent.putExtra("c", c);
            intent.putExtra("insby",insby);
            intent.putExtra("insdet",insdet);
            intent.putExtra("lidet",lidet);
            finish();
            startActivity(intent);

        //}
    }

}



