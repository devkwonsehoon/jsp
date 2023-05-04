<%@ page import="com.kwonsehoon.work1.service.WifiService" %>
<%@ page import="com.kwonsehoon.work1.dto.wifi.WifiListDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.kwonsehoon.work1.service.HistoryService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    HistoryService historyService = HistoryService.getInstance();
    WifiService wifiService = WifiService.getInstance();
    List<WifiListDto> wifiList = null;
    String[] titles = {"거리(Km)", "관리번호", "자치구", "와이파이명", "도로명주소", "상세주소", "설치위치(층)", "설치유형", "설치기관", "서비스구분", "설치년도", "실내외구분", "WIFI접속환경", "X좌표", "Y좌표", "작업일자"};

    String lat = request.getParameter("lat");
    String lnt = request.getParameter("lnt");
    if (lnt != null) {
        try {
            wifiList = wifiService.getWifi(Double.parseDouble(lat), Double.parseDouble(lnt));
            historyService.createHistory(Double.parseDouble(lat), Double.parseDouble(lnt));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
%>
<html>
    <head>
        <title>와이파이 정보 구하기</title>
        <link rel="stylesheet" href="./styles/index.css">
        <script src="script/library.js" type="text/javascript"></script>
    </head>
    <body>
        <header>
            <h1>와이파이 정보 구하기</h1>
        </header>

        <div>
            <a href="/">홈</a> | <a href="history">위치 히스토리 목록</a> | <a href="wifi-load">Open API 와이파이 정보 가져오기</a> | <a href="bookmark-list">북마크 보기</a> | <a href="bookmark">북마크 그룹 관리</a>
        </div>
        <div>
            <form action="/" method="get">
                <label for="lat">LAT: </label>
                <input id="lat" type="number" name="lat" step="any" value="0.0"> ,
                <label for="lnt">LNT: </label>
                <input id="lnt" type="number" name="lnt" step="any" value="0.0">
                <button type="button" onclick="getPosition()">내 위치 가져오기</button>
                <button type="submit">근처 WIFI 정보 보기</button>
            </form>
        </div>

        <table>
            <thead>
            <%
                for (String title : titles) {
                    out.write("<th>" + title + "</th>");
                }
            %>
            </thead>
            <tbody>
            <%
                if (wifiList == null) {
                    out.write("<td colspan=\"16\" style=\"text-align: center\">");
                    out.write("위치 정보를 입력한 후에 조회해 주세요.");
                    out.write("</td>");
                } else {
                    for (WifiListDto wifi : wifiList) {
                        String dist = String.format("%.4f", wifi.getDistance());
                        out.write("    <tr>");
                        out.write("        <td>" + dist + "</td>");
                        out.write("        <td>" + wifi.getId() + "</td>");
                        out.write("        <td>" + wifi.getDistrict() + "</td>");
                        out.write("        <td><a href=\"wifi?id=" + wifi.getId() + "&distance=" + dist + "\">" + wifi.getName() + "</a></td>");
                        out.write("        <td>" + wifi.getAddress() + "</td>");
                        out.write("        <td>" + wifi.getDetail_address() + "</td>");
                        out.write("        <td>" + wifi.getFloor() + "</td>");
                        out.write("        <td>" + wifi.getType() + "</td>");
                        out.write("        <td>" + wifi.getAgency() + "</td>");
                        out.write("        <td>" + wifi.getService() + "</td>");
                        out.write("        <td>" + wifi.getInstall_year() + "</td>");
                        out.write("        <td>" + wifi.getIndoor_outdoor() + "</td>");
                        out.write("        <td>" + wifi.getWifi_access() + "</td>");
                        out.write("        <td>" + wifi.getLat() + "</td>");
                        out.write("        <td>" + wifi.getLnt() + "</td>");
                        out.write("        <td>" + wifi.getDate() + "</td>");
                        out.write("    </tr>");
                    }
                }
            %>
            </tbody>
        </table>
    </body>
</html>