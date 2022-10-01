package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
    List<Order> queryOrders(Integer userId);
    List<OrderItem> queryOrderItemsByOrderId(String orderId);
    List<Order> queryAll();

    Order queryOrderByOrderId(String orderId);

    void sendOrder(String orderId);
    void receiveOrder(String orderId);
}
