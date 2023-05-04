package com.kwonsehoon.work1.dto.bookmark;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookmarkResponseDto {
    int id;
    String name;
    String wifiName;
    String wifiId;
    Timestamp createdAt;
}
