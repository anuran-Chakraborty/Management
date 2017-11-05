package com.example.anuran.management;

/**
 * Created by ANURAN on 23-10-2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */

public class ProductAdapterIssueHistory extends RecyclerView.Adapter<ProductAdapterIssueHistory.ProductViewHolder> {


    private Context mCtx;
    private List<IssueDespHist> productList;

    public ProductAdapterIssueHistory(Context mCtx, List<IssueDespHist> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.row_layout_issue_history, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder viewItem, int position) {
        IssueDespHist product = productList.get(position);

        //loading the image

        viewItem.issueid.setText("Issue id: "+productList.get(position).issueid);
        viewItem.desp.setText(productList.get(position).description);
        viewItem.siteid.setText("Site id: "+productList.get(position).site_id);
        if(productList.get(position).status.compareTo("0")==0) {
            viewItem.status.setText("Status: Not Resolved");
            viewItem.status.setTextColor(Color.RED);
            viewItem.issue.setVisibility(View.INVISIBLE);
        }
        else{
            viewItem.issue.setVisibility(View.VISIBLE);
            viewItem.status.setText("Status: Resolved");
            viewItem.status.setTextColor(Color.GREEN);
            viewItem.issue.setId(Integer.parseInt(productList.get(position).issueid));
        }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView issueid;
        TextView desp;
        TextView siteid;
        TextView status;
        Button issue;

        public ProductViewHolder(View itemView) {
            super(itemView);

            issueid = (TextView)itemView.findViewById(R.id.issue_id);
            desp = (TextView)itemView.findViewById(R.id.desp);
            siteid = (TextView)itemView.findViewById(R.id.cid);
            status = (TextView)itemView.findViewById(R.id.status);
            issue = (Button)itemView.findViewById(R.id.issue);

            issue.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            Context con=v.getContext();
            Intent intent = new Intent(con,Remarks.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", issue.getId());
            intent.putExtras(bundle);
            con.startActivity(intent);
        }

    }
}
