package com.example.shopping;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopping.database.ShoppingDBHelper;
import com.example.shopping.enity.Goodsinfo;
import com.example.shopping.utils.ToastUtils;

import java.util.List;

public class ShoppingChannelActivity extends AppCompatActivity implements View.OnClickListener {
    private ShoppingChannelActivity shoppingChannelActivity;
    ShoppingDBHelper shoppingDBHelper;
    private GridLayout gl_channel;
    private TextView tv_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_channel);
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText("手机商城");
        tv_count = findViewById(R.id.tv_count);
        gl_channel = findViewById(R.id.gl_channel);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cart).setOnClickListener(this);

        shoppingDBHelper = ShoppingDBHelper.getInstance(this);
        shoppingDBHelper.openReadLink();
        shoppingDBHelper.openWriteLink();
        //从数据库里显示商品
        showGoods();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //查询购物车商品总数，并显示
        showCartsInfoTotal();
    }

    private void showCartsInfoTotal() {
        //得到购物车总数量
        int count=shoppingDBHelper.countCartinfo();
        MyApplication.getInstance().goodscount=count;
        tv_count.setText(String.valueOf(count));
    }

    private void showGoods() {
        //让商品占屏幕一半
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(widthPixels / 2, LinearLayout.LayoutParams.WRAP_CONTENT);

        List<Goodsinfo> goodsinfoList = shoppingDBHelper.queryallinfo();

        //移除下面的所有子视图-----添加之间先清空
        gl_channel.removeAllViews();
        for (Goodsinfo goodsinfo: goodsinfoList){
            //导入布局
            View view = LayoutInflater.from(this).inflate(R.layout.item_goods, null);
            ImageView iv_thumb = view.findViewById(R.id.iv_thumb);
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_price = view.findViewById(R.id.tv_price);

            Button btn_add  = view.findViewById(R.id.btn_add);


            iv_thumb.setImageURI(Uri.parse(goodsinfo.picpath));
            tv_name.setText(goodsinfo.name);
            tv_price.setText(String.valueOf((int) goodsinfo.price));
            btn_add.setOnClickListener(v->{
                addToCart(goodsinfo.id,goodsinfo.name);
            });

            iv_thumb.setOnClickListener(v->{
                Intent intent = new Intent(ShoppingChannelActivity.this, ShoppingDetailActivity.class);
                intent.putExtra("goods_id",goodsinfo.id);
                startActivity(intent);
            });
            gl_channel.addView(view,layoutParams);
        }


    }

    private void addToCart(int id, String name) {

        int count=++MyApplication.getInstance().goodscount;
        tv_count.setText(String.valueOf(count));
        shoppingDBHelper.insertCartsInfo(id);
        ToastUtils.show(this,"已添加"+name+"到购物车");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        shoppingDBHelper.closeLink();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_cart:
                //跳转购物车
                Intent intent = new Intent(this, ShoppingCartActivitty.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }


}