package com.kwonsehoon.work1.repository;

import com.google.gson.internal.bind.util.ISO8601Utils;
import com.kwonsehoon.work1.dto.bookmark.BookmarkResponseDto;
import com.kwonsehoon.work1.dto.wifi.WifiDto;
import com.kwonsehoon.work1.dto.wifi.WifiListDto;
import com.kwonsehoon.work1.query.WifiQueryBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class WifiRepository {
    static final String URL ="jdbc:mysql://localhost/demo";
    static final WifiRepository instance = new WifiRepository();

    public static WifiRepository getInstance() {
        return instance;
    }

    public void upsertWifi(List<WifiDto> wifiList) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            WifiQueryBuilder wifiQueryBuilder = new WifiQueryBuilder(connection);
            statement = wifiQueryBuilder.upsertWifiStatement(wifiList);

            int[] count = statement.executeBatch();
            if (count.length == 0) System.out.println("업로드 할 데이터가 없거나 실패");
            else System.out.println("데이터 업로드 성공");

        } catch (SQLException error) {
            System.out.println(error.getMessage());
        } finally {
            if (statement != null && !statement.isClosed()) {
                try { statement.close(); }
                catch (SQLException e) { e.printStackTrace(); }
            }
            if (connection != null && !connection.isClosed()) {
                try { connection.close(); }
                catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }

    public List<WifiListDto> findAllWifiByLatAndLnt(double x, double y) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        List<WifiListDto> wifies = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");
            WifiQueryBuilder wifiQueryBuilder = new WifiQueryBuilder(connection);
            statement = wifiQueryBuilder.findAllWifiByLatAndLnt(x, y);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                WifiListDto wifi = new WifiListDto();
                wifi.setDistance(Math.sqrt(Math.pow(x - rs.getDouble("lnt"), 2) + Math.pow(y - rs.getDouble("lat"), 2)));
                wifi.setId(rs.getString("id"));
                wifi.setDistrict(rs.getString("district"));
                wifi.setName(rs.getString("name"));
                wifi.setAddress(rs.getString("address"));
                wifi.setDetail_address(rs.getString("detail_address"));
                wifi.setFloor(rs.getString("floor"));
                wifi.setType(rs.getString("type"));
                wifi.setAgency(rs.getString("agency"));
                wifi.setService(rs.getString("service"));
                wifi.setNetwork(rs.getString("network"));
                wifi.setInstall_year(rs.getString("install_year"));
                wifi.setIndoor_outdoor(rs.getString("indoor_outdoor"));
                wifi.setWifi_access(rs.getString("wifi_access"));
                wifi.setLat(rs.getDouble("lat"));
                wifi.setLnt(rs.getDouble("lnt"));
                wifi.setDate(rs.getString("date"));
                wifies.add(wifi);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        } finally {
            if (statement != null && !statement.isClosed()) {
                try { statement.close(); }
                catch (SQLException e) { e.printStackTrace(); }
            }
            if (connection != null && !connection.isClosed()) {
                try { connection.close(); }
                catch (SQLException e) { e.printStackTrace(); }
            }
        }
        return wifies;
    }

    public WifiDto findWifiById(String id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        WifiDto wifi = new WifiDto();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            WifiQueryBuilder wifiQueryBuilder = new WifiQueryBuilder(connection);
            statement = wifiQueryBuilder.findWifiById(id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                wifi.setId(rs.getString("id"));
                wifi.setDistrict(rs.getString("district"));
                wifi.setName(rs.getString("name"));
                wifi.setAddress(rs.getString("address"));
                wifi.setDetail_address(rs.getString("detail_address"));
                wifi.setFloor(rs.getString("floor"));
                wifi.setType(rs.getString("type"));
                wifi.setAgency(rs.getString("agency"));
                wifi.setService(rs.getString("service"));
                wifi.setNetwork(rs.getString("network"));
                wifi.setInstall_year(rs.getString("install_year"));
                wifi.setIndoor_outdoor(rs.getString("indoor_outdoor"));
                wifi.setWifi_access(rs.getString("wifi_access"));
                wifi.setLat(rs.getDouble("lat"));
                wifi.setLnt(rs.getDouble("lnt"));
                wifi.setDate(rs.getString("date"));
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        } finally {
            if (statement != null && !statement.isClosed()) {
                try { statement.close(); }
                catch (SQLException e) { e.printStackTrace(); }
            }
            if (connection != null && !connection.isClosed()) {
                try { connection.close(); }
                catch (SQLException e) { e.printStackTrace(); }
            }
        }
        return wifi;
    }
}
