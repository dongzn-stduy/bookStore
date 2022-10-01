package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数，pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        // 调用BookService.page()方法，获取Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("managerUser/bookServlet?action=page");
        // 保存Page对象到Request域中
        req.setAttribute("page", page);
        // 链接到/pages/managerUser/book_managerUser.jsp页面
        req.getRequestDispatcher("/pages/managerUser/book_managerUser.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        // 获取请求的参数，封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 调用BookService.addBook()保存图书
        bookService.addBook(book);
        // 链接到图书列表页面
        // 会导致图书再次添加
        // req.getRequestDispatcher("/pages/managerUser/book_managerUser.jsp").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/managerUser/bookServlet?action=page&pageNo=" + pageNo);

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数，图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用BookService.deleteBookById()，删除图书
        bookService.deleteBookById(id);
        // 重定向回图书列表管理页面
        resp.sendRedirect(req.getContextPath() + "/managerUser/bookServlet?action=page&pageNo=" +
                req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数，封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        // 调用BookService.updateBook()方法修改图书
        bookService.updateBook(book);
        // 链接到图书列表管理页面
        // 地址：/工程名/managerUser/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/managerUser/bookServlet?action=page&pageNo=" +
                req.getParameter("pageNo"));
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        // 把全部图书保存到Request域中
        req.setAttribute("books", books);
        // 链接到/pages/manage/book_manage.jsp页面
        req.getRequestDispatcher("/pages/managerUser/book_managerUser.jsp").forward(req, resp);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数，图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用bookService.queryBookById()查询图书
        Book book = bookService.queryBookById(id);
        // 保存图书到Request域中
        req.setAttribute("book", book);
        // 链接到/pages/managerUser/book_edit.jsp页面
        req.getRequestDispatcher("/pages/managerUser/book_edit.jsp").forward(req, resp);
    }
}
