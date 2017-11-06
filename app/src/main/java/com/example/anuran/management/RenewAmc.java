package com.example.anuran.management;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

public class RenewAmc extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG= "Confirm";

    TextView tv1,tv2;
    EditText t1;
    Button b1;
    private DatePickerDialog.OnDateSetListener mDate;
    public String expire,cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renew_amc);
        Bundle bundle = getIntent().getExtras();
        cid = bundle.getString("id");
        tv1 = (TextView) findViewById(R.id.textView11);
        tv2 = (TextView) findViewById(R.id.expire);
        t1 = (EditText) findViewById(R.id.amt);
        b1 = (Button) findViewById(R.id.button7);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(RenewAmc.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDate, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: mm/dd/yyyy: " + month + "/" + day + "/" + year);
                month=month+1;
                String date = month + "/" + (day) + "/" + year;
                tv1.setText(date);
                tv1.setTextColor(Color.GREEN);
                tv1.setTextSize(18);
                callme();

            }
        };
    }
    public void callme() {

        final String end = tv1.getText().toString().trim();
        if (end.isEmpty()) {
            tv2.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.INVISIBLE);
            t1.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(), end, Toast.LENGTH_LONG).show();
        } else {
            tv2.setVisibility(View.VISIBLE);
            b1.setVisibility(View.VISIBLE);
            int slash1 = end.indexOf("/");
            int slash2 = end.lastIndexOf("/");
            int dtend = Integer.parseInt(end.substring(slash1 + 1, slash2));
            int mend = Integer.parseInt(end.substring(0, slash1));
            int yrend = Integer.parseInt(end.substring(slash2 + 1));
            if (((yrend % 400 == 0) || ((yrend % 4 == 0) && (yrend % 100 != 0))) && (mend == 2) && (dtend == 29)) {
                dtend = dtend - 1;
            }
            yrend=yrend+1;
            expire = mend + "/" + dtend + "/" + yrend;
            tv2.setText("EXPIRES ON:(mm/dd/yyyy) : " + expire);
            tv2.setTextColor(Color.GREEN);
            tv2.setTextSize(18);
        }
        if (tv2.getText().toString().trim().isEmpty()) {
            t1.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.INVISIBLE);
        } else {
            t1.setVisibility(View.VISIBLE);

        }
            b1.setVisibility(View.VISIBLE);

        b1.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if(view==b1)
        {
            amc();
        }


    }
    private void amc()
    {
        final String dts=tv1.getText().toString().trim();
        int slash11 = dts.indexOf("/");
        int slash22 = dts.lastIndexOf("/");
        final String a=dts.substring(slash11 + 1, slash22)+"-"+dts.substring(0,slash11)+"-"+dts.substring(slash22+1);

        //final String dte=tv2.getText().toString().trim();
        final String amt=t1.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_AMC_NEW, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                int flag=0;
                Toast.makeText(getApplicationContext(),response, Toast.LENGTH_LONG).show();
                try {
                    JSONObject obj = new JSONObject(response);
                 /*   Toast.makeText(getApplicationContext(),obj.getString("message"), Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),Amc.class);
                    finish();
                    startActivity(intent);*/

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Server error", Toast.LENGTH_LONG).show();
                error.printStackTrace();
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                params.put("start1",a);
                params.put("start", dts);
                params.put("end",expire);
                params.put("amt",amt);
                params.put("id",cid);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

}
