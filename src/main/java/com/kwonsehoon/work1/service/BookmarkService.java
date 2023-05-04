package com.kwonsehoon.work1.service;

import com.kwonsehoon.work1.dto.bookmark.BookmarkDto;
import com.kwonsehoon.work1.dto.bookmark.BookmarkResponseDto;
import com.kwonsehoon.work1.repository.BookmarkRepository;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor
public class BookmarkService {
    BookmarkRepository bookmarkRepository = BookmarkRepository.getInstance();
    private static final BookmarkService instance = new BookmarkService();

    public static BookmarkService getInstance() {
        return instance;
    }

    public void createBookmark(String name, int order) throws SQLException, ClassNotFoundException {
        bookmarkRepository.create(name, order);
    }

    public void createWifiBookmark(String wifiId, int bookmarkId) throws SQLException, ClassNotFoundException {
        bookmarkRepository.createWifiBookmark(wifiId, bookmarkId);
    }

    public BookmarkDto findBookmarkById(int bookmarkId) throws SQLException, ClassNotFoundException {
        return bookmarkRepository.findOne(bookmarkId);
    }

    public BookmarkResponseDto findWifiBookmarkById(int id) throws SQLException, ClassNotFoundException {
        return bookmarkRepository.findOneWifiBookmark(id);
    }

    public List<BookmarkDto> findAllBookmark() throws SQLException, ClassNotFoundException {
        return bookmarkRepository.findAll();
    }

    public List<BookmarkResponseDto> findAllBookmarkedWifi() throws SQLException, ClassNotFoundException {
        return bookmarkRepository.findAllBookmarkedWifi();
    }

    public void updateBookmark(int bookmarkId, String name, int order) throws SQLException, ClassNotFoundException {
        BookmarkDto bookmark = bookmarkRepository.findOne(bookmarkId);
        if (bookmark != null) bookmarkRepository.update(bookmarkId, name, order);
        else System.out.println("존재하지 않는 bookmark id 입니다.");
    }

    public void deleteBookmark(int bookmarkId) throws SQLException, ClassNotFoundException {
        BookmarkDto bookmark = bookmarkRepository.findOne(bookmarkId);

        if (bookmark != null) bookmarkRepository.delete(bookmarkId);
        else System.out.println("존재하지 않거나 이미 삭제된 bookmark 입니다.");
    }

    public void deleteWifiBookmark(int id) throws SQLException, ClassNotFoundException {
        BookmarkResponseDto bookmark = bookmarkRepository.findOneWifiBookmark(id);

        if (bookmark != null) bookmarkRepository.deleteWifiBookmark(id);
        else System.out.println("존재하지 않거나 이미 삭제된 bookmark 입니다.");
    }
}
