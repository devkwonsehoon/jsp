package com.kwonsehoon.work1.dto.wifi;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WifiResponseDto {

    @SerializedName("list_total_count")
    Integer total;

    @SerializedName("row")
    List<WifiDto> wifi;
}
