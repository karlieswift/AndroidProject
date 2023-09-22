package com.example.shopping.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareUtils {

    private static ShareUtils mUtil;
    private SharedPreferences preferences;
    public static ShareUtils getInstance(Context context){
        if(mUtil==null){
            mUtil=new ShareUtils();
            mUtil.preferences=context.getSharedPreferences("shopping",Context.MODE_PRIVATE);
        }
        return  mUtil;
    }

    public void writeBoolean(String key,boolean  value){
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key,value);
        edit.commit();
    }

    public Boolean readBoolean(String key,boolean  value){
        return preferences.getBoolean(key,value);
    }
}
