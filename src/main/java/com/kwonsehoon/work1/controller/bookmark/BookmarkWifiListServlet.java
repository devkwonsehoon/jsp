package com.kwonsehoon.work1.controller.bookmark;

import com.kwonsehoon.work1.dto.bookmark.BookmarkDto;
import com.kwonsehoon.work1.dto.bookmark.BookmarkResponseDto;
import com.kwonsehoon.work1.service.BookmarkService;
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

@WebServlet(name = "bookmarkWifiList", urlPatterns = "/bookmark-list")
public class BookmarkWifiListServlet extends HttpServlet {
    BookmarkService bookmarkService = BookmarkService.getInstance();

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] titles = {"ID", "북마크 이름", "와이파이명", "등록일자", "비고"};
        List<BookmarkResponseDto> bookmarkedWifi = bookmarkService.findAllBookmarkedWifi();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write(htmlStart("와이파이 정보 구하기", "북마크 목록"));
        w.write(htmlNavMenu());

        w.write("<table>\n<thead>");
        for (String title : titles) {
            w.write("<th>" + title + "</th>");
        }
        w.write("</thead>\n<tbody>");

        if (bookmarkedWifi == null) {
            w.write("<td colspan=\"16\" style=\"text-align: center\">");
            w.write("북마크 그룹이 존재하지 않습니다.");
            w.write("</td>");
        } else {
            for (BookmarkResponseDto bookmark : bookmarkedWifi) {
                w.write("    <tr>");
                w.write("        <td>" + bookmark.getId() + "</td>");
                w.write("        <td>" + bookmark.getName() + "</td>");
                w.write("        <td><a href=\"wifi?id=" + bookmark.getWifiId() + "&distance=" + 0.0 + "\">" + bookmark.getWifiName() + "</a></td>");
                w.write("        <td>" + bookmark.getCreatedAt() + "</td>");
                w.write("        <td style=\"text-align: center\">");
                w.write("           <a href=\"remove-wifi-bookmark?id=" + bookmark.getId() + "\">삭제</a>");
                w.write("        </td>");
                w.write("    </tr>");
            }
        }
        w.write("</tbody>\n</table>");

        w.write(htmlEnd());
    }

}
