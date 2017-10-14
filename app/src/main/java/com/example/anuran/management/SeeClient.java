package com.example.anuran.management;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import static com.example.anuran.management.R.id.username;

public class SeeClient extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_client);
        //Toast.makeText(getApplicationContext(),"c1", Toast.LENGTH_LONG).show();
		/* Button button = (Button) findViewById(R.id.button1);
button.setOnClickListener(new View.OnClickListener()
{
@SuppressWarnings("deprecation")
public void onClick(View view)
{*/
//        String result = "";
//
//        Thread thread = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                try  {
//                    try {
//                        HttpClient httpclient = new DefaultHttpClient();
//                        HttpPost httppost = new HttpPost("http://supernovasoftsys.com/Android/v1/SeeClient.php");
//                        HttpResponse response = httpclient.execute(httppost);
//                        HttpEntity entity = response.getEntity();
//                        is = entity.getContent();
//
//                        Log.e("log_tag", "connection success");
//                        //   Toast.makeText(getApplicationContext(), "pass", Toast.LENGTH_SHORT).show();
//                    }
//                    catch (Exception e) {
//                        Log.e("log_tag", "Error in http connection" + e.toString());
//                        Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        thread.start();
//
//        //convert response to string
//        try {
//            Toast.makeText(getApplicationContext(), "c1", Toast.LENGTH_SHORT).show();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "ISO-8859-1"),8);
//            //Toast.makeText(getApplicationContext(), "c1", Toast.LENGTH_SHORT).show();
//            StringBuilder sb = new StringBuilder();
//            String line ="";
//            while ((line = reader.readLine()) != "") {
//                sb.append(line + "\n");
//                //  Toast.makeText(getApplicationContext(), "Input Reading pass", Toast.LENGTH_SHORT).show();
//            }
//            is.close();
//
//            result = sb.toString();
//        } catch (Exception e) {
//            Log.e("log_tag", "Error converting result" + e.toString());
//            Toast.makeText(getApplicationContext(), "Input reading fail", Toast.LENGTH_SHORT).show();
//
//        }

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_SEECLIENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),"gsgf", Toast.LENGTH_LONG).show();
                Log.e("log_tag", "fesfd");
                try {

                    JSONArray jArray = new JSONArray(response);
                    //Toast.makeText(getApplicationContext(),"g", Toast.LENGTH_LONG).show();
                    if(jArray.length()>0) {

                        TableLayout tv = (TableLayout) findViewById(R.id.table);
                        tv.removeAllViewsInLayout();
                        int flag = 1;
                        for (int i = -1; i < jArray.length(); i++) {
                            TableRow tr = new TableRow(SeeClient.this);
                            tr.setLayoutParams(new LayoutParams(
                                    LayoutParams.FILL_PARENT,
                                    LayoutParams.WRAP_CONTENT));
                            if (flag == 1) {
                                TextView b6 = new TextView(SeeClient.this);
                                b6.setText("Id");
                                b6.setTextColor(Color.BLUE);
                                b6.setTextSize(15);
                                tr.addView(b6);
                                TextView b19 = new TextView(SeeClient.this);
                                b19.setPadding(10, 0, 0, 0);
                                b19.setTextSize(15);
                                b19.setText("Name");
                                b19.setTextColor(Color.BLUE);
                                tr.addView(b19);
                                TextView b29 = new TextView(SeeClient.this);
                                b29.setPadding(10, 0, 0, 0);
                                b29.setText("Username");
                                b29.setTextColor(Color.BLUE);
                                b29.setTextSize(15);
                                tr.addView(b29);
                                tv.addView(tr);
                                final View vline = new View(SeeClient.this);
                                vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 2));
                                vline.setBackgroundColor(Color.BLUE);
                                tv.addView(vline);
                                flag = 0;
                            }
                            else {
                                JSONObject json_data = jArray.getJSONObject(i);
                                Log.i("log_tag", "id: " + json_data.getInt("id") + ", Username: " + json_data.getString("uname") + ", Name: " + json_data.getString("name"));
                                TextView b = new TextView(SeeClient.this);
                                String stime = String.valueOf(json_data.getInt("id"));

                                Toast.makeText(getApplicationContext(),stime, Toast.LENGTH_LONG).show();

                                b.setText(stime);
                                b.setTextColor(Color.RED);
                                b.setTextSize(15);
                                tr.addView(b);
                                TextView b1 = new TextView(SeeClient.this);
                                b1.setPadding(10, 0, 0, 0);
                                b1.setTextSize(15);
                                String stime1 = json_data.getString("uname");
                                b1.setText(stime1);
                                b1.setTextColor(Color.BLACK);
                                tr.addView(b1);
                                TextView b2 = new TextView(SeeClient.this);
                                b2.setPadding(10, 0, 0, 0);
                                String stime2 = json_data.getString("name");
                                b2.setText(stime2);
                                b2.setTextColor(Color.BLACK);
                                b2.setTextSize(15);
                                tr.addView(b2);
                                tv.addView(tr);
                                final View vline1 = new View(SeeClient.this);
                                vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
                                vline1.setBackgroundColor(Color.WHITE);
                                tv.addView(vline1);
                            }
                        }

                    }

                    else{
                        Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_LONG).show();
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
        });
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        //parse json data


    }

    //method to set the global int value
//    private void setMyInt(InputStream i){
//       is=i;
//
//    }

}