package com.rifki.pustakav20;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Muhammad Rifqi on 19/06/2017.
 */

public class introManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;


    public introManager(Context context) {
        this.context =context;
        pref = context.getSharedPreferences("first",0);
        editor = pref.edit();

    }
    public void setFirst(boolean isFirst)
    {
        editor.putBoolean("check", isFirst);
        editor.commit();

    }
    public boolean Check()
    {
        return pref.getBoolean("check",true);

    }
}
