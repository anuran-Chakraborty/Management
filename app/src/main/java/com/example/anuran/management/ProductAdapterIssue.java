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
import android.widget.TextView;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */

public class ProductAdapterIssue extends RecyclerView.Adapter<ProductAdapterIssue.ProductViewHolder> {


    private Context mCtx;
    private List<IssueDesp> productList;

    public ProductAdapterIssue(Context mCtx, List<IssueDesp> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.row_layout_issue, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder viewItem, int position) {
        IssueDesp product = productList.get(position);

        //loading the image

        viewItem.issueid.setText("Issue id: "+productList.get(position).issueid);
        viewItem.desp.setText(productList.get(position).description);
        viewItem.clientid.setText("Client id: "+productList.get(position).client_id);
        viewItem.issue.setId(Integer.parseInt(productList.get(position).issueid));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView issueid;
        TextView desp;
        TextView clientid;
        Button issue;

        public ProductViewHolder(View itemView) {
            super(itemView);

            issueid = (TextView)itemView.findViewById(R.id.issue_id);
            desp = (TextView)itemView.findViewById(R.id.desp);
            clientid = (TextView)itemView.findViewById(R.id.cid);
            issue = (Button)itemView.findViewById(R.id.issue);

            issue.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            Context con=v.getContext();
            Intent intent = new Intent(con,IssueDetails.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", issue.getId());
            intent.putExtras(bundle);
            con.startActivity(intent);
        }

    }
}
