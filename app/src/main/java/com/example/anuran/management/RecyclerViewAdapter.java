package com.example.anuran.management;

/**
 * Created by ANURAN on 05-11-2017.
 */

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import java.util.List;
import com.android.volley.toolbox.ImageLoader;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;

    List<DataAdapter> dataAdapters;

    ImageLoader imageLoader;

    public RecyclerViewAdapter(List<DataAdapter> getDataAdapter, Context context){

        super();
        this.dataAdapters = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_issue_details, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder Viewholder, int position) {

        DataAdapter dataAdapterOBJ =  dataAdapters.get(position);

        imageLoader = ImageAdapter.getInstance(context).getImageLoader();

        imageLoader.get(dataAdapterOBJ.getImageUrl(),
                ImageLoader.getImageListener(
                        Viewholder.VollyImageView,//Server Image
                        R.mipmap.ic_launcher,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );

        Viewholder.VollyImageView.setImageUrl(dataAdapterOBJ.getImageUrl(), imageLoader);

        //Viewholder.ImageTitleTextView.setText(dataAdapterOBJ.getImageTitle());

    }

    @Override
    public int getItemCount() {

        return dataAdapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        //public TextView ImageTitleTextView;
        public NetworkImageView VollyImageView ;

        public ViewHolder(View itemView) {

            super(itemView);

            //ImageTitleTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView) ;

            VollyImageView = (NetworkImageView) itemView.findViewById(R.id.iv);

        }
    }
}
