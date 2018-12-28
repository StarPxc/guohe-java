package com.guohe3.just.craw;

import com.guohe3.just.DO.Student;
import okhttp3.OkHttpClient;

import java.io.IOException;

/**
 * @author 浦希成 【pxc2955317305@outlook.com】
 * @date 2018/6/4  22:16
 */
public interface CrawService {


    /**
     * 登录成功后根据url获取相关页面
     *
     * @param client OkHttpClient
     * @param url    url
     * @return html页面字符串
     * @throws IOException
     */
    String getSourceHtml(OkHttpClient client, String url) throws IOException;


    /**
     * 登录教务处
     *
     * @param username 教务系统用户名
     * @param password 教务系统密码
     * @return OkHttpClient
     * @throws IOException
     */
    OkHttpClient login(String username, String password) throws IOException;
}
