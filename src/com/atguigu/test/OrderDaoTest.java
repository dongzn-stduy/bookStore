package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveDao() {
        orderDao.saveOrder(new Order("1", new Date(), new BigDecimal(100), 0, 1));
    }

    @Test
    public void queryAll(){
        System.out.println(orderDao.queryAllOrder(1));
    }

    @Test
    public void queryOrders(){
        for (Order o: orderDao.queryAllOrders()) {
            System.out.println(o);
        }
    }

    @Test
    public void queryOrderByOrderId(){
        Order order = orderDao.queryOrderByOrderId("1");
        System.out.println(order);
    }
}