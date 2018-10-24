package com.guohe3.just.craw;

import okhttp3.OkHttpClient;

import java.io.IOException;

/**
 * @author 浦希成 【pxc2955317305@outlook.com】
 * @date 2018/6/4  22:16
 */
public interface CrawService {
     /**
      * 通过vpn登录教务系统
      * @param username 教务系统用户名
      * @param password 教务系统密码
      * @return OkHttpClient
      * @throws IOException IOException
      */
     OkHttpClient justLoginVpn(String username, String password) throws IOException;
     /**
      * 通过普通方式登录教务系统
      * @param username 教务系统用户名
      * @param password 教务系统密码
      * @return OkHttpClient
      * @throws IOException IOException
      */
     OkHttpClient justLoginNormal(String username, String password) throws IOException;

     /**
      * 获取成绩页面
      * @param client 登陆后的OkHttpClient
      * @return html字符串
      *
      */
     String getScoreHtml(OkHttpClient client) throws IOException;
}
