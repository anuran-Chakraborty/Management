package com.example.anuran.management;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

public class ViewAmc extends AppCompatActivity implements View.OnClickListener {

    TextView tv1,tv2,tv3;
    Button b1;
    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_amc);
        tv1=(TextView)findViewById(R.id.status1);
        tv2=(TextView)findViewById(R.id.start);
        tv3=(TextView)findViewById(R.id.expiry);
        b1=(Button)findViewById(R.id.newamc);
        Bundle bundle=getIntent().getExtras();
        String strt=bundle.getString("start");
        String status=bundle.getString("status");
        String end=bundle.getString("end");
        id=bundle.getString("id");
        if(status.equals("No"))
        {
            tv1.setText("STATUS* : AMC NEVER DONE BEFORE");
            tv1.setTextSize(18);
            tv1.setTextColor(Color.RED);
            tv2.setVisibility(View.INVISIBLE);
            tv3.setVisibility(View.INVISIBLE);
            b1.setVisibility(View.VISIBLE);

        }
        else
        {
            final String currentDate = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
          //  int slash1=end.indexOf("/");
            //Toast.makeText(getApplicationContext(), Integer.toString(slash1),Toast.LENGTH_LONG).show();
            int slash1=end.indexOf("/");
            int slash2=end.lastIndexOf("/");
            int dtend=Integer.parseInt(end.substring(slash1+1,slash2));
            int mend=Integer.parseInt(end.substring(0,slash1));
            int yrend=Integer.parseInt(end.substring(slash2+1));
           //Toast.makeText(getApplicationContext(), currentDate,Toast.LENGTH_LONG).show();
            int slash11=currentDate.indexOf("-");
            int slash22=currentDate.lastIndexOf("-");
            int dtendc=Integer.parseInt(currentDate.substring(slash11+1,slash22));
            int mendc=Integer.parseInt(currentDate.substring(0,slash11));
            int yrendc=Integer.parseInt(currentDate.substring(slash22+1));
            //Toast.makeText(getApplicationContext(),Integer.toString(mendc),Toast.LENGTH_LONG).show();
            int flag=0;
            if(yrendc > yrend)
                flag=1;
            else if(yrend==yrendc)
            {
                if(mendc > mend)
                    flag=1;
                else if(mendc==mend)
                {
                    if(dtendc > dtend)
                        flag=1;
                    else
                        flag=0;
                }

            }
            if (flag==0)
            {
                tv1.setText("STATUS* : RUNNING");
                tv1.setTextSize(18);
                tv1.setTextColor(Color.GREEN);
                tv2.setText("AMC START DATE(mm/dd/yyyy) : "+strt);
                tv2.setTextSize(18);
                tv2.setTextColor(Color.MAGENTA);
                tv3.setText("AMC END DATE(mm/dd/yyyy) : "+end);
                tv3.setTextSize(18);
                tv3.setTextColor(Color.MAGENTA);
                b1.setVisibility(View.INVISIBLE);

            }
            else if(flag == 1)
            {
                tv1.setText("STATUS* : EXPIRED");
                tv1.setTextSize(18);
                tv1.setTextColor(Color.RED);
                tv2.setText("AMC START DATE(mm/dd/yyyy) : "+strt);
                tv2.setTextSize(18);
                tv2.setTextColor(Color.MAGENTA);
                tv3.setText("AMC END DATE(mm/dd/yyyy) : "+end);
                tv3.setTextSize(18);
                tv3.setTextColor(Color.MAGENTA);
                b1.setVisibility(View.VISIBLE);
            }

        }
        b1.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(view==b1)
        {
         renew();
        }


    }
    private void renew()
    {
        Intent intent=new Intent(getApplicationContext(),RenewAmc.class);
        intent.putExtra("id",id);
        finish();
        startActivity(intent);

    }
}
