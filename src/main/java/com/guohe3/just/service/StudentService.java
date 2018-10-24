package com.guohe3.just.service;

import com.guohe3.just.DO.Score;

import java.io.IOException;
import java.util.List;

/**
 * @author 浦希成
 * 2018/10/24
 */
public interface StudentService {

     List<Score> getScoreAll(String username, String password) throws IOException;
}
