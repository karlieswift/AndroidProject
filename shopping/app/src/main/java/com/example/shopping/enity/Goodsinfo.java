package com.example.shopping.enity;

import com.example.shopping.R;

import java.util.ArrayList;

public class Goodsinfo {

    public int id;
    public String name;
    public String describle;
    public float price;
    //图片的保存路径
    public String picpath;
    //图片的资源编号
    public int pic;

    //声明一个手机商品的名称数组
    private static String[] mNameArray={
            "小米11","iphone13","荣耀70 Pro","OPPO11","Samsung GalaxyS22+","华为P60Pro","iphone13","荣耀70 Pro","iphone13","荣耀70 Pro"
    };
    //声明一个手机商品的描述
    private static String[] mDescArray={
            "小米11 5G手机 骁龙888 2K AMOLED四曲面柔性屏1亿像素 黑色【55W充电器套装】 8GB+256GB",
            "Apple/苹果 iPhone11 256GB 星光色 4G全网通  移动联通电信4G手机 双卡双待",
            "HONOR/荣耀70 Pro 5G智能手机行业首发IMX800三主摄 Vlog主角模式拍摄 天玑8000旗舰芯片 100W超级快充",
            "OPPO11 256GB 星光色 至尊版5G骁龙888",
            "Samsung GalaxyS22+ 星光色 至尊版5G骁龙888",
            "华为P60Pro 512GB 星光色 至尊版5G骁龙888",
            "Apple/苹果 iPhone11 256GB 星光色 4G全网通",
            "HONOR/荣耀70 Pro 5G智能手机行业首发IMX800三主摄 Vlog主角模式拍摄 天玑8000旗舰芯片 100W超级快充",
            "Apple/苹果 iPhone11 256GB 星光色 4G全网通",
            "HONOR/荣耀70 Pro 5G智能手机行业首发IMX800三主摄 Vlog主角模式拍摄 天玑8000旗舰芯片 100W超级快充"
    };
    //手机价格
    private static float[] mPriceArray={3299,4999,3999,4999,3666,8888,4999,3999,4999,3999};
    private static int[] mPicArray={
            R.drawable.xiaomi,
            R.drawable.iphone11,
            R.drawable.honr,
            R.drawable.oppo,
            R.drawable.samsumg,
            R.drawable.huawei,
            R.drawable.iphone11,
            R.drawable.honr,
            R.drawable.iphone11,
            R.drawable.honr
    };
    //获取手机信息列表
    public static ArrayList<Goodsinfo> getDefaultList(){
        ArrayList<Goodsinfo> goodsinfos =new ArrayList<>();

        for (int i=0;i<mNameArray.length;i++){
            Goodsinfo goodsinfo = new Goodsinfo();
            goodsinfo.id=i;
            goodsinfo.name=mNameArray[i];
            goodsinfo.describle=mDescArray[i];
            goodsinfo.price=mPriceArray[i];
            goodsinfo.pic=mPicArray[i];
            goodsinfos.add(goodsinfo);
        }


        return goodsinfos;
    }


}
