package com.kwonsehoon.work1.controller.bookmark;

import com.kwonsehoon.work1.service.BookmarkService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "createWifiBookmark", urlPatterns = "/create-wifi-bookmark")
public class CreateWifiBookmark extends HttpServlet {
    BookmarkService bookmarkService = BookmarkService.getInstance();

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wifiId = request.getParameter("wifi_id");
        int bookmarkId = Integer.parseInt(request.getParameter("bookmark_id"));

        bookmarkService.createWifiBookmark(wifiId, bookmarkId);
        response.sendRedirect(request.getContextPath() + "/bookmark-list");
    }
}
