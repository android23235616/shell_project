package com.example.tanmay.shell;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by PRANSHOO VERMA on 03-12-2016.
 */

public class Session {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context ctx;

    int PRIVATE_MODE=0;

    private static final String PREF_NAME="SLIDE_129";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public Session(Context context)
    {
        this.ctx=context;
        pref=ctx.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}

