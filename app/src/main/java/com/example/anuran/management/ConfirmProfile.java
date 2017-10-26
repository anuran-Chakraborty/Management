package com.example.anuran.management;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ConfirmProfile extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog progressDialog;
    public LinearLayout mLayout;
    public int flag=0;
    public String sites[]=new String[1000];public int count=0;
    public TextView insdate,insby,ldetails,qprice;
    public EditText sname;
    public Button confirm,vsite;
    public ImageButton add;
    public String cid,inby,indt,ldet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_profile);
        mLayout=(LinearLayout)findViewById(R.id.linearLayout);
        insdate=(TextView) findViewById(R.id.editText2);

        insby=(TextView) findViewById(R.id.editText1);
        ldetails=(TextView) findViewById(R.id.editText3);
        qprice=(TextView) findViewById(R.id.editText4);
        sname=(EditText)findViewById(R.id.editText6);
        add=(ImageButton)findViewById(R.id.searchImageButton);
        confirm=(Button)findViewById(R.id.btn4);
        vsite=(Button)findViewById(R.id.btn5);
        Bundle bundle = getIntent().getExtras();
        cid = bundle.getString("id");
        inby=bundle.getString("insby");
        indt=bundle.getString("insdet");
        ldet=bundle.getString("lidet");
        progressDialog = new ProgressDialog(ConfirmProfile.this);
        progressDialog.setMessage("Processing....");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_CONFIRMEDIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                //Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                try {
              //      Toast.makeText(getApplicationContext(),cid, Toast.LENGTH_SHORT).show();

                    JSONObject obj=new JSONObject(response);
                //    Toast.makeText(getApplicationContext(),"ZZZZ", Toast.LENGTH_SHORT).show();

                    //Toast.makeText(getApplicationContext(),obj.getString("uname"), Toast.LENGTH_LONG).show();
                    // TextView name=(TextView)findViewById(R.id.name);
                    //TextView uname=(TextView)findViewById(R.id.uname);
                    // TextView mo1=(TextView)findViewById(R.id.mo1);
                    //TextView ccid=(TextView)findViewById(R.id.ccid);
                  //  if(!(obj.getString("insby").isEmpty())) {
                        insby.setText("INSTALLED BY : "+inby);
                        insby.setTextColor(Color.MAGENTA);
                        insby.setTextSize(20);
                    //}
                    //uname.setText(obj.getString("uname"));
                    //if(!(obj.getString("insdate").isEmpty())) {

                        insdate.setText("INSTALLED ON : "+indt);
                        insdate.setTextColor(Color.MAGENTA);
                        insdate.setTextSize(20);
                    //}
                    //  ccid.setText(Integer.toString(100000+obj.getInt("id")));
                    //Toast.makeText(getApplicationContext(),"Hi", Toast.LENGTH_SHORT).show();
                    //if(!(obj.getString("license").isEmpty())) {
                        ldetails.setText("License no. : "+ldet);

                        ldetails.setTextColor(Color.MAGENTA);
                        ldetails.setTextSize(20);
                  //  Toast.makeText(getApplicationContext(),"Hi", Toast.LENGTH_SHORT).show();
                    if(!(obj.getString("credit").isEmpty())) {
                        qprice.setText("DUE : (\u20B9)"+obj.getString("credit"));
                        qprice.setTextColor(Color.MAGENTA);
                        qprice.setTextSize(20);
                    }
                    //}
                    //Toast.makeText(getApplicationContext(),"Hello", Toast.LENGTH_SHORT).show();


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
           //     Toast.makeText(getApplicationContext(),cid,Toast.LENGTH_LONG).show();
                params.put("id",cid);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        add.setOnClickListener(this);
        confirm.setOnClickListener(this);
        vsite.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view==add)
            addsite();
        if(view==confirm)
            confirmme();
        if(view==vsite)
            viewsite();

    }
    private void viewsite()
    {
        Intent intent=new Intent(ConfirmProfile.this,ViewSites.class);
        intent.putExtra("cid",cid);
        startActivity(intent);
    }
    private void addsite() {
        final String site= sname.getText().toString().trim();
        if(site.isEmpty())
            Toast.makeText(getApplicationContext(),"Please write proper site name", Toast.LENGTH_LONG).show();
        else
        {
            sites[count]=site;
            count=count+1;
            Toast.makeText(getApplicationContext(),sites[count-1], Toast.LENGTH_LONG).show();
            mLayout.addView(createNewTextView(site,count));
            sname.setText("");
        }


    }
    private TextView createNewTextView(String text, int c) {
        final ActionBar.LayoutParams lparams = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText("Site " + c + " " + text);
        textView.setTextColor(Color.RED);
        textView.setTextSize(18);
        return textView;
    }

    private void confirmme() {
     //   final String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Bundle bundle = getIntent().getExtras();

       // final String name = bundle.getString("name");
        final String dist = bundle.getString("dist");
        //final String ename = bundle.getString("ename");
        final String mo1 = bundle.getString("mo1");
        final String mo2 = bundle.getString("mo2");
        final String mo3 = bundle.getString("mo3");
        final String lan = bundle.getString("lan");
        final String c = bundle.getString("c");
       // final String in_date = insdate.getText().toString().trim();
       // final String in_by = insby.getText().toString().trim();
       // final String in_det = ldetails.getText().toString().trim();
       // final String q_pr = qprice.getText().toString().trim();
   //     final String f_pr = fprice.getText().toString().trim();

        //Toast.makeText(getApplicationContext(),Integer.toString(count), Toast.LENGTH_LONG).show();
        progressDialog = new ProgressDialog(ConfirmProfile.this);
        progressDialog.setMessage("Confirming profile....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CONFIRM2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
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
                JSONObject jsonObject=new JSONObject();
                for(int i=0;i<count;i++)
                {
                    try {
                        jsonObject.put("params_"+i,sites[i]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                params.put("params",jsonObject.toString());
                //params.put("name", name);
                //params.put("esname", ename);
                params.put("mo1", mo1);
                params.put("mo2", mo2);
                params.put("mo3", mo3);
                params.put("lan", lan);
                params.put("city", c);
                params.put("dist", dist);
                params.put("id",cid);
                if(count==0)
                    params.put("st",Integer.toString(0));
                else
                params.put("st",Integer.toString(1));
               // params.put("in_date",in_date);
                //params.put("in_by",in_by);
                //params.put("in_det",in_det);
                //params.put("q_pr",q_pr);
                //params.put("f_pr",f_pr);
                //params.put("date",currentDate);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
