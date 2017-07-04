package com.ahuazhu.fjoin.datasource;

import com.ahuazhu.fjoin.config.Configure;
import com.ahuazhu.fjoin.config.JoinOnRule;
import com.ahuazhu.fjoin.config.MySqlDataSource;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.table.api.GroupedTable;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.types.Row;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhuzhengwen on 2017/7/4.
 */
public class JoinExample {

    public static void main(String[] args) throws Exception {
        Configure config = init();

        List<MySqlDataSource> ds = config.getDataSourceList();

        Table[] tables = new Table[ds.size()];

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);

        int i = 0;
        for (MySqlDataSource dataSource : ds) {
            DataSet<Row> dataSet = initDataSet(env, dataSource);
            tableEnv.registerDataSet(dataSource.getName(), dataSet);
        }

        Table main = tableEnv.scan("fulfil_main_order");
        Table sub = tableEnv.scan("lg_fulfil_order");
        Table detail = tableEnv.scan("fulfil_order_detail");

        Table t = main.leftOuterJoin(sub, "fulfil_main_order_lg_order_code = lg_fulfil_order_lg_order_code");
        t = t.leftOuterJoin(detail, "fulfil_main_order_lg_order_code = fulfil_order_detail_lg_order_code");

        GroupedTable grouped = t.groupBy("fulfil_main_order_lg_order_code");

        List<Row> collect = tableEnv.toDataSet(grouped.table(), new RowTypeInfo()).collect();

        for (Row row : collect) {
            System.err.println(row);
        }
        System.out.println(collect);
    }

    private static DataSet<Row> initDataSet(ExecutionEnvironment env, MySqlDataSource dataSource) {

        JDBCInputFormat inputFormat = JDBCInputFormat.buildJDBCInputFormat()
                .setDrivername("com.mysql.jdbc.Driver")
                .setDBUrl(System.getProperty("jdbcUrl"))
                .setUsername(System.getProperty("userName"))
                .setPassword(System.getProperty("password"))
                .setQuery(dataSource.getSql())
                .setRowTypeInfo(new RowTypeInfo(toFlinkBasicType(dataSource.getClazz()), dataSource.getFields()))
                .finish();
        DataSet<Row> dbData = env.createInput(inputFormat);


        return dbData;
    }

    private static TypeInformation<?>[] toFlinkBasicType(Class<?>[] clazz) {
        TypeInformation[] fieldTypes = new TypeInformation[clazz.length];

        for (int i = 0; i < clazz.length; i++) {
            if (clazz[i] == String.class) {
                fieldTypes[i] = BasicTypeInfo.STRING_TYPE_INFO;
            } else if (clazz[i] == Integer.class) {
                fieldTypes[i] = BasicTypeInfo.INT_TYPE_INFO;
            }
        }

        return fieldTypes;
    }

    private static Configure init() {


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String[] mainFields = "lg_order_code, fulfil_order_code, order_source, features, cn_service".split(",");
        Class[] mainClazz = new Class[mainFields.length];
        for (int i = 0; i < mainFields.length; i++) {
            mainFields[i] = "fulfil_main_order_" + mainFields[i].trim();

            if (mainFields[i].contains("_status")
                    || mainFields[i].contains("_status")
                    || mainFields[i].contains("_type")
                    || mainFields[i].contains("_source")) {
                mainClazz[i] = Integer.class;
            } else {
                mainClazz[i] = String.class;
            }
        }

        MySqlDataSource mainDataSource = MySqlDataSource.builder()
                .name("fulfil_main_order")
                .jdbcUrl(System.getProperty("jdbcUrl"))
                .userName(System.getProperty("userName"))
                .password(System.getProperty("password"))
                .mainDataSource(true)
                .sql("SELECT lg_order_code, fulfil_order_code, order_source, features, cn_service FROM fulfil_main_order")
                .fields(mainFields)
                .clazz(mainClazz)
                .build();

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        String[] subFields = "lg_order_code, fulfil_status, mail_no".split(",");
        Class[] subClazz = new Class[subFields.length];
        for (int i = 0; i < subFields.length; i++) {
            subFields[i] = "lg_fulfil_order_" + subFields[i].trim();
            if (subFields[i].contains("_status")
                    || subFields[i].contains("_status")
                    || subFields[i].contains("_type")
                    || subFields[i].contains("_source")) {
                subClazz[i] = Integer.class;
            } else {
                subClazz[i] = String.class;
            }
        }

        MySqlDataSource subDataSource = MySqlDataSource.builder()
                .name("lg_fulfil_order")
                .jdbcUrl(System.getProperty("jdbcUrl"))
                .userName(System.getProperty("userName"))
                .password(System.getProperty("password"))
                .mainDataSource(false)
                .sql("SELECT lg_order_code, fulfil_status, mail_no FROM lg_fulfil_order")
                .fields(subFields)
                .clazz(subClazz)
                .build();

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String[] detailFields = "lg_order_code, out_task_code, node_type, event_type, service_code, resource_code, receiver_area_id".split(",");
        Class[] detailClazz = new Class[detailFields.length];
        for (int i = 0; i < detailFields.length; i++) {
            detailFields[i] = "fulfil_order_detail_" + detailFields[i].trim();
            if (detailFields[i].contains("_status")
                    || detailFields[i].contains("_status")
                    || detailFields[i].contains("_type")
                    || detailFields[i].contains("_source")
                    || detailFields[i].contains("_id")) {
                detailClazz[i] = Integer.class;
            } else {
                detailClazz[i] = String.class;
            }
        }

        MySqlDataSource detailDataSource = MySqlDataSource.builder()
                .name("fulfil_order_detail")
                .jdbcUrl(System.getProperty("jdbcUrl"))
                .userName(System.getProperty("userName"))
                .password(System.getProperty("password"))
                .mainDataSource(false)
                .sql("select lg_order_code, out_task_code, node_type, event_type, service_code, resource_code, receiver_area_id From fulfil_order_detail")
                .fields(detailFields)
                .clazz(detailClazz)
                .build();

        Configure configure = new Configure();
        configure.setDataSourceList(Arrays.asList(mainDataSource, subDataSource, detailDataSource));

        JoinOnRule rule1 = new JoinOnRule();
        rule1.setLeftDataSource("fulfil_main_order");
        rule1.setLeftField("lg_order_code");
        rule1.setRightDataSource("lg_fulfil_order");
        rule1.setRightField("lg_order_code");

        JoinOnRule rule2 = new JoinOnRule();
        rule2.setLeftDataSource("fulfil_main_order");
        rule2.setLeftField("lg_order_code");
        rule2.setRightDataSource("fulfil_order_detail");
        rule2.setRightField("lg_order_code");

        configure.setRules(Arrays.asList(rule1, rule2));
        return configure;
    }
}
