package com.kwonsehoon.work1.query;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookmarkQueryBuilder {
    final Connection connection;

    public BookmarkQueryBuilder(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement findById(int bookmarkId) throws SQLException {
        String sql = "SELECT * FROM bookmark WHERE id = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, bookmarkId);
        return ps;
    }

    public PreparedStatement findAll() throws SQLException {
        String sql = "SELECT * FROM bookmark ORDER BY order_number;";
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps;
    }

    public PreparedStatement findOneBookmarkedWifiById(int id) throws SQLException {
        String sql = "SELECT wb.id id, b.name bookmark_name, w.name wifi_name, w.id wifi_id, wb.created_at created_at " +
                "FROM wifi_bookmark wb JOIN wifi w ON wb.wifi_id = w.id " +
                "JOIN bookmark b ON wb.bookmark_id = b.id " +
                "WHERE wb.id = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public PreparedStatement deleteBookmarkedWifi(int id) throws SQLException {
        String sql = "DELETE FROM wifi_bookmark WHERE id = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        return ps;
    }

    public PreparedStatement findAllBookmarkedWifi() throws SQLException {
        String sql = "SELECT wb.id id, b.name bookmark_name, w.name wifi_name, w.id wifi_id, wb.created_at created_at " +
                "FROM wifi_bookmark wb JOIN wifi w ON wb.wifi_id = w.id " +
                "JOIN bookmark b ON wb.bookmark_id = b.id " +
                "ORDER BY id";
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps;
    }

    public PreparedStatement create(String name, int order) throws SQLException {
        String sql = "INSERT INTO bookmark (name, order_number) " +
                "VALUES (?, ?);";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, order);
        return ps;
    }

    public PreparedStatement createWifiBookmark(String wifiId, int bookmarkId) throws SQLException {
        String sql = "INSERT INTO wifi_bookmark (wifi_id, bookmark_id) " +
                "VALUES (?, ?);";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, wifiId);
        ps.setInt(2, bookmarkId);
        return ps;
    }

    public PreparedStatement update(int bookmarkId, String name, int order) throws SQLException {
        String sql = "UPDATE bookmark SET name = ?, order_number = ?, updated_at = now() " +
                "WHERE id = ?;";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setInt(2, order);
        ps.setInt(3, bookmarkId);
        return ps;
    }

    public PreparedStatement delete(int bookmarkId) throws SQLException {
        String sql = "DELETE FROM bookmark WHERE id = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, bookmarkId);
        return ps;
    }
}
