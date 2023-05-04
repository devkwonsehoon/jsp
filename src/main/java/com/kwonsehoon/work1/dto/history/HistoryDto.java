package com.kwonsehoon.work1.dto.history;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryDto {
    int id;
    double lat;
    double lnt;
    Timestamp date;
    boolean is_deleted;
}
