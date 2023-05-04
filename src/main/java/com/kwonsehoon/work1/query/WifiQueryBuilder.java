package com.kwonsehoon.work1.query;

import com.kwonsehoon.work1.dto.wifi.WifiDto;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class WifiQueryBuilder {
    final Connection connection;

    public WifiQueryBuilder(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement upsertWifiStatement(List<WifiDto> wifiList) throws SQLException {
        String sql = "INSERT INTO wifi (id, district, name, address, detail_address, floor, type, agency, service, network, install_year, indoor_outdoor, wifi_access, lat, lnt, date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE district=?, name=?, address=?, detail_address=?, floor=?, type=?, agency=?, service=?, network=?, install_year=?, indoor_outdoor=?, wifi_access=?, lat=?, lnt=?, date=?;";

        PreparedStatement ps = connection.prepareStatement(sql);
        for (WifiDto wifi : wifiList) {
            ps.setString(1, wifi.getId());
            ps.setString(2, wifi.getDistrict());
            ps.setString(3, wifi.getName());
            ps.setString(4, wifi.getAddress());
            ps.setString(5, wifi.getDetail_address());
            ps.setString(6, wifi.getFloor());
            ps.setString(7, wifi.getType());
            ps.setString(8, wifi.getAgency());
            ps.setString(9, wifi.getService());
            ps.setString(10, wifi.getNetwork());
            ps.setString(11, wifi.getInstall_year());
            ps.setString(12, wifi.getIndoor_outdoor());
            ps.setString(13, wifi.getWifi_access());
            ps.setDouble(14, wifi.getLat());
            ps.setDouble(15, wifi.getLnt());
            ps.setString(16, wifi.getDate());
            ps.setString(17, wifi.getDistrict());
            ps.setString(18, wifi.getName());
            ps.setString(19, wifi.getAddress());
            ps.setString(20, wifi.getDetail_address());
            ps.setString(21, wifi.getFloor());
            ps.setString(22, wifi.getType());
            ps.setString(23, wifi.getAgency());
            ps.setString(24, wifi.getService());
            ps.setString(25, wifi.getNetwork());
            ps.setString(26, wifi.getInstall_year());
            ps.setString(27, wifi.getIndoor_outdoor());
            ps.setString(28, wifi.getWifi_access());
            ps.setDouble(29, wifi.getLat());
            ps.setDouble(30, wifi.getLnt());
            ps.setString(31, wifi.getDate());
            ps.addBatch();
        }
        return ps;
    }


    public PreparedStatement findAllWifi() throws SQLException {
        String sql = "SELECT * FROM wifi;";
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps;
    }

    public PreparedStatement findAllWifiByLatAndLnt(double x, double y) throws SQLException {
        double nx = x + 0.1, ny = y + 0.1;
        String sql = "SELECT * FROM wifi " +
                "WHERE lat between ? and ? " +
                "and lnt between ? and ? " +
                "LIMIT 20;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setDouble(1, y);
        ps.setDouble(2, ny);
        ps.setDouble(3, x);
        ps.setDouble(4, nx);
        return ps;
    }

    public PreparedStatement findWifiById(String id) throws SQLException {
        String sql = "SELECT * FROM wifi WHERE id = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        return ps;
    }
}
