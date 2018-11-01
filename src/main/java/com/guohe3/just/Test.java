package com.guohe3.just;

import com.guohe3.just.common.constants.Constants;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 浦希成
 * 2018/11/1
 */
public class Test {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                //超时时间
                .connectTimeout(2, TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url.host(), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();

        Request request = new Request.Builder()
                .url("http://www.v2exdaas.com")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.isSuccessful());
        System.out.println(response.body().toString());
    }
}
