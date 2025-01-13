package com.sjjwn.util;

import cn.hutool.json.JSONObject;
import com.sjjwn.constant.CommonConstant;
import com.sjjwn.entity.IpLocation;
import com.sjjwn.entity.IpLocationPO;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class IpUtil {

    private static DbSearcher searcher;

    private static Method method;

    // 根据 IP 获取位置
    @SuppressWarnings("all")
    public static IpLocationPO getLocationByIp(String ip) throws Exception {
        String url = "http://ip-api.com/json/" + ip + "?lang=zh-CN";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        // 获取响应数据
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        // 解析 JSON 数据
        JSONObject jsonResponse = new JSONObject(response.toString());
        // 检查响应是否成功
        jsonResponse.getInt("status");
        IpLocation location = jsonResponse.toBean(IpLocation.class);
        String regionName = location.getRegionName();//省
        String city = location.getCity();//市
        String addressFromCoordinates = OpenCageGeocodeExample.getAddressFromCoordinates(location.getLat(), location.getLon());
        String[] split = addressFromCoordinates.split(",");
        int i = split.length - 1;
        String loadName = split[i];
        IpLocationPO ipLocationPO = IpLocationPO.builder()
                .province(regionName).city(city)
                .address(loadName).msg(addressFromCoordinates).build();
        return ipLocationPO;
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Real-IP");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("x-forwarded-for");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("getIpAddress exception:", e);
                }
                assert inet != null;
                ipAddress = inet.getHostAddress();
            }
        }
        return StringUtils.substringBefore(ipAddress, ",");
    }

    @PostConstruct
    private void initIp2regionResource() throws Exception {
        InputStream inputStream = new ClassPathResource("/ip/ip2region.db").getInputStream();
        byte[] dbBinStr = FileCopyUtils.copyToByteArray(inputStream);
        DbConfig dbConfig = new DbConfig();
        searcher = new DbSearcher(dbConfig, dbBinStr);
        method = searcher.getClass().getMethod("memorySearch", String.class);
    }

    public static String getIpSource(String ipAddress) {
        if (ipAddress == null || !Util.isIpAddress(ipAddress)) {
            log.error("Error: Invalid ip address");
            return "";
        }
        try {
            DataBlock dataBlock = (DataBlock) method.invoke(searcher, ipAddress);
            String ipInfo = dataBlock.getRegion();
            if (!StringUtils.isEmpty(ipInfo)) {
                ipInfo = ipInfo.replace("|0", "");
                ipInfo = ipInfo.replace("0|", "");
                return ipInfo;
            }
        } catch (Exception e) {
            log.error("getCityInfo exception:", e);
        }
        return "";
    }

    public static String getIpProvince(String ipSource) {
        if (StringUtils.isBlank(ipSource)) {
            return CommonConstant.UNKNOWN;
        }
        String[] strings = ipSource.split("\\|");
        if (strings.length > 1 && strings[1].endsWith("省")) {
            return StringUtils.substringBefore(strings[1], "省");
        }
        return strings[0];
    }

    public static UserAgent getUserAgent(HttpServletRequest request) {
        return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
    }

}
