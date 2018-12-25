package com.guohe3.just.common.utils;

import lombok.Data;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 浦希成
 * 2018/10/31
 * 用户登录教务处后保存登录成功后的OkHttpClient
 */
@Component
public class ClientUtil {
    public static Map<String, OkHttpClient> clientMap = new ConcurrentHashMap<>();

    public void setClinet(String username, OkHttpClient client) {
        clientMap.put(username, client);
    }

    public OkHttpClient getClient(String username) {

        return clientMap.get(username);
    }
}
