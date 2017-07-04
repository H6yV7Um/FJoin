package com.ahuazhu.fjoin.config;

/**
 * Created by zhuzhengwen on 2017/7/4.
 */
public class JoinOnRule {

    private String leftDataSource;

    private String leftField;

    private String rightDataSource;

    private String rightField;

    private JoinCondition condition = JoinCondition.EQUAL;

    public String getLeftDataSource() {
        return leftDataSource;
    }

    public void setLeftDataSource(String leftDataSource) {
        this.leftDataSource = leftDataSource;
    }

    public String getLeftField() {
        return leftField;
    }

    public void setLeftField(String leftField) {
        this.leftField = leftField;
    }

    public String getRightDataSource() {
        return rightDataSource;
    }

    public void setRightDataSource(String rightDataSource) {
        this.rightDataSource = rightDataSource;
    }

    public String getRightField() {
        return rightField;
    }

    public void setRightField(String rightField) {
        this.rightField = rightField;
    }

    public JoinCondition getCondition() {
        return condition;
    }

    public void setCondition(JoinCondition condition) {
        this.condition = condition;
    }
}
