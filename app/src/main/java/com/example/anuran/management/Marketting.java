package com.example.anuran.management;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Marketting extends AppCompatActivity {

    private Button b1,b2,b3,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketting);
        if(!SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this,MarketLogin.class));
        }
        addListenerOnButton();

    }
    public void addListenerOnButton()
    {
        b1=(Button)(findViewById(R.id.button));
        b2=(Button)(findViewById(R.id.button2));
        b3=(Button)(findViewById(R.id.button3));
        logout=(Button) (findViewById(R.id.logout));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Marketting.this,Lead_Form.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(Marketting.this).logout();
                startActivity(new Intent(Marketting.this,MarketLogin.class));
                finish();
            }
        });
    }
        }



