package com.example.anuran.management;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class RaiseIsssue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_isssue);

        Bundle bundle = getIntent().getExtras();
        final int siteidd = bundle.getInt("id");
        Toast.makeText(getApplicationContext(),Integer.toString(siteidd), Toast.LENGTH_LONG).show();
    }
}
