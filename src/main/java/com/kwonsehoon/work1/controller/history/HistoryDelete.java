package com.kwonsehoon.work1.controller.history;

import com.kwonsehoon.work1.service.HistoryService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "historyDelete", urlPatterns = "/delete-history")
public class HistoryDelete extends HttpServlet {
    HistoryService historyService = HistoryService.getInstance();

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        historyService.deleteHistory(Integer.parseInt(id));
        response.sendRedirect(request.getContextPath() + "/history");
    }

}
