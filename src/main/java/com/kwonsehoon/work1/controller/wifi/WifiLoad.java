package com.kwonsehoon.work1.controller.wifi;

import com.kwonsehoon.work1.controller.common.CommonServlet;
import com.kwonsehoon.work1.dto.wifi.WifiResponseDto;
import com.kwonsehoon.work1.service.WifiService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static com.kwonsehoon.work1.controller.common.CommonServlet.htmlEnd;
import static com.kwonsehoon.work1.controller.common.CommonServlet.htmlStart;

@WebServlet(name = "wifiLoad", urlPatterns = "/wifi-load")
public class WifiLoad extends HttpServlet {
    WifiService wifiService = WifiService.getInstance();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WifiResponseDto wifiData = null;

        try {
            wifiData = wifiService.getWifiFromOpenAPI();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Integer total = wifiData.getTotal();


        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write(htmlStart("와이파이 정보 구하기", ""));

        w.write("<h1 style=\"text-align: center\">\n" +
                    total + "개의 WIFI 정보를 정상적으로 저장하였습니다.\n" +
                "  </h1>\n" +
                "\n" +
                "  <div style=\"text-align: center\">\n" +
                "      <a href=\"index.jsp\">홈으로 가기</a>\n" +
                "  </div>");

        w.write(htmlEnd());
    }

}
