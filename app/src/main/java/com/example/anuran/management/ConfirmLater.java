package com.example.anuran.management;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.util.HashMap;
import java.util.Map;

public class ConfirmLater extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG= "Confirm";

    ProgressDialog progressDialog;
    public LinearLayout mLayout;
    public int flag=0;
    public String sites[]=new String[1000];public int count=0;
    public EditText insdate,insby,ldetails,qprice,fprice,sname;
    private DatePickerDialog.OnDateSetListener mDate;
    public Button confirm;
    public ImageButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_later);
        mLayout=(LinearLayout)findViewById(R.id.linearLayout);
        insdate=(EditText)findViewById(R.id.editText2);

        insby=(EditText)findViewById(R.id.editText1);
        ldetails=(EditText)findViewById(R.id.editText3);
        qprice=(EditText)findViewById(R.id.editText4);
        fprice=(EditText)findViewById(R.id.editText5);
        sname=(EditText)findViewById(R.id.editText6);
        add=(ImageButton)findViewById(R.id.searchImageButton);
        confirm=(Button)findViewById(R.id.btn4);
        insdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(ConfirmLater.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDate,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDate = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker,int year,int month,int day)
            {
                Log.d(TAG,"onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year);
                String date = month + "/" + day + "/" + year;
                insdate.setText(date);
            }
        };
        add.setOnClickListener(this);
        confirm.setOnClickListener(this);

    }

   @Override
    public void onClick(View view) {
        if(view==add)
        {
            flag=1;
            addsite();
        }

        if(view==confirm)
        {
            if(flag==0)
                Toast.makeText(getApplicationContext(),"Atleast 1 site must be added", Toast.LENGTH_LONG).show();
            else
                confirmme();
        }



    }
    private void confirmme() {
        Bundle bundle = getIntent().getExtras();

        final String name = bundle.getString("name");
        final String dist = bundle.getString("district");
        final String ename = bundle.getString("ename");
        final String mo1 = bundle.getString("mo1");
        final String mo2 = bundle.getString("mo2");
        final String mo3 = bundle.getString("mo3");
        final String lan = bundle.getString("land");
        final String c = bundle.getString("city");
        final String in_date = insdate.getText().toString().trim();
        final String in_by = insby.getText().toString().trim();
        final String in_det = ldetails.getText().toString().trim();
        final String q_pr = qprice.getText().toString().trim();
        final String f_pr = fprice.getText().toString().trim();

        //Toast.makeText(getApplicationContext(),Integer.toString(count), Toast.LENGTH_LONG).show();
        progressDialog = new ProgressDialog(ConfirmLater.this);
        progressDialog.setMessage("Registering client....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CONFIRM, new Response.Listener<String>() {
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
                params.put("name", name);
                params.put("esname", ename);
                params.put("mo1", mo1);
                params.put("mo2", mo2);
                params.put("mo3", mo3);
                params.put("lan", lan);
                params.put("city", c);
                params.put("dist", dist);
                params.put("in_date",in_date);
                params.put("in_by",in_by);
                params.put("in_det",in_det);
                params.put("q_pr",q_pr);
                params.put("f_pr",f_pr);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
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




}
