package com.atguigu.test;

import com.atguigu.web.UserServlet;

import java.lang.reflect.Method;

public class UserServletTest {
    public static void main(String[] args) {
        String action = "login";

        try {
            // 获取action业务鉴别字符串，获取相应的业务方法反射对象
            Method method = UserServletTest.class.getDeclaredMethod(action);

            // 调用目标业务方法
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login() {
        System.out.println("这是login()方法调用了");
    }

    public void regist() {
        System.out.println("这是regist()方法调用了");
    }
}
