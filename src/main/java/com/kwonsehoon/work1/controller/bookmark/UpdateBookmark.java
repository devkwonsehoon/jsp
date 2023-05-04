package com.kwonsehoon.work1.controller.bookmark;

import com.kwonsehoon.work1.service.BookmarkService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "updateBookmark", urlPatterns = "/update-bookmark")
public class UpdateBookmark extends HttpServlet {

    BookmarkService bookmarkService = BookmarkService.getInstance();

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int order = Integer.parseInt(request.getParameter("order"));

        bookmarkService.updateBookmark(id, name, order);
        response.sendRedirect(request.getContextPath() + "/bookmark");
    }
}
