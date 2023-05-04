package com.kwonsehoon.work1.dto.wifi;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OpenApiDto {
    @SerializedName("TbPublicWifiInfo")
    WifiResponseDto data;
}
