package com.ahuazhu.flink.model;


import java.util.List;

/**
 * Created by zhuzhengwen on 2017/7/5.
 */
public class UserOrder {

    private int userId;

    private int goodsId;

    public UserOrder(int userId, int goodsId) {
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
