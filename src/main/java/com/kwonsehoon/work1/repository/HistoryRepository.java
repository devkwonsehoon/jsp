package com.kwonsehoon.work1.repository;

import com.kwonsehoon.work1.dto.history.HistoryDto;
import com.kwonsehoon.work1.dto.history.HistoryResponseDto;
import com.kwonsehoon.work1.query.HistoryQueryBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryRepository {
    static final String URL ="jdbc:mysql://localhost/demo";
    static final HistoryRepository instance = new HistoryRepository();

    public static HistoryRepository getInstance() {
        return instance;
    }

    public void create(double x, double y) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            HistoryQueryBuilder historyQueryBuilder = new HistoryQueryBuilder(connection);
            statement = historyQueryBuilder.createHistoryStatement(x, y);

            int count = statement.executeUpdate();
            if (count == 0) System.out.println("업로드 할 데이터가 없거나 실패");
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

    public HistoryDto findOne(int historyId) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        HistoryDto history = new HistoryDto();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            HistoryQueryBuilder historyQueryBuilder = new HistoryQueryBuilder(connection);
            statement = historyQueryBuilder.findHistoryById(historyId);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                history.setId(rs.getInt("id"));
                history.setLat(rs.getDouble("lat"));
                history.setLnt(rs.getDouble("lnt"));
                history.setDate(rs.getTimestamp("date"));
                history.set_deleted(rs.getBoolean("is_deleted"));
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
        return history;
    }

    public List<HistoryDto> findAll() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        List<HistoryDto> histories = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            HistoryQueryBuilder historyQueryBuilder = new HistoryQueryBuilder(connection);
            statement = historyQueryBuilder.findAllHistoryStatement();

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                HistoryDto history = new HistoryDto();
                history.setId(rs.getInt("id"));
                history.setLat(rs.getDouble("lat"));
                history.setLnt(rs.getDouble("lnt"));
                history.setDate(rs.getTimestamp("date"));
                history.set_deleted(rs.getBoolean("is_deleted"));
                histories.add(history);
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
        return histories;
    }

    public void delete(int historyId) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            HistoryQueryBuilder historyQueryBuilder = new HistoryQueryBuilder(connection);
            statement = historyQueryBuilder.deleteHistoryStatement(historyId);

            int count = statement.executeUpdate();
            if (count == 0) System.out.println("삭제 할 데이터가 없거나 실패");
            else System.out.println("데이터 삭제 성공");
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
}
