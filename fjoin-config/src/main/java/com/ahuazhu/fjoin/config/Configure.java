package com.ahuazhu.fjoin.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzhengwen on 2017/7/4.
 */
public class Configure {

    List<MySqlDataSource> dataSourceList;

    List<JoinOnRule> rules;



    public Configure() {
        dataSourceList = new ArrayList<>();
        rules = new ArrayList<>();
    }

    public List<MySqlDataSource> getDataSourceList() {
        return dataSourceList;
    }

    public void setDataSourceList(List<MySqlDataSource> dataSourceList) {
        this.dataSourceList = dataSourceList;
    }

    public List<JoinOnRule> getRules() {
        return rules;
    }

    public void setRules(List<JoinOnRule> rules) {
        this.rules = rules;
    }
}
