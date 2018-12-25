package com.guohe3.just.craw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


/**
 * @author 浦希成
 * 2018/10/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawServiceImplTest {
    public static final String USERNAME = "152210702119";
    public static final String PASSWORD = "935377012pxc";
    @Autowired
    private CrawService crawService;

    @Test
    public void justLoginNormal() throws IOException {


    }
}