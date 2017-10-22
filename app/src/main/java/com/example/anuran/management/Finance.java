package com.example.anuran.management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Finance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        Button logout=(Button)findViewById(R.id.logout);
        Button debit=(Button)findViewById(R.id.debit);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefManager.getInstance(Finance.this).logout();
                startActivity(new Intent(Finance.this,FinanceLogin.class));
                finish();
            }
        });

        debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Finance.this,CreditDebit.class);
                startActivity(intent);
            }
        });
    }
}
