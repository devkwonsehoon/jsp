package com.kwonsehoon.work1.repository;

import com.kwonsehoon.work1.dto.bookmark.BookmarkDto;
import com.kwonsehoon.work1.dto.bookmark.BookmarkResponseDto;
import com.kwonsehoon.work1.dto.history.HistoryDto;
import com.kwonsehoon.work1.query.BookmarkQueryBuilder;
import com.kwonsehoon.work1.query.HistoryQueryBuilder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookmarkRepository {
    static final String URL ="jdbc:mysql://localhost/demo";
    static final BookmarkRepository instance = new BookmarkRepository();

    public static BookmarkRepository getInstance() {
        return instance;
    }

    public void create(String name, int order) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            BookmarkQueryBuilder bookmarkQueryBuilder = new BookmarkQueryBuilder(connection);
            statement = bookmarkQueryBuilder.create(name, order);

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

    public void createWifiBookmark(String wifiId, int bookmarkId) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            BookmarkQueryBuilder bookmarkQueryBuilder = new BookmarkQueryBuilder(connection);
            statement = bookmarkQueryBuilder.createWifiBookmark(wifiId, bookmarkId);

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

    public BookmarkDto findOne(int bookmarkId) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        BookmarkDto bookmark = new BookmarkDto();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            BookmarkQueryBuilder bookmarkQueryBuilder = new BookmarkQueryBuilder(connection);
            statement = bookmarkQueryBuilder.findById(bookmarkId);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                bookmark.setId(rs.getInt("id"));
                bookmark.setName(rs.getString("name"));
                bookmark.setOrder(rs.getInt("order_number"));
                bookmark.setCreatedAt(rs.getTimestamp("created_at"));
                bookmark.setUpdatedAt(rs.getTimestamp("updated_at"));
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
        return bookmark;
    }

    public BookmarkResponseDto findOneWifiBookmark(int id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        BookmarkResponseDto bookmark = new BookmarkResponseDto();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            BookmarkQueryBuilder bookmarkQueryBuilder = new BookmarkQueryBuilder(connection);
            statement = bookmarkQueryBuilder.findOneBookmarkedWifiById(id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                bookmark.setId(rs.getInt("id"));
                bookmark.setName(rs.getString("bookmark_name"));
                bookmark.setWifiName(rs.getString("wifi_name"));
                bookmark.setWifiId(rs.getString("wifi_id"));
                bookmark.setCreatedAt(rs.getTimestamp("created_at"));
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
        return bookmark;
    }

    public List<BookmarkResponseDto> findAllBookmarkedWifi() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        List<BookmarkResponseDto> bookmarks = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            BookmarkQueryBuilder bookmarkQueryBuilder = new BookmarkQueryBuilder(connection);
            statement = bookmarkQueryBuilder.findAllBookmarkedWifi();

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                BookmarkResponseDto bookmark = new BookmarkResponseDto();
                bookmark.setId(rs.getInt("id"));
                bookmark.setName(rs.getString("bookmark_name"));
                bookmark.setWifiName(rs.getString("wifi_name"));
                bookmark.setWifiId(rs.getString("wifi_id"));
                bookmark.setCreatedAt(rs.getTimestamp("created_at"));
                bookmarks.add(bookmark);
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
        return bookmarks;
    }

    public List<BookmarkDto> findAll() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        List<BookmarkDto> bookmarks = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            BookmarkQueryBuilder bookmarkQueryBuilder = new BookmarkQueryBuilder(connection);
            statement = bookmarkQueryBuilder.findAll();

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                BookmarkDto bookmark = new BookmarkDto();
                bookmark.setId(rs.getInt("id"));
                bookmark.setName(rs.getString("name"));
                bookmark.setOrder(rs.getInt("order_number"));
                bookmark.setCreatedAt(rs.getTimestamp("created_at"));
                bookmark.setUpdatedAt(rs.getTimestamp("updated_at"));
                bookmarks.add(bookmark);
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
        return bookmarks;
    }

    public void update(int bookmarkId, String name, int order) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            BookmarkQueryBuilder bookmarkQueryBuilder = new BookmarkQueryBuilder(connection);
            statement = bookmarkQueryBuilder.update(bookmarkId, name, order);

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

    public void delete(int bookmarkId) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            BookmarkQueryBuilder bookmarkQueryBuilder = new BookmarkQueryBuilder(connection);
            statement = bookmarkQueryBuilder.delete(bookmarkId);

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

    public void deleteWifiBookmark(int id) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, "kwonsehoon", "kwonsehoon");

            BookmarkQueryBuilder bookmarkQueryBuilder = new BookmarkQueryBuilder(connection);
            statement = bookmarkQueryBuilder.deleteBookmarkedWifi(id);

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
