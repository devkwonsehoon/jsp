package com.kwonsehoon.work1.query;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryQueryBuilder {
    final Connection connection;

    public HistoryQueryBuilder(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement findHistoryById(int historyId) throws SQLException {
        String sql = "SELECT * FROM history WHERE id = ? AND is_deleted = false;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, historyId);
        return ps;
    }

    public PreparedStatement findAllHistoryStatement() throws SQLException {
        String sql = "SELECT * FROM history WHERE is_deleted = false ORDER BY id desc;";
        PreparedStatement ps = connection.prepareStatement(sql);
        return ps;
    }

    public PreparedStatement createHistoryStatement(double x, double y) throws SQLException {
        String sql = "INSERT INTO history (lat, lnt) " +
                "VALUES (?, ?);";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setDouble(1, x);
        ps.setDouble(2, y);
        return ps;
    }

    public PreparedStatement deleteHistoryStatement(int historyId) throws SQLException {
        String sql = "UPDATE history SET is_deleted = ? WHERE id = ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setBoolean(1, true);
        ps.setInt(2, historyId);
        return ps;
    }
}
