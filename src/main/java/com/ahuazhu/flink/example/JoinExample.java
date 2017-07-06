package com.ahuazhu.flink.example;

import com.ahuazhu.flink.config.Configure;
import com.ahuazhu.flink.config.JoinOnRule;
import com.ahuazhu.flink.config.MySqlDataSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.GroupReduceFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.operators.IterativeDataSet;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.types.Row;
import org.apache.flink.util.Collector;

import java.io.Serializable;
import java.util.*;

/**
 * Created by zhuzhengwen on 2017/7/4.
 */
public class JoinExample {

    public static void main(String[] args) throws Exception {
        Configure config = init();

        final List<MySqlDataSource> ds = config.getDataSourceList();

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        final DataSet<Row>[] dataSets = new DataSet[ds.size()];

        for (int i = 0; i < dataSets.length; i++) {
            DataSet<Row> dataSet = initDataSet(env, ds.get(i));
            dataSets[i] = dataSet;
        }

        List<Row> result = dataSets[0]
                .leftOuterJoin(dataSets[1])
                .where("fulfil_main_order_lg_order_code")
                .equalTo("lg_fulfil_order_lg_order_code")
                .with(new MyJoinFunction(ds.get(0).getFields(), ds.get(1).getFields()))
                .collect();

        String[] fields0 = ds.get(0).getFields();
        Class[] clazz0 = ds.get(0).getClazz();

        String[] fields1 = ds.get(1).getFields();
        Class[] clazz1 = ds.get(1).getClazz();

        String[] fields2 = ds.get(2).getFields();
        Class[] clazz2 = ds.get(2).getClazz();


        String[] fields = new String[fields0.length + fields1.length];
        Class[] clazz = new Class[clazz0.length + clazz1.length];

        for (int i = 0; i < fields0.length; i++) {
            fields[i] = fields0[i];
            clazz[i] = clazz0[i];
        }
        for (int i = 0; i < fields1.length; i++) {
            fields[i + fields0.length] = fields1[i];
            clazz[i + clazz0.length] = clazz1[i];
        }


        DataSet<Row> dataSet = env.fromCollection(result, new RowTypeInfo(toFlinkBasicType(clazz), fields));

        List<Row> finalList = dataSet.leftOuterJoin(dataSets[2])
                .where("fulfil_main_order_lg_order_code")
                .equalTo("fulfil_order_detail_lg_order_code")
                .with(new MyJoinFunction(fields, ds.get(2).getFields()))
                .collect();

        final String[] finalFields = new String[fields.length + fields2.length];
        Class[] finalClazz = new Class[clazz.length + clazz2.length];


        for (int i = 0; i < fields.length; i++) {
            finalFields[i] = fields[i];
            finalClazz[i] = clazz[i];
        }
        for (int i = 0; i < fields2.length; i++) {
            finalFields[i + fields.length] = fields2[i];
            finalClazz[i + clazz.length] = clazz2[i];
        }

        DataSet<Row> finalDataSet = env.fromCollection(finalList, new RowTypeInfo(toFlinkBasicType(finalClazz), finalFields));

        List<Record> recordList = finalDataSet.groupBy("fulfil_main_order_lg_order_code")
                .reduceGroup(new GroupReduceFunction<Row, Record>() {
                    @Override
                    public void reduce(Iterable<Row> values, Collector<Record> out) throws Exception {

                        Record record = new Record();

                        for (Row row : values) {
                            for (int i = 0; i < row.getArity(); i++) {
                                String fieldName = finalFields[i];
                                Object value = row.getField(i);
                                record.putValue(fieldName, value);
                            }
                        }

                        out.collect(record);

                    }
                })
                .collect();

        for (Record record : recordList) {
            System.out.println(record);
        }
    }

    private static class Record implements Serializable{

        private String keyValue;

        private String keyName;

        private Map<String, List<String>> fields = new HashMap<>();

        public String getKeyValue() {
            return keyValue;
        }

        public void setKeyValue(String keyValue) {
            this.keyValue = keyValue;
        }

        public String getKeyName() {
            return keyName;
        }

        public void setKeyName(String keyName) {
            this.keyName = keyName;
        }

        void putValue(String field, Object value) {
            List valueList = fields.get(field);
            if (valueList == null) {
                valueList = new ArrayList();
                fields.put(field, valueList);
            }
            valueList.add(value);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(1024);

            for (Map.Entry<String, List<String>> entry : fields.entrySet()) {
                sb.append(entry.getKey()).append(":")
                        .append("[")
                        .append(StringUtils.join(entry.getValue(), ","))
                        .append("]")
                        .append(",")
                        ;
            }
            return sb.toString();
        }
    }

    private static DataSet<Row> initDataSet(ExecutionEnvironment env, final MySqlDataSource dataSource) {

        JDBCInputFormat inputFormat = JDBCInputFormat.buildJDBCInputFormat()
                .setDrivername("com.mysql.jdbc.Driver")
                .setDBUrl("jdbc:MySQL://ahuazhu.com:3306/flink")
                .setUsername("flink")
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
                .jdbcUrl("jdbc:MySQL://ahuazhu.com:3306/flink")
                .userName("flink")
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
                .jdbcUrl("jdbc:MySQL://ahuazhu.com:3306/flink")
                .userName("flink")
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
                .jdbcUrl("jdbc:MySQL://ahuazhu.com:3306/flink")
                .userName("flink")
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

    static class MyMapFunction implements MapFunction<Row, Map>, Serializable {

        private final String[] fields;

        public MyMapFunction(String[] fields) {
            this.fields = fields;
        }

        @Override
        public Map map(Row row) throws Exception {
            Map map = new TreeMap();

            for (int i = 0; i < row.getArity(); i++) {
                map.put(fields[i], row.getField(i));
            }
            return map;
        }
    }
}
