package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1, "java从入门到精通", 1,
                new BigDecimal(100), new BigDecimal(100)));

        System.out.println("订单号是：" + orderService.createOrder(cart, 1));
    }

    @Test
    public void queryAll(){
        System.out.println(orderService.queryOrders(1));
    }

    @Test
    public void queryAllOrderItems(){
        System.out.println(orderService.queryOrderItemsByOrderId("1"));
    }

    @Test
    public void queryAllOrder(){
        for (Order o:orderService.queryAll()) {
            System.out.println(o);
        }
    }
    @Test
    public void queryOrderByOrderId(){
        Order order = orderService.queryOrderByOrderId("1");
        System.out.println(order);
    }
}