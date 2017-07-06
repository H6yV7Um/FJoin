package com.ahuazhu.flink.model;

import java.io.Serializable;

/**
 * Created by zhuzhengwen on 2017/7/5.
 */
public class Goods  implements Serializable{

    public int goodsId;

    public String goodsName;

    public Goods(int goodsId, String goodsName) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
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
}
