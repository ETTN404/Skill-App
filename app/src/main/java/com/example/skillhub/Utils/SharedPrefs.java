package com.example.skillhub.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefs {
    private static SharedPreferences preferences;
    private static int userId = 0;

    // Must call this before using other methods
    public static void init(Context context) {
        if(preferences == null){
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        String idString = preferences.getString("id", null);
        if (idString != null) {
            try {
                userId = Integer.parseInt(idString);
            } catch (NumberFormatException e) {
                userId = 0;
            }
        }
    }


    public static void setUserId(int Userid, Context context) {
        if(preferences == null){
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        userId = Userid;
        preferences.edit().putString("id", String.valueOf(userId)).apply();
    }

    public static int getUserId(Context context)
    {
        if(preferences == null){
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        init(context);
        return userId;
    }

    public static void deleteUserId(Context context) {
        if(preferences == null){
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        preferences.edit().remove("id").apply();
        userId = 0;
    }
}


//best to use getapplicataioncontext();
