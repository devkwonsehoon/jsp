package com.kwonsehoon.work1.controller.bookmark;

import com.kwonsehoon.work1.dto.bookmark.BookmarkDto;
import com.kwonsehoon.work1.service.BookmarkService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.kwonsehoon.work1.controller.common.CommonServlet.htmlNavMenu;
import static com.kwonsehoon.work1.controller.common.CommonServlet.htmlStart;

@WebServlet(name = "bookmarkEdit", urlPatterns = "/edit-bookmark")
public class BookmarkEditServlet extends HttpServlet {

    BookmarkService bookmarkService = BookmarkService.getInstance();

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] titles = {"북마크 이름", "순서"};
        String id = request.getParameter("id");
        BookmarkDto bookmark = bookmarkService.findBookmarkById(Integer.parseInt(id));
        String[] inputs = new String[]{
                "<input id=\"name\" type=\"text\" name=\"name\" value=\"" + bookmark.getName() + "\">",
                "<input id=\"order\" type=\"number\" name=\"order\" value=\"" + bookmark.getOrder() + "\">"
        };

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write(htmlStart("와이파이 정보 구하기", "북마크 그룹 수정"));
        w.write(htmlNavMenu());

        w.write("<form method=\"patch\" action=\"update-bookmark\">");
        w.write("   <input type=\"hidden\" name=\"id\" value=\"" + id + "\">");
        w.write("<table>");
        for (int i = 0; i < titles.length; i++) {
            w.write("    <tr>");
            w.write("        <th>" + titles[i] + "</th>");
            w.write("        <td>" + inputs[i] + "</td>");
            w.write("    </tr>");
        }
        w.write("<tr><td colspan=\"2\" style=\"text-align: center\"><a href=\"bookmark\">돌아가기</a> | <button type=\"submit\">수정</button></td></tr>");
        w.write("</table>");
        w.write("</form>");
    }

}
