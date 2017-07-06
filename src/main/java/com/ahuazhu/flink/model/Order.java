package com.ahuazhu.flink.model;


import java.io.Serializable;

/**
 * Created by zhuzhengwen on 2017/7/5.
 */
public class Order implements  Serializable{

    public int userId;

    public int goodsId;

    public Order(int userId, int goodsId) {
        this.userId = userId;
        this.goodsId = goodsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

}
