package com.example.anuran.management;

/**
 * Created by ANURAN on 09-10-2017.
 */

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Aditya on 4/16/2017.
 */

public class SharedPrefManagerFin {
    private static SharedPrefManagerFin mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME_FIN="mysharedpref13";
    private static final String KEY_USER_USERNAME_FIN="mysharedpref13";
    private static final String KEY_USER_PASS_FIN="userpasswordfin";
    private static final String KEY_USER_ID="userid2";
    private SharedPrefManagerFin(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManagerFin getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerFin(context);
        }
        return mInstance;
    }

    public boolean userLogin(String username)
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_FIN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        //editor.putInt(KEY_USER_ID,id);
        //editor.putString(KEY_USER_PASS_FIN,pass);
        editor.putString(KEY_USER_USERNAME_FIN,username);
        editor.apply();
        return true;
    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_FIN,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_USERNAME_FIN,null)!=null){
            return true;}
        return false;
    }
    public boolean logout()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_FIN,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
    public String getUsername()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_FIN,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_USERNAME_FIN,null);

    }
    public String getUserpass()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_FIN,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_PASS_FIN,null);

    }

}


