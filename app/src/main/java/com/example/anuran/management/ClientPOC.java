package com.example.anuran.management;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClientPOC extends Fragment {


    public ClientPOC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_client_poc, container, false);
        Button client = (Button) rootView.findViewById(R.id.client);
        Button poc= (Button) rootView.findViewById(R.id.poc);
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(getActivity().getApplicationContext(),clientLogin.class);
                startActivity(i);
                //getActivity().setContentView(R.layout.activity_client_login);
            }
        });

        poc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(getActivity().getApplicationContext(),pocLogin.class);
                startActivity(i);
                //getActivity().setContentView(R.layout.activity_poc_login);
            }
        });

        return rootView;
    }

}
