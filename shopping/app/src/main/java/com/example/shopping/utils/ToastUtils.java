package com.example.shopping.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    public  static void show(Context context,String desc){
        Toast.makeText(context,desc,Toast.LENGTH_SHORT).show();
    }
}
