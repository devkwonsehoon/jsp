package com.kwonsehoon.work1.controller.wifi;

import com.kwonsehoon.work1.controller.common.CommonServlet;
import com.kwonsehoon.work1.dto.bookmark.BookmarkDto;
import com.kwonsehoon.work1.dto.history.HistoryResponseDto;
import com.kwonsehoon.work1.dto.wifi.WifiDto;
import com.kwonsehoon.work1.service.BookmarkService;
import com.kwonsehoon.work1.service.WifiService;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import static com.kwonsehoon.work1.controller.common.CommonServlet.*;

@WebServlet(name = "wifiDetail", urlPatterns = "/wifi")
public class WifiDetail extends HttpServlet {
    WifiService wifiService = WifiService.getInstance();
    BookmarkService bookmarkService = BookmarkService.getInstance();

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] titles = {"거리(Km)", "관리번호", "자치구", "와이파이명", "도로명주소", "상세주소", "설치위치(층)", "설치유형", "설치기관", "서비스구분", "설치년도", "실내외구분", "WIFI접속환경", "X좌표", "Y좌표", "작업일자"};
        String id = request.getParameter("id");
        String dist = request.getParameter("distance");

        List<BookmarkDto> bookmarks = bookmarkService.findAllBookmark();
        WifiDto wifi = wifiService.getWifiById(id);

        String[] items = new String[]{
                dist,
                wifi.getId(),
                wifi.getDistrict(),
                wifi.getName(),
                wifi.getAddress(),
                wifi.getDetail_address(),
                wifi.getFloor(),
                wifi.getType(),
                wifi.getAgency(),
                wifi.getService(),
                wifi.getInstall_year(),
                wifi.getIndoor_outdoor(),
                wifi.getWifi_access(),
                String.valueOf(wifi.getLat()),
                String.valueOf(wifi.getLnt()),
                wifi.getDate()
        };

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write(htmlStart("와이파이 정보 구하기", "와이파이 정보 구하기"));
        w.write(htmlNavMenu());

        // 북마크 선택 및 추가하기 버튼
        w.write("<form method=\"post\" action=\"create-wifi-bookmark\">");
        w.write("   <input type=\"hidden\" name=\"wifi_id\" value=\"" + id + "\">");
        w.write("   <select name=\"bookmark_id\">");
        w.write("       <option value=\"\">북마크 그룹 이름 선택</option>");
        for (BookmarkDto bookmark : bookmarks) {
            w.write("   <option value=\"" + bookmark.getId() + "\">" + bookmark.getName() + "</option>");
        }
        w.write("   </select>");
        w.write("   <button type=\"submit\">북마크 추가하기</button>");
        w.write("</form>");

        w.write("<table>");
        for (int i = 0; i < titles.length; i++) {
            w.write("    <tr>");
            w.write("        <th>" + titles[i] + "</th>");
            w.write("        <td>" + items[i] + "</td>");
            w.write("    </tr>");
        }
        w.write("</table>");

        w.write(htmlEnd());
    }

}
