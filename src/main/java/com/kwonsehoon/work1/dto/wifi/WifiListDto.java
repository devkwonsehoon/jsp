package com.kwonsehoon.work1.dto.wifi;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WifiListDto extends WifiDto{
    double distance;
}
