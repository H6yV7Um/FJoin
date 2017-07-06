package com.ahuazhu.flink.example;

import com.ahuazhu.flink.model.*;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.operators.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuzhengwen on 2017/7/5.
 */
public class JoinWithPojoExample {

    public static void main(String[] args) throws Exception {

        List<User> users = genUsers();
        List<Order> orders = genOrders();
        List<Goods> goodsList = genGoods();

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        DataSource<User> userDataSet = env.fromCollection(users);
        DataSource<Order> orderDataSet = env.fromCollection(orders);
        DataSource<Goods> goodsDataSet = env.fromCollection(goodsList);

        DataSet<UserOrder> userOrder = userDataSet.leftOuterJoin(orderDataSet)
                .where(new KeySelector<User, Integer>() {
                    @Override
                    public Integer getKey(User user) throws Exception {
                        return user.getUserId();
                    }
                })
                .equalTo(new KeySelector<Order, Integer>() {
                    @Override
                    public Integer getKey(Order order) throws Exception {
                        return order.getUserId();
                    }
                })
                .with(new JoinFunction<User, Order, UserOrder>() {
                    @Override
                    public UserOrder join(User user, Order order) throws Exception {
                        return new UserOrder(user.getUserId(), order == null ? -1 : order.getGoodsId());
                    }
                });

        List<UserGoods> userGoods = userOrder.leftOuterJoin(goodsDataSet)
                .where(new KeySelector<UserOrder, Integer>() {
                    @Override
                    public Integer getKey(UserOrder userOrder) throws Exception {
                        return userOrder.getGoodsId();
                    }
                })
                .equalTo(new KeySelector<Goods, Integer>() {
                    @Override
                    public Integer getKey(Goods goods) throws Exception {
                        return goods.getGoodsId();
                    }
                })
                .with(new JoinFunction<UserOrder, Goods, UserGoods>() {
                    @Override
                    public UserGoods join(UserOrder user, Goods goods) throws Exception {
                        return new UserGoods(user.getUserId(), "", user.getGoodsId(), goods == null ? "null" : goods.getGoodsName());
                    }
                })
                .collect();

        for (UserGoods userGood : userGoods) {

            System.out.println(userGood);
        }


    }

    private static List<Goods> genGoods() {
        List<Goods> goodsList = new ArrayList<>();
        for (int i = 0; i < 300; i ++) {
            goodsList.add(new Goods(i, "goods " + i));
        }
        return goodsList;
    }

    private static List<Order> genOrders() {
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < 100; i +=2) {
            Order order = new Order(i, i * 3);
            orders.add(order);
        }
        return orders;
    }

    private static List<User> genUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i <100; i ++) {
            users.add(new User(i, "user" + i));
        }
        return users;
    }
}
