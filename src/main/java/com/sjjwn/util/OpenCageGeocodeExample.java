package com.sjjwn.util;

import cn.hutool.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OpenCageGeocodeExample {

    private static final String API_KEY = "7cc935c381cb4920abb753cc2036782d";  // 你的 OpenCage API 密钥

    public static String getAddressFromCoordinates(String latitude, String longitude) {
        try {
            String urlStr = "https://api.opencagedata.com/geocode/v1/json?q="
                    + latitude + "," + longitude + "&key=" + API_KEY;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 解析返回的 JSON 数据
            JSONObject jsonResponse = new JSONObject(response.toString());
            if (!jsonResponse.getJSONArray("results").isEmpty()) {
                    return jsonResponse.getJSONArray("results").getJSONObject(0).get("formatted").toString();
            } else {
                return "No address found";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred";
        }
    }
}
