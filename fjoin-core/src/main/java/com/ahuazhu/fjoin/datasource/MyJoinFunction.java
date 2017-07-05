package com.ahuazhu.fjoin.datasource;

import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.types.Row;

import java.io.Serializable;

/**
 * Created by zhuzhengwen on 2017/7/5.
 */
public class MyJoinFunction implements Serializable, JoinFunction<Row, Row, Row> {


    private final String[] fields1;
    private final String[] fields2;

    public MyJoinFunction(String fields1[], String[] fields2) {
        this.fields1 = fields1;
        this.fields2 = fields2;
    }

    @Override
    public Row join(Row first, Row second) throws Exception {

        int firstLen = fields1.length;
        int secendLen = fields2.length;

        Row row = new Row(firstLen + secendLen);

        if (first != null) {
            for (int i = 0; i < firstLen; i++) {
                row.setField(i, first.getField(i));
            }
        }

        if (second != null) {
            for (int i = firstLen; i < firstLen + secendLen; i++) {
                row.setField(i, second.getField(i - firstLen));
            }
        }

        return row;
    }
}
