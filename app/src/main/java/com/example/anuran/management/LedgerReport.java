package com.example.anuran.management;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import static com.example.anuran.management.R.id.username;

public class LedgerReport extends Activity {
    public int cd;
    ProgressDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledger_report);
        Bundle bundle = getIntent().getExtras();
        final int li=bundle.getInt("id");
        progressDialog = new ProgressDialog(LedgerReport.this);
        progressDialog.setMessage("Making report....");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_LEDGER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                //Toast.makeText(getApplicationContext(),"gsgf", Toast.LENGTH_LONG).show();
                Log.e("log_tag", "fesfd");
                try {

                    JSONArray jArray = new JSONArray(response);
                    //Toast.makeText(getApplicationContext(),"g", Toast.LENGTH_LONG).show();
                    if(jArray.length()>0) {

                        TableLayout tv = (TableLayout) findViewById(R.id.table);
                        tv.removeAllViewsInLayout();
                        int flag = 1;
                        for (int i = -1; i < jArray.length()-1; i++) {
                            TableRow tr = new TableRow(LedgerReport.this);
                            tr.setLayoutParams(new LayoutParams(
                                    LayoutParams.FILL_PARENT,
                                    LayoutParams.WRAP_CONTENT));
                            if (flag == 1) {
                                TextView b6 = new TextView(LedgerReport.this);
                                b6.setText("Credit/To be paid(\u20B9):");
                                b6.setTextColor(Color.BLUE);
                                b6.setTextSize(16);
                                tr.addView(b6);
                                TextView b19 = new TextView(LedgerReport.this);
                                b19.setPadding(20, 0, 0, 0);
                                b19.setTextSize(16);
                                b19.setText("Debit(\u20B9):");
                                b19.setTextColor(Color.BLUE);
                                tr.addView(b19);
                                TextView b29 = new TextView(LedgerReport.this);
                                b29.setPadding(20, 0, 0, 0);
                                b29.setText("Dt(dd/mm/yyyy):");
                                b29.setTextColor(Color.BLUE);
                                b29.setTextSize(16);
                                tr.addView(b29);
                                tv.addView(tr);
                                final View vline = new View(LedgerReport.this);
                                vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 2));
                                vline.setBackgroundColor(Color.BLUE);
                                tv.addView(vline);
                                flag = 0;
                            }
                            else {
                                JSONObject json_data = jArray.getJSONObject(i);
                                //  Log.i("log_tag", "id: " + json_data.getInt("id") + ", Username: " + json_data.getString("uname") + ", Name: " + json_data.getString("name"));

                                TextView b = new TextView(LedgerReport.this);
                                cd=json_data.getInt("credit");
                                String stime = String.valueOf(json_data.getInt("credit"));
                                b.setText(stime);
                                b.setTextColor(Color.RED);
                                b.setTextSize(16);
                                tr.addView(b);


                                TextView b2 = new TextView(LedgerReport.this);
                                b2.setPadding(20, 0, 0, 0);
                                String stime2 = json_data.getString("debit");
                                b2.setText(stime2);
                                b2.setTextColor(Color.RED);
                                b2.setTextSize(16);
                                tr.addView(b2);
                                TextView b3 = new TextView(LedgerReport.this);
                                b3.setPadding(20, 0, 0, 0);
                                String stime3 = json_data.getString("date");
                                b3.setText(stime3);
                                b3.setTextColor(Color.RED);
                                b3.setTextSize(16);
                                tr.addView(b3);



                                tv.addView(tr);
                                final View vline1 = new View(LedgerReport.this);
                                vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
                                vline1.setBackgroundColor(Color.GREEN);
                                tv.addView(vline1);
                            }
                        }
                        TableRow tr1 = new TableRow(LedgerReport.this);
                        tr1.setLayoutParams(new LayoutParams(
                                LayoutParams.FILL_PARENT,
                                LayoutParams.WRAP_CONTENT));

                        TextView b41 = new TextView(LedgerReport.this);
                        if(cd < 0)
                            cd=0;
                        String stimel = String.valueOf(cd);
                        b41.setText("Due:"+" ₹"+stimel);
                        b41.setTextColor(Color.DKGRAY);
                        b41.setTextSize(18);
                        tr1.addView(b41);
                        tv.addView(tr1);

                    }

                    else{
                        Toast.makeText(getApplicationContext(), "No transactions...Unable to make report", Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("exception:",e.getMessage());
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
                Map<String, String> params = new HashMap<>();


                params.put("clid",Integer.toString(li));

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        //parse json data


    }

}