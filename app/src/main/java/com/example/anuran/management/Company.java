package com.example.anuran.management;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Company extends Fragment {


    public Company() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);
        Button market = (Button) rootView.findViewById(R.id.marketing);
        Button finance= (Button) rootView.findViewById(R.id.Finance);
        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(getActivity().getApplicationContext(),MarketLogin.class);
                startActivity(i);
                //getActivity().setContentView(R.layout.activity_market_login);
            }
        });

        finance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(getActivity().getApplicationContext(),FinanceLogin.class);
                startActivity(i);
                //getActivity().setContentView(R.layout.activity_finance_login);
            }
        });

        return rootView;
    }

}
