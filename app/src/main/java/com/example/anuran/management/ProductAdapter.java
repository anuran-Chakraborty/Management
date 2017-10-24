package com.example.anuran.management;

/**
 * Created by ANURAN on 23-10-2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<SiteDesp> productList;

    public ProductAdapter(Context mCtx, List<SiteDesp> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.row_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder viewItem, int position) {
        SiteDesp product = productList.get(position);

        //loading the image

        viewItem.siteid.setText("Site id: "+productList.get(position).sid);
        viewItem.site_name.setText("Site name: "+productList.get(position).sitename);
        viewItem.clientid.setText("Client id: "+productList.get(position).client_id);
        viewItem.issue.setId(Integer.parseInt(productList.get(position).sid));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView siteid;
        TextView site_name;
        TextView clientid;
        Button issue;

        public ProductViewHolder(View itemView) {
            super(itemView);

            siteid = (TextView)itemView.findViewById(R.id.site_id);
            site_name = (TextView)itemView.findViewById(R.id.site_name);
            clientid = (TextView)itemView.findViewById(R.id.cid);
            issue = (Button)itemView.findViewById(R.id.issue);

            issue.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            Context con=v.getContext();
            Intent intent = new Intent(con,RaiseIsssue.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", issue.getId());
            intent.putExtras(bundle);
            con.startActivity(intent);
        }

    }
}
