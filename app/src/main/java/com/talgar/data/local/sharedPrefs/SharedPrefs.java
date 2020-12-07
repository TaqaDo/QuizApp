package com.talgar.data.local.sharedPrefs;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private SharedPreferences sharedPreferences;
    public SharedPrefs(Context context) {
        sharedPreferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);
    }
    public int getTheme(){
        return sharedPreferences.getInt("isShown",0);
    }
    public void setTheme(int theme){
        sharedPreferences.edit().putInt("isShown",theme).apply();
    }
}