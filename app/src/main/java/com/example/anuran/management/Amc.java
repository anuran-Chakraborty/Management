package com.example.anuran.management;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Amc extends Fragment {
    public Amc() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_amc, container, false);
        Button confirm = (Button) rootView.findViewById(R.id.search);
        EditText clientid=(EditText)rootView.findViewById(R.id.cid);

        return rootView;
    }

}

