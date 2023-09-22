package com.example.shopping.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shopping.enity.Cartsinfo;
import com.example.shopping.enity.Goodsinfo;

import java.util.ArrayList;
import java.util.List;

public class ShoppingDBHelper extends SQLiteOpenHelper {

    private static final  String DB_NAME="shopping.db";
    //商品信息
    private static final  String TABLE_GOODS_INFO="goods_info";
    private static final  String TABLE_CART_INFO="cart_info";
    private static final  int DB_VERSION=1;
    private static ShoppingDBHelper userDatabaseHelper= null;
    private SQLiteDatabase mRDE=null;
    private SQLiteDatabase mWDE=null;
    private ShoppingDBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    public static ShoppingDBHelper getInstance(Context context){
        if (userDatabaseHelper==null){
            userDatabaseHelper=new ShoppingDBHelper(context);
        }
        return userDatabaseHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //商品信息表
        String sql="CREATE TABLE IF NOT EXISTS "+ TABLE_GOODS_INFO +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                " name VARCHAR NOT NULL,"+
                " describle VARCHAR NOT NULL,"+
                " price FLOAT NOT NULL,"+
                " pic_path VARCHAR NOT NULL);";
        sqLiteDatabase.execSQL(sql);
        //购物车信息表
        sql="CREATE TABLE IF NOT EXISTS "+ TABLE_CART_INFO +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                " goods_id INTEGER NOT NULL,"+
                " count INTEGER NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }

    public SQLiteDatabase openReadLink(){
        if (mRDE==null || !mRDE.isOpen()){
            mRDE=userDatabaseHelper.getReadableDatabase();
        }
        return mRDE;
    }

    public SQLiteDatabase openWriteLink(){
        if (mWDE==null || !mWDE.isOpen()){
            mWDE=userDatabaseHelper.getWritableDatabase();
        }
        return mWDE;
    }

    public void closeLink(){
        if (mRDE!=null && mRDE.isOpen()){
            mRDE.close();
            mRDE=null;
        }
        if (mWDE!=null && mWDE.isOpen()){
            mWDE.close();
            mWDE=null;
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    //添加多条信息
    public void insertGoodsInfo(List<Goodsinfo> list){
        try {
            mWDE.beginTransaction();
            for (Goodsinfo info : list){
                ContentValues values = new ContentValues();
                values.put("name",info.name);
                values.put("describle",info.describle);
                values.put("price",info.price);
                values.put("pic_path",info.picpath);
                mWDE.insert(TABLE_GOODS_INFO,null,values);
            }
            mWDE.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mWDE.endTransaction();
        }
    }

    public List<Goodsinfo> queryallinfo(){
        String sql="select * from "+TABLE_GOODS_INFO;
        List<Goodsinfo> list=new ArrayList<>();
        Cursor cursor = mRDE.rawQuery(sql, null);
        while (cursor.moveToNext()){
            Goodsinfo goodsinfo =new Goodsinfo();
            goodsinfo.id=cursor.getInt(0);
            goodsinfo.name=cursor.getString(1);
            goodsinfo.describle=cursor.getString(2);
            goodsinfo.price=cursor.getFloat(3);
            goodsinfo.picpath=cursor.getString(4);
            list.add(goodsinfo);
        }
        cursor.close();
        return list;
    }

    public void insertCartsInfo(int goodsid) {
        Cartsinfo cartsinfo=queryCartInfoByGoodsId(goodsid);
        ContentValues values = new ContentValues();
        values.put("goods_id",goodsid);
        if (cartsinfo==null){
            values.put("count",1);
            mWDE.insert(TABLE_CART_INFO,null,values);
        }else {
            values.put("_id",cartsinfo.id);
            values.put("count",++cartsinfo.count);
            mWDE.update(TABLE_CART_INFO,values,"_id=?",new String[]{String.valueOf(cartsinfo.id)});
        }
    }

    private Cartsinfo queryCartInfoByGoodsId(int goodsid) {
        Cursor cursor = mRDE.query(TABLE_CART_INFO, null, "goods_id=?", new String[]{String.valueOf(goodsid)}, null, null, null);
        Cartsinfo cartsinfo=null;
        if(cursor.moveToNext()){
            cartsinfo=new Cartsinfo();
            cartsinfo.id=cursor.getInt(0);
            cartsinfo.goodsId=cursor.getInt(1);
            cartsinfo.count=cursor.getInt(2);
        }
        cursor.close();
    return cartsinfo;

    }

    public int countCartinfo() {
        int count=0;
        String sql="select sum(count) from "+TABLE_CART_INFO;
        Cursor cursor = mRDE.rawQuery(sql, null);
        if(cursor.moveToNext()){
            count=cursor.getInt(0);
        }
        cursor.close();

        return count;
    }

    public List<Cartsinfo> queryallCartinfo() {
        List<Cartsinfo> list = new ArrayList<>();
        Cursor cursor = mRDE.query(TABLE_CART_INFO, null, null, null, null, null, null);

        while (cursor.moveToNext()){
            Cartsinfo cartsinfo=new Cartsinfo();
            cartsinfo.id=cursor.getInt(0);
            cartsinfo.goodsId=cursor.getInt(1);
            cartsinfo.count=cursor.getInt(2);
            list.add(cartsinfo);
        }
        return list;
    }

    public Goodsinfo querygoodsinfoBy_id(int goods_id) {
        Goodsinfo goodsinfo=null;
        Cursor cursor = mRDE.query(TABLE_GOODS_INFO, null, "_id=?", new String[]{String.valueOf(goods_id)}, null, null, null);
        if(cursor.moveToNext()){
            goodsinfo=new Goodsinfo();
            goodsinfo.id=cursor.getInt(0);
            goodsinfo.name=cursor.getString(1);
            goodsinfo.describle=cursor.getString(2);
            goodsinfo.price=cursor.getFloat(3);
            goodsinfo.picpath=cursor.getString(4);
        }

        return goodsinfo;

    }

    //删除cart的指定商品
    public void deleteCartInfoByGoodsId(int goodsId) {
        mWDE.delete(TABLE_CART_INFO,"goods_id=?",new String[]{String.valueOf(goodsId)});
    }
    //删除cart的所有商品----清空
    public void deleteAllCartGoods() {
        mWDE.delete(TABLE_CART_INFO,"1=1",null);
    }
}
