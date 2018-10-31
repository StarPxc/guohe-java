package com.guohe3.just.craw;

import com.guohe3.just.DO.Student;
import com.guohe3.just.common.constants.Constants;
import com.guohe3.just.common.enums.ResultEnum;
import com.guohe3.just.common.execption.CustomException;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 浦希成 【pxc2955317305@outlook.com】
 * @date 2018/6/4  22:17
 */
@Service
public class CrawServiceImpl implements CrawService {


    @Override
    public OkHttpClient justLoginVpn(String username, String password) throws IOException {
        return null;

    }

    @Override
    public OkHttpClient justLoginNormal(String username, String password) throws IOException {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new CustomException(ResultEnum.OBJECT_NULL_ERROR);
        }
        OkHttpClient client = new OkHttpClient.Builder()
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

        FormBody.Builder params = new FormBody.Builder();

        params.add("USERNAME", username);
        params.add("PASSWORD", password);

        Request request = new Request.Builder()
                .post(params.build())
                .url(Constants.LOGIN_TO_JWGL_NORMAL)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {

            String result = response.body().string();
            //login success
            if (result.contains("我的桌面")) {
                return client;
            }
        } else {
            throw new CustomException(ResultEnum.REQUEST_ERROR);
        }

        return null;
    }

    @Override
    public String getScoreHtml(OkHttpClient client, String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {

            String result = response.body().string();

            return result;
        } else {
            throw new CustomException(ResultEnum.REQUEST_ERROR);
        }

    }

    @Override
    public Student getStudentInfo(OkHttpClient client) throws IOException {
        String html = getScoreHtml(client, Constants.STUDENT_INFO);
        Document doc = Jsoup.parse(html);
        Elements trs = doc.getElementById("xjkpTable").select("tr");
        String name = StringUtils.trim(trs.get(3).select("td").get(1).text());
        String birthday = StringUtils.trim(trs.get(4).select("td").get(1).text());
        String academy = trs.get(2).select("td").get(0).text().split("：")[1];
        String major = trs.get(2).select("td").get(1).text().split("：")[1];
        String classNum = trs.get(2).select("td").get(3).text().split("：")[1];
        Student student = new Student();
        student.setName(name);
        student.setBirthday(birthday);
        student.setMajor(major);
        student.setAcademy(academy);
        student.setClassNum(classNum);


        return student;
    }

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.parse(new File("/Users/pxc/Documents/java/guohe-java/src/main/java/com/guohe3/just/test.html"), "utf-8");
        Elements trs = doc.getElementById("xjkpTable").select("tr");
        String name = StringUtils.trim(trs.get(3).select("td").get(1).text());
        String birthday = StringUtils.trim(trs.get(4).select("td").get(1).text());


        Elements tds = trs.get(2).select("td");
        String academy = trs.get(2).select("td").get(0).text().split("：")[1];
        String major = trs.get(2).select("td").get(1).text().split("：")[1];
        String classNum = trs.get(2).select("td").get(3).text().split("：")[1];
        Student student = new Student();
        student.setName(name);
        student.setBirthday(birthday);
        student.setMajor(major);
        student.setAcademy(academy);
        student.setClassNum(classNum);



    }

}
