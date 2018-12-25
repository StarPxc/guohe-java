package com.guohe3.just.common.template;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String modelUp = "Student";//类名
        String modelLow = modelUp.toLowerCase().toCharArray()[0] + modelUp.substring(1);
        //保存模版文件的根目录
        String root = "E:\\代码\\java_2018\\springbootDemo\\src\\main\\java\\test\\springboot\\common\\template\\";
        FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        //从跟目下加载文件
        Template t = gt.getTemplate("controller.txt");
        //将文件中的占位符赋值
        t.binding("modelUp", modelUp);
        t.binding("modelLow", modelLow);
        //保存到文件
        t.renderTo(new FileOutputStream(new File(modelUp + "Controller.java")));

    }
}
