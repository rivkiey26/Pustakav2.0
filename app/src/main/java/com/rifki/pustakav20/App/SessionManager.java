package com.rifki.pustakav20.App;

/**
 * Created by Owners on 9/27/2016.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.View;

import com.rifki.pustakav20.LoginActivity;

public class SessionManager {
    //Logcat tag
    private static String TAG = SessionManager.class.getSimpleName();

    //Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    //Shared pref mode
    int PRIVATE_MODE = 0;

    //shared preferences file name
    private static final String PREF_NAME = "PustakaLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    private static final String KEY_UID = "dataUID";
    private static final String KEY_NAME = "dataNAME";
    private static final String KEY_EMAIL = "dataEMAIL";

    public SessionManager(Context context)
    {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

    }

    public void saveData(String uid, String name, String email){
        editor.putString(KEY_UID,uid);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_EMAIL,email);

        editor.commit();
        Log.d(TAG, "Data User saved");
    }

    public String getDataUID(){
        String uid;
        uid = pref.getString(KEY_UID,null);
        return uid;
    }
    public String getDataNAMA(){
        String uid;
        uid = pref.getString(KEY_NAME,null);
        return uid;
    }
    public String getDataEMAIL(){
        String uid;
        uid = pref.getString(KEY_EMAIL,null);
        return uid;
    }

    public void setLogin(boolean isLoggedIn)
    {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        //commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }
    public boolean isLoggedIn()
    {
        return pref.getBoolean(KEY_IS_LOGGEDIN,false);
    }

}
