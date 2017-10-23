package com.example.anuran.management;

/**
 * Created by ANURAN on 23-10-2017.
 */

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter
{
    Context context;
    List<SiteDesp> valueList;
    public ListAdapter(List<SiteDesp> listValue, Context context)
    {
        this.context = context;
        this.valueList = listValue;
    }

    @Override
    public int getCount()
    {
        return this.valueList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem viewItem = null;
        if(convertView == null)
        {
            viewItem = new ViewItem();
            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            //LayoutInflater layoutInfiater = LayoutInflater.from(context);
            convertView = layoutInfiater.inflate(R.layout.row_layout, null);

            viewItem.siteid = (TextView)convertView.findViewById(R.id.site_id);
            viewItem.site_name = (TextView)convertView.findViewById(R.id.site_name);
            viewItem.clientid = (TextView)convertView.findViewById(R.id.cid);
            viewItem.issue = (Button)convertView.findViewById(R.id.issue);
            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.siteid.setText("Site id: "+valueList.get(position).sid);
        viewItem.site_name.setText("Site name: "+valueList.get(position).sitename);
        viewItem.clientid.setText("Client id: "+valueList.get(position).client_id);
        viewItem.issue.setId(Integer.parseInt(valueList.get(position).client_id));
        return convertView;
    }
}

class ViewItem
{
    TextView siteid;
    TextView site_name;
    TextView clientid;
    Button issue;
}

