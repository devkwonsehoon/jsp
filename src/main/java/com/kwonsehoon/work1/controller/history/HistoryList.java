package com.kwonsehoon.work1.controller.history;

import com.kwonsehoon.work1.dto.history.HistoryResponseDto;
import com.kwonsehoon.work1.service.HistoryService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.kwonsehoon.work1.controller.common.CommonServlet.*;

@WebServlet(name = "historyList", urlPatterns = "/history")
public class HistoryList extends HttpServlet {

    HistoryService historyService = HistoryService.getInstance();

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] titles = {"ID", "X좌표", "Y좌표", "조회일자", "비고"};
        List<HistoryResponseDto> histories = historyService.findAllHistory();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        w.write(htmlStart("와이파이 정보 구하기", "위치 히스토리 목록"));
        w.write(htmlNavMenu());

        w.write("<table>\n<thead>");
        for (String title : titles) {
            w.write("<th>" + title + "</th>");
        }
        w.write("</thead>\n<tbody>");

        if (histories == null) {
            w.write("<td colspan=\"16\" style=\"text-align: center\">");
            w.write("위치 조회 기록이 존재하지 않습니다.");
            w.write("</td>");
        } else {
            for (HistoryResponseDto history : histories) {
                w.write("    <tr>");
                w.write("        <td>" + history.getId() + "</td>");
                w.write("        <td>" + history.getLat() + "</td>");
                w.write("        <td>" + history.getLnt() + "</td>");
                w.write("        <td>" + history.getDate() + "</td>");
                w.write("        <td style=\"text-align: center\">");
                w.write("        <form method=\"post\" action=\"delete-history\">");
                w.write("        <input type=\"hidden\" name=\"id\" value=\"" + history.getId() + "\">");
                w.write("        <button type=\"submit\">삭제</button>");
                w.write("        </form>");
                w.write("        </td>");
                w.write("    </tr>");
            }
        }
        w.write("</tbody>\n</table>");
        w.write(htmlEnd());
    }

}
