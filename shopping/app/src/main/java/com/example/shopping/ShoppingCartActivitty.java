package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopping.database.ShoppingDBHelper;
import com.example.shopping.enity.Cartsinfo;
import com.example.shopping.enity.Goodsinfo;
import com.example.shopping.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartActivitty extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_count;
    private TextView tv_sum;
    private TextView tv_total_price;
    private LinearLayout ll_cart;
    private ShoppingDBHelper shoppingDBHelper;
    private List<Cartsinfo> cartsinfoList;
    //把商品缓存起来不用每次查找数据库
    private Map<Integer, Goodsinfo> mGoodsMap=new HashMap<>();
    private LinearLayout ll_empty;
    private LinearLayout ll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart_activitty);
        TextView tv_title = findViewById(R.id.tv_title);
        ll_cart = findViewById(R.id.ll_cart);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_settle).setOnClickListener(this);
        findViewById(R.id.btn_shopping_channel).setOnClickListener(this);
        tv_title.setText("购物车");

        tv_count = findViewById(R.id.tv_count);
        ll_empty = findViewById(R.id.ll_empty);
        ll_content = findViewById(R.id.ll_content);
        tv_sum = findViewById(R.id.tv_sum);
        tv_total_price = findViewById(R.id.tv_total_price);
        tv_count.setText(String.valueOf(MyApplication.getInstance().goodscount));
        //cart的数据库
        shoppingDBHelper = ShoppingDBHelper.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showCarts();
    }

    private void showCarts() {
        //移除下面的所有子视图-----添加之间先清空
        ll_cart.removeAllViews();
        cartsinfoList = shoppingDBHelper.queryallCartinfo();
        //查数据
        if(cartsinfoList.size()==0){
            return;
        }
        for (Cartsinfo cartsinfo : cartsinfoList) {
            Goodsinfo goodsinfo = shoppingDBHelper.querygoodsinfoBy_id(cartsinfo.goodsId);
            mGoodsMap.put(cartsinfo.goodsId,goodsinfo);
            View view = LayoutInflater.from(this).inflate(R.layout.item_cart, null);

            ImageView iv_thumb = view.findViewById(R.id.iv_thumb);
            TextView tv_name = view.findViewById(R.id.tv_name);
            TextView tv_desc = view.findViewById(R.id.tv_desc);
            TextView tv_count = view.findViewById(R.id.tv_count);
            TextView tv_price = view.findViewById(R.id.tv_price);
            TextView tv_sum = view.findViewById(R.id.tv_sum);
            iv_thumb.setImageURI(Uri.parse(goodsinfo.picpath));
            tv_name.setText(goodsinfo.name);
            tv_desc.setText(goodsinfo.describle);
            tv_count.setText(String.valueOf(cartsinfo.count));
            tv_price.setText(String.valueOf((int)goodsinfo.price));
            tv_sum.setText(String.valueOf((int)(goodsinfo.price*cartsinfo.count)));


            //给商品添加长按事件，进行删除购物车商品
            view.setOnLongClickListener(v->{
                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivitty.this);
                builder.setMessage("是否从购物车删除"+goodsinfo.name+"?");
                builder.setPositiveButton("是",(dialog,which)->{
                    //移除当前视图
                    ll_cart.removeView(v);
                    //删除该商品
                    deleteGoods(cartsinfo);
                });
                builder.setNegativeButton("否",null);
                builder.create().show();
                return true;
            });
            //给商品添加点击事件，跳转到详细页面
            view.setOnClickListener(v->{
                Intent intent = new Intent(ShoppingCartActivitty.this, ShoppingDetailActivity.class);
                intent.putExtra("goods_id",goodsinfo.id);
                startActivity(intent);
            });

            //向购物车里加入商品
            ll_cart.addView(view);
        }



        //总金额
        refreshTotalPrice();



    }

    private void deleteGoods(Cartsinfo goodsinfo) {
        MyApplication.getInstance().goodscount-=goodsinfo.count;
        //从数据库购物车删除该商品
        shoppingDBHelper.deleteCartInfoByGoodsId(goodsinfo.goodsId);
        //同时需要清空list里面的商品
        Cartsinfo removed=null;
        for (Cartsinfo cartsinfo : cartsinfoList) {
            if (cartsinfo.goodsId==goodsinfo.goodsId){
                removed=cartsinfo;
                break;
            }
        }
        cartsinfoList.remove(removed);

        showCount();
        ToastUtils.show(this,"已从购物车删除"+mGoodsMap.get(goodsinfo.goodsId).name);
        mGoodsMap.remove(goodsinfo.goodsId);
        //刷新一下总金额
        refreshTotalPrice();

    }
    //显示购物车的数量
    private void showCount() {
        int temp_count=MyApplication.getInstance().goodscount;
        tv_count.setText(String.valueOf(temp_count));
        //判断购物车有没有商品
        if (temp_count==0){
            ll_empty.setVisibility(View.VISIBLE);
            ll_content.setVisibility(View.GONE);
            ll_cart.removeAllViews();
        }else {
            ll_content.setVisibility(View.VISIBLE);
            ll_empty.setVisibility(View.GONE);
        }

    }

    private void refreshTotalPrice() {
        int total=0;
        for (Cartsinfo cartsinfo : cartsinfoList) {
            Goodsinfo goodsinfo = mGoodsMap.get(cartsinfo.goodsId);
            total += goodsinfo.price * cartsinfo.count;
        }
        tv_total_price.setText(String.valueOf(total));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_shopping_channel:
                Intent intent = new Intent(this, ShoppingChannelActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.btn_settle:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("结算商品");
                builder.setMessage("您已经支付"+tv_total_price.getText().toString()+"元");
                builder.setPositiveButton("ok",null);
                builder.create().show();
                break;
            case R.id.btn_clear:
                shoppingDBHelper.deleteAllCartGoods();
                MyApplication.getInstance().goodscount=0;
                showCount();
                ToastUtils.show(this,"购物车已经清空");
                break;
        }
    }
}