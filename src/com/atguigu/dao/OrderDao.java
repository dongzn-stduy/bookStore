package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;

public interface OrderDao {
    public int saveOrder(Order order);
    List<Order> queryAllOrder(Integer userId);
    List<Order> queryAllOrders();

    Order queryOrderByOrderId(String orderId);

    void sendOrder(String orderId);
    void receiveOrder(String orderId);
}
