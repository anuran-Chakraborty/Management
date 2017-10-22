package com.example.anuran.management;

/**
 * Created by ANURAN on 09-10-2017.
 */

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Aditya on 4/16/2017.
 */

public class SharedPrefManagerClient {
    private static SharedPrefManagerClient mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME_CLIENT="mysharedpref14";
    private static final String KEY_USER_USERNAME_CLIENT="mysharedpref14";
    private static final String KEY_USER_PASS_CLIENT="userpasswordcl";
    private static final String KEY_USER_ID_CLIENT="userid3";
    private SharedPrefManagerClient(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManagerClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerClient(context);
        }
        return mInstance;
    }

    public boolean userLogin(String username)
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_CLIENT,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        //editor.putInt(KEY_USER_ID_CLIENT,id);
        //editor.putString(KEY_USER_PASS_CLIENT,pass);
        editor.putString(KEY_USER_USERNAME_CLIENT,username);
        editor.apply();
        return true;
    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_CLIENT,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_USERNAME_CLIENT,null)!=null){
            return true;}
        return false;
    }
    public boolean logout()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_CLIENT,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }
    public String getUsername()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_CLIENT,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_USERNAME_CLIENT,null);

    }
    public String getUserpass()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_CLIENT,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_PASS_CLIENT,null);

    }

}


