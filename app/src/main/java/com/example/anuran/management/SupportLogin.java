package com.example.anuran.management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SupportLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_login);
        Button head = (Button) findViewById(R.id.head);
        Button support= (Button) findViewById(R.id.support);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(),HeadLogin.class);
                startActivity(i);
                //getActivity().setContentView(R.layout.activity_client_login);
            }
        });

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(),OsupportLogin.class);
                startActivity(i);
                //getActivity().setContentView(R.layout.activity_poc_login);
            }
        });

    }
}
