package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 创建订单对象
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取用户编号
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        Integer userId = loginUser.getId();
        String orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId", orderId);

        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    protected void queryAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        int userId = loginUser.getId();
        List<Order> order = (List<Order>) orderService.queryOrders(userId);
        req.setAttribute("order", order);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    protected void queryOrderItems(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItem = (List<OrderItem>) orderService.queryOrderItemsByOrderId(orderId);
        System.out.println(orderItem);
        req.setAttribute("orderItem", orderItem);
        req.getRequestDispatcher("/pages/order/order_details.jsp").forward(req,resp);
    }


    protected void allOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = orderService.queryAll();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/managerUser/order_managerUser.jsp").forward(req,resp);
    }


    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        req.getSession().setAttribute("orderId", orderId);
        Order order = orderService.queryOrderByOrderId(orderId);
        orderService.sendOrder(orderId);
        req.getRequestDispatcher("orderServlet?action=allOrders").forward(req,resp);

    }

    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        User loginUser = (User) req.getSession().getAttribute("user");
        if (loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        int userId = loginUser.getId();
        Order order = orderService.queryOrderByOrderId(orderId);
        orderService.receiveOrder(orderId);
        req.getRequestDispatcher("orderServlet?action=queryAllOrders&userId=userId").forward(req,resp);
    }
}
