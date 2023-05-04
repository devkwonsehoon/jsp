package com.kwonsehoon.work1.dto.wifi;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WifiDto {
    @SerializedName("X_SWIFI_MGR_NO")
    String id;
    @SerializedName("X_SWIFI_WRDOFC")
    String district;
    @SerializedName("X_SWIFI_MAIN_NM")
    String name;
    @SerializedName("X_SWIFI_ADRES1")
    String address;
    @SerializedName("X_SWIFI_ADRES2")
    String detail_address;
    @SerializedName("X_SWIFI_INSTL_FLOOR")
    String floor;
    @SerializedName("X_SWIFI_INSTL_TY")
    String type;
    @SerializedName("X_SWIFI_INSTL_MBY")
    String agency;
    @SerializedName("X_SWIFI_SVC_SE")
    String service;
    @SerializedName("X_SWIFI_CMCWR")
    String network;
    @SerializedName("X_SWIFI_CNSTC_YEAR")
    String install_year;
    @SerializedName("X_SWIFI_INOUT_DOOR")
    String indoor_outdoor;
    @SerializedName("X_SWIFI_REMARS3")
    String wifi_access;
    @SerializedName("LAT")
    Double lat;
    @SerializedName("LNT")
    Double lnt;
    @SerializedName("WORK_DTTM")
    String date;
}
