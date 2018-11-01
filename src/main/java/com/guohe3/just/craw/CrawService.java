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
      * 登录成功后根据url获取相关页面
      * @param client OkHttpClient
      * @param url url
      * @return html页面字符串
      * @throws IOException
      */
     String getScoreHtml(OkHttpClient client,String url) throws IOException;
     /**
      * 获取学生信息页面
      * @param html html字符串
      * @return Student
      *
      */
     Student getStudentInfo(String html);

     /**
      * 登录教务处,默认先从justLoginNormal，失败再去调用justLoginVpn
      * @param username 教务系统用户名
      * @param password 教务系统密码
      * @return OkHttpClient
      */
     OkHttpClient login(String username,String password) throws IOException;
}
