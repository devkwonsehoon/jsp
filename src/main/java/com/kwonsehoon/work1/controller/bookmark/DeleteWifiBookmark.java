package com.kwonsehoon.work1.controller.bookmark;

import com.kwonsehoon.work1.service.BookmarkService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteWifiBookmark", urlPatterns = "/delete-wifi-bookmark")
public class DeleteWifiBookmark extends HttpServlet {
    BookmarkService bookmarkService = BookmarkService.getInstance();

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        bookmarkService.deleteWifiBookmark(Integer.parseInt(id));
        response.sendRedirect(request.getContextPath() + "/bookmark-list");
    }

}
