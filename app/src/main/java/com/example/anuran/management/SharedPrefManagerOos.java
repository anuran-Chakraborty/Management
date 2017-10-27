package com.example.anuran.management;

/**
 * Created by ANURAN on 09-10-2017.
 */

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Aditya on 4/16/2017.
 */

public class SharedPrefManagerOos {
    private static SharedPrefManagerOos mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME_OS="mysharedpref16";
    private static final String KEY_USER_USERNAME_OS="mysharedpref17";
    private static final String KEY_USER_PASS_OS="userpasswordos";
    private static final String KEY_USER_ID="userid4";
    private SharedPrefManagerOos(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManagerOos getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerOos(context);
        }
        return mInstance;
    }

    public boolean userLogin(String username)
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_OS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        //editor.putInt(KEY_USER_ID,id);
        //editor.putString(KEY_USER_PASS_OS,pass);
        editor.putString(KEY_USER_USERNAME_OS,username);
        editor.apply();
        return true;
    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_OS,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_USERNAME_OS,null)!=null){
            return true;}
        return false;
    }
    public boolean logout()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_OS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
    public String getUsername()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_OS,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_USERNAME_OS,null);

    }
    public String getUserpass()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_OS,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_PASS_OS,null);

    }

}


