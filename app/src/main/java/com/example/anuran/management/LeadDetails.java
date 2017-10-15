package com.example.anuran.management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LeadDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_details);
        TextView lid=(TextView)findViewById(R.id.lid);
        TextView nam=(TextView)findViewById(R.id.name);
        Bundle bundle = getIntent().getExtras();
        final String name=bundle.getString("name");
        final String mo1=bundle.getString("mo1");
        final String mo2=bundle.getString("mo2");
        final String mo3=bundle.getString("mo3");
        final String ename=bundle.getString("esname");
        final String land=bundle.getString("land");
        final String city=bundle.getString("city");
        final String district=bundle.getString("district");
        final int leadid=bundle.getInt("id");
        lid.setText(Integer.toString(100000+bundle.getInt("id")));
        nam.setText(bundle.getString("name"));

        Button conf=(Button)findViewById(R.id.confirm);

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ConfirmLater.class);
                Bundle bundle2 = new Bundle();
                bundle2.putInt("id", leadid);
                bundle2.putString("name",name);
                bundle2.putString("esname",ename);
                bundle2.putString("mo1",mo1);
                bundle2.putString("mo2",mo2);
                bundle2.putString("mo3",mo3);
                bundle2.putString("land",land);
                bundle2.putString("city",city);
                bundle2.putString("district",district);
                intent.putExtras(bundle2);
                startActivity(intent);
            }
        });


    }
}
