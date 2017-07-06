package com.ahuazhu.flink.model;

import java.io.Serializable;

/**
 * Created by zhuzhengwen on 2017/7/5.
 */
public class User implements Serializable{

    public int userId;

    public String userName;

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
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

}
