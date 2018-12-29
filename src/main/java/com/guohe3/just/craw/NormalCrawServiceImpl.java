package com.guohe3.just.craw;

import com.guohe3.just.common.constants.Constants;
import com.guohe3.just.common.enums.ResultEnum;
import com.guohe3.just.common.execption.CustomException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 浦希成 【pxc2955317305@outlook.com】
 * @date 2018/6/4  22:17
 */
@Service
@Slf4j
public class NormalCrawServiceImpl implements CrawService {

    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            //管理cookie
            .cookieJar(new CookieJar() {
                private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

                @Override
                public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                    cookieStore.put(url.host(), cookies);
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl url) {
                    List<Cookie> cookies = cookieStore.get(url.host());
                    return cookies != null ? cookies : new ArrayList<>();
                }
            })
            .build();


    @Override
    public OkHttpClient login(String username, String password) throws IOException {
        OkHttpClient client = getClient();
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new CustomException(ResultEnum.OBJECT_NULL_ERROR);
        }
        FormBody.Builder params = new FormBody.Builder();

        params.add("USERNAME", username);
        params.add("PASSWORD", password);

        Request request = new Request.Builder()
                .post(params.build())
                .url(Constants.LOGIN_TO_JWGL_URL)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {

                String result = response.body().string();
                //login success
                if (result.contains("我的桌面")) {
                    log.info("登录成功，用户名：{}，密码：{}，时间：{}", username, password, LocalDateTime.now().toString());
                    return client;
                } else {
                    log.error("登录失败，用户名：{}，密码：{}，时间：{}", username, password, LocalDateTime.now().toString());
                    throw new CustomException(ResultEnum.JWC_ACCOUNT_ERROR);
                }
            } else {
                log.info("登录失败：{}", response.message());
                throw new CustomException(ResultEnum.LOGIN_FAIL);
            }
        } catch (SocketTimeoutException | UnknownHostException e) {
            log.info("教务处异常：{}", e);
            return null;
        }


    }

    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                //管理cookie
                .cookieJar(new CookieJar() {
                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url.host(), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<>();
                    }
                })
                .build();
    }

    @Override
    public String getSourceHtml(OkHttpClient client, String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {

            return response.body().string();
        } else {
            throw new CustomException(ResultEnum.REQUEST_ERROR);
        }

    }


}
