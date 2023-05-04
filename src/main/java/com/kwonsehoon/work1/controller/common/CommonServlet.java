package com.kwonsehoon.work1.controller.common;

public class CommonServlet {
    public static String htmlStart(String title, String header) {
        return "<html>\n" +
                "    <head>\n" +
                "        <title>" + title + "</title>\n" +
                "        <link rel=\"stylesheet\" href=\"./styles/index.css\">\n" +
                "        <script src=\"script/library.js\" type=\"text/javascript\"></script>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <header>\n" +
                "            <h1>" + header + "</h1>\n" +
                "        </header>";
    }

    public static String htmlEnd() {
        return "    </body>\n" +
                "</html>";
    }

    public static String htmlNavMenu() {
        return "<div>\n" +
                "<a href=\"/\">홈</a> | <a href=\"history\">위치 히스토리 목록</a> | <a href=\"wifi-load\">Open API 와이파이 정보 가져오기</a> | <a href=\"bookmark-list\">북마크 보기</a> | <a href=\"bookmark\">북마크 그룹 관리</a>" +
                "</div>";
    }
}
