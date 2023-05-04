package com.kwonsehoon.work1.service;

import com.google.gson.Gson;
import com.kwonsehoon.work1.dto.wifi.OpenApiDto;
import com.kwonsehoon.work1.dto.wifi.WifiDto;
import com.kwonsehoon.work1.dto.wifi.WifiListDto;
import com.kwonsehoon.work1.dto.wifi.WifiResponseDto;
import com.kwonsehoon.work1.repository.WifiRepository;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor
public class WifiService {
    static final int ITEM = 1000;
    static final String BASE_URL = "http://openapi.seoul.go.kr:8088/4c7158444f7470673431514b4a5079/json/TbPublicWifiInfo/";

    WifiRepository wifiRepository = WifiRepository.getInstance();
    private static final WifiService instance = new WifiService();

    public static WifiService getInstance() {
        return instance;
    }

    public WifiResponseDto getWifiFromOpenAPI() throws IOException, SQLException, ClassNotFoundException {
        int start = 1;
        int end = start + ITEM - 1;

        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        WifiResponseDto data = null;

        String url = BASE_URL + start + "/" + end;
        data = parseWifiData(client, gson, data, url);
        int total = data.getTotal();

        while (start <= total) {
            url = BASE_URL + start + "/" + end;
            data = parseWifiData(client, gson, data, url);
            start = end + 1;
            end = start + ITEM - 1;
            if (end > total) end = total;
            wifiRepository.upsertWifi(data.getWifi());
        }

        return data;
    }

    public List<WifiListDto> getWifi(double x, double y) throws SQLException, ClassNotFoundException {
        List<WifiListDto> data = wifiRepository.findAllWifiByLatAndLnt(x, y);
        data.sort(Comparator.comparingDouble(WifiListDto::getDistance));
        return data;
    }

    public WifiDto getWifiById(String id) throws  SQLException, ClassNotFoundException {
        return wifiRepository.findWifiById(id);
    }

    private WifiResponseDto parseWifiData(OkHttpClient client, Gson gson, WifiResponseDto data, String url) throws IOException {
        Request.Builder builder = new Request.Builder().url(url).get();
        Request req = builder.build();
        Response res = client.newCall(req).execute();
        if (res.isSuccessful()) {
            ResponseBody body = res.body();
            if (body != null) data = gson.fromJson(body.string(), OpenApiDto.class).getData();
        }
        return data;
    }
}
