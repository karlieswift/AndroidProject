package com.example.shopping;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import androidx.annotation.NonNull;


import com.example.shopping.database.ShoppingDBHelper;
import com.example.shopping.enity.Goodsinfo;
import com.example.shopping.utils.FileUtils;
import com.example.shopping.utils.ShareUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class MyApplication extends Application {

    private static MyApplication mApp;

    public HashMap<String,String> infoMap=new HashMap<>();
    //购物车商品的总数量
    public int goodscount;

    public static MyApplication getInstance(){
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp=this;

        //初始化商品
        initGoodsInfo();

    }

    private void initGoodsInfo() {
        Boolean isfirst = ShareUtils.getInstance(this).readBoolean("first", true);
        String directory=getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()+ File.separatorChar;
        if (isfirst){
            List<Goodsinfo> list=Goodsinfo.getDefaultList();
            for (Goodsinfo info:list){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), info.pic);
                String path=directory+info.id+".jpg";
                //向存储卡保存图片
                FileUtils.saveImage(path,bitmap);
                //回收
                bitmap.recycle();
                info.picpath=path;
            }
            ShoppingDBHelper shoppingDBHelper = ShoppingDBHelper.getInstance(this);
            shoppingDBHelper.openWriteLink();
            shoppingDBHelper.insertGoodsInfo(list);
            shoppingDBHelper.closeLink();

            //打开数据库，将信息加insert列表
            ShareUtils.getInstance(this).writeBoolean("first",false);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
