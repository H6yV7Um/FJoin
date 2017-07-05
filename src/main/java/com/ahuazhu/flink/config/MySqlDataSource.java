package com.ahuazhu.flink.config;

/**
 * Created by zhuzhengwen on 2017/7/4.
 */
public class MySqlDataSource {



    private String name;

    private String sql;

    private String userName;

    private String password;

    private String jdbcUrl;

    private boolean mainDataSource;

    private String[] fields;

    private Class<?>[] clazz;

    private MySqlDataSource() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public boolean isMainDataSource() {
        return mainDataSource;
    }

    public void setMainDataSource(boolean mainDataSource) {
        this.mainDataSource = mainDataSource;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public Class<?>[] getClazz() {
        return clazz;
    }

    public void setClazz(Class<?>[] clazz) {
        this.clazz = clazz;
    }

    public static MySqlDataSourceBuilder builder() {
        return new MySqlDataSourceBuilder();
    }

    public static class MySqlDataSourceBuilder {

        private String name;

        private String sql;

        private String userName;

        private String password;

        private String jdbcUrl;

        private boolean mainDataSource;

        private String[] fields;

        private Class[] clazz;

        public MySqlDataSourceBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MySqlDataSourceBuilder sql(String sql) {
            this.sql = sql;
            return this;
        }

        public MySqlDataSourceBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public MySqlDataSourceBuilder password(String password) {
            this.password = password;
            return this;
        }

        public MySqlDataSourceBuilder jdbcUrl(String jdbcUrl) {
            this.jdbcUrl = jdbcUrl;
            return this;
        }

        public MySqlDataSourceBuilder mainDataSource(boolean isMainDataSource) {
            this.mainDataSource = isMainDataSource;
            return this;
        }

        public MySqlDataSourceBuilder fields(String[] fields) {
            this.fields = fields;
            return this;
        }

        public MySqlDataSourceBuilder clazz(Class[] clazz) {
            this.clazz = clazz;
            return this;
        }

        public MySqlDataSource build() {
            MySqlDataSource mySqlDataSource = new MySqlDataSource();

            mySqlDataSource.setName(name);
            mySqlDataSource.setUserName(userName);
            mySqlDataSource.setPassword(password);
            mySqlDataSource.setJdbcUrl(jdbcUrl);
            mySqlDataSource.setSql(sql);
            mySqlDataSource.setMainDataSource(mainDataSource);
            mySqlDataSource.fields = fields;
            mySqlDataSource.clazz = clazz;

            return mySqlDataSource;
        }

    }

}
