package com.kwonsehoon.work1.dto.bookmark;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookmarkDto {
    int id;
    String name;
    int order;
    Timestamp createdAt;
    Timestamp updatedAt;
}
