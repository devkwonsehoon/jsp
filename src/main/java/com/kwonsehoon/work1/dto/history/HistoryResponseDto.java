package com.kwonsehoon.work1.dto.history;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HistoryResponseDto {
    int id;
    double lat;
    double lnt;
    Timestamp date;
}
