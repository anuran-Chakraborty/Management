package com.example.anuran.management;

/**
 * Created by ANURAN on 09-10-2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

import static android.os.ParcelFileDescriptor.MODE_WORLD_READABLE;


/**
 * Created by Aditya on 4/16/2017.
 */

public class SharedPrefManagerClient {
    private static SharedPrefManagerClient mInstance;
    private SharedPreferences sharedPreferences;
    private static Context mCtx;
    private final static String SHARED_PREF_NAME_CLIENT="mysharedpref14";
    static String KEY_USER_USERNAME_CLIENT="mysharedpref17";
    private static String KEY_USER_PASS_CLIENT="userpasswordcl";
    private static String KEY_USER_ID_CLIENT="userid3";
    private static Editor editor;
    public SharedPrefManagerClient(Context context) {
        mCtx = context;
        sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_CLIENT,MODE_WORLD_READABLE);
        editor=sharedPreferences.edit();
        //editor.putInt(KEY_USER_ID_CLIENT,id);
        //editor.putString(KEY_USER_PASS_CLIENT,pass);

        editor.commit();
    }

    public static synchronized SharedPrefManagerClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerClient(context);
        }
        return mInstance;
    }

    public boolean userLogin(String username)
    {
        //editor.putInt(KEY_USER_ID_CLIENT,id);
        //editor.putString(KEY_USER_PASS_CLIENT,pass);
        editor.putString(KEY_USER_USERNAME_CLIENT,username);

        editor.commit();
        return true;
    }
    public boolean isLoggedIn(){
        sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_CLIENT,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USER_USERNAME_CLIENT,null)!=null){
            return true;}
        return false;
    }
    public boolean logout()
    {
        sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_CLIENT,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
        return true;
    }
    public HashMap<String, String> getUsername()
    {
        HashMap<String, String> user = new HashMap<String, String>();
        sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_CLIENT,MODE_WORLD_READABLE);
        user.put(KEY_USER_USERNAME_CLIENT, sharedPreferences.getString(KEY_USER_USERNAME_CLIENT, null));
        return user;

    }
    public String getUserpass()
    {
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME_CLIENT,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_PASS_CLIENT,null);

    }

}


