package com.example.anuran.management;

/**
 * Created by ANURAN on 23-10-2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.List;
import java.util.Map;

/**
 * Created by Belal on 10/18/2017.
 */

public class ProductAdapterSupport extends RecyclerView.Adapter<ProductAdapterSupport.ProductViewHolder> {


    private Context mCtx;
    private List<SupportDesp> productList;
    SharedPreferences sharedpreferences;

    public ProductAdapterSupport(Context mCtx, List<SupportDesp> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.row_layout_support_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder viewItem, int position) {
        SupportDesp product = productList.get(position);

        //loading the image

        viewItem.suppid.setText("Site id: "+productList.get(position).supid);
        viewItem.nam.setText("Site name: "+productList.get(position).nm);
        viewItem.assign.setId(Integer.parseInt(productList.get(position).supid));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView suppid;
        TextView nam;
        Button assign;

        public ProductViewHolder(View itemView) {
            super(itemView);

            suppid = (TextView)itemView.findViewById(R.id.supid);
            nam = (TextView)itemView.findViewById(R.id.name);
            assign = (Button)itemView.findViewById(R.id.assign);

            assign.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            Context con=v.getContext();

            Intent intent = new Intent(con,HeadSupport.class);
            //Bundle bundle = new Bundle();
            //bundle.putInt("id1", assign.getId());

            assignToSupport(v,Integer.toString(assign.getId()));
            Toast.makeText(con,"Issue successfully assigned to support id "+Integer.toString(assign.getId()), Toast.LENGTH_LONG).show();
            //intent.putExtras(bundle);
            con.startActivity(intent);
        }

    }

    public static void assignToSupport(View ve, final String support_id){
        final View v=ve;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_ISSUE_ASSIGN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj=new JSONObject(response);


                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(v.getContext(),"Exception", Toast.LENGTH_LONG).show();
                    Log.e("exception:",e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(v.getContext(),"Server error",Toast.LENGTH_LONG).show();
                error.printStackTrace();
                Log.e("VOLLEY", error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params= new HashMap<>();
                params.put("issueid",IssueDetails.iid);
                params.put("suppid",support_id);
                return params;
            }
        };
        RequestHandler.getInstance(v.getContext()).addToRequestQueue(stringRequest);
    }
}
