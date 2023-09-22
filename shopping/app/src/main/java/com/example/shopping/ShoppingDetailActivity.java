package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopping.database.ShoppingDBHelper;
import com.example.shopping.enity.Goodsinfo;
import com.example.shopping.utils.ToastUtils;

import org.w3c.dom.Text;

public class ShoppingDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_title;
    private TextView tv_count;
    private TextView tv_goods_detail;
    private TextView tv_goods_price;
    private ImageView iv_goods_thumb;
    private ShoppingDBHelper dbHelper;
    private int goods_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_detail);
        tv_title = findViewById(R.id.tv_title);
        tv_count=findViewById(R.id.tv_count);
        tv_goods_detail=findViewById(R.id.tv_goods_detail);
        tv_goods_price=findViewById(R.id.tv_goods_price);
        iv_goods_thumb=findViewById(R.id.iv_goods_thumb);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cart).setOnClickListener(this);
        findViewById(R.id.btn_add_cart).setOnClickListener(this);

        tv_count.setText(String.valueOf(MyApplication.getInstance().goodscount));
        dbHelper = ShoppingDBHelper.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showDetailInfo();
    }

    private void showDetailInfo() {
        //获取商品的信息
        goods_id = getIntent().getIntExtra("goods_id", 0);
        if(goods_id >0){
            Goodsinfo goodsinfo = dbHelper.querygoodsinfoBy_id(goods_id);
            tv_title.setText((goodsinfo.name));
            tv_goods_detail.setText((goodsinfo.describle));
            tv_goods_price.setText(String.valueOf(goodsinfo.price));
            iv_goods_thumb.setImageURI(Uri.parse(goodsinfo.picpath));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_add_cart:
                addToCart(goods_id);
                break;
            case R.id.iv_cart:
                Intent intent = new Intent(this, ShoppingCartActivitty.class);
                startActivity(intent);
                break;
        }
    }

    private void addToCart(int goods_id) {
        int count=++MyApplication.getInstance().goodscount;
        tv_count.setText(String.valueOf(count));
        dbHelper.insertCartsInfo(goods_id);
        ToastUtils.show(this,"已经成功添加到购物车");
    }
}