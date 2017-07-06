package com.ahuazhu.flink.model;

/**
 * Created by zhuzhengwen on 2017/7/5.
 */
public class UserGoods {

    public int userId;

    public String userName;


    private int goodsId;

    private String goodsName;


    public UserGoods(int userId, String userName, int goodsId, String goodsName) {
        this.userId = userId;
        this.userName = userName;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String toString() {
        return String.format("%d\t%s\t%d\t%s", userId, userName, goodsId, goodsName);
    }
}
