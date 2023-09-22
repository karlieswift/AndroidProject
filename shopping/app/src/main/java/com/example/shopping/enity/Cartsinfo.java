package com.example.shopping.enity;

public class Cartsinfo {
    public int id;
    public int goodsId;
    public int count;
    public Cartsinfo(){}

    public Cartsinfo(int id, int goodsId, int count) {
        this.id = id;
        this.goodsId = goodsId;
        this.count = count;
    }
}
