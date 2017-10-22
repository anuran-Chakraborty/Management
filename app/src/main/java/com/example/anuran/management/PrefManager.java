package com.example.anuran.management;

/**
 * Created by ANURAN on 09-10-2017.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Aditya on 4/16/2017.
 */

public class  PrefManager{
    private static PrefManager mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF="mysharedpref13";
    private static final String USERNAME="mysharedpref13";
    private static final String PASS="userpass";
    private static final String KEY_USER_ID="uid";
    private PrefManager(Context context) {
        mCtx = context;

    }

    public static synchronized PrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PrefManager(context);
        }
        return mInstance;
    }

    public boolean userLogin(String username)
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        //editor.putInt(KEY_USER_ID,id);
        //editor.putString(KEY_USER_PASS,pass);
        editor.putString(USERNAME,username);
        editor.apply();
        return true;
    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(USERNAME,null)!=null){
            return true;}
        return false;
    }
    public boolean logout()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
    public String getUsername()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
        return sharedPreferences.getString(USERNAME,null);

    }
    public String getUserpass()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
        return sharedPreferences.getString(PASS,null);

    }

}


