package com.guohe3.just.service.impl;

import com.guohe3.just.DO.Score;
import com.guohe3.just.DO.Student;
import com.guohe3.just.common.constants.Constants;
import com.guohe3.just.common.enums.ResultEnum;
import com.guohe3.just.common.execption.CustomException;
import com.guohe3.just.common.utils.ClientUtil;
import com.guohe3.just.craw.CrawService;
import com.guohe3.just.mapper.StudentMapper;
import com.guohe3.just.service.StudentService;
import com.sun.xml.internal.bind.v2.TODO;
import javafx.application.Application;
import okhttp3.OkHttpClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 浦希成
 * 2018/10/24
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private CrawService crawService;
    @Autowired
    private StudentMapper mapper;

    @Override
    public List<Score> getScoreAll(String username, String password) throws IOException {
        //重新登录
        OkHttpClient client = crawService.login(username, password);
        if (client == null) {
            throw new CustomException(ResultEnum.LOGIN_FAIL);
        }
        String result = crawService.getScoreHtml(client, Constants.SCORE_NORMAL);
        Document doc = Jsoup.parse(result);
        Element table = doc.getElementById("dataList");
        if (table==null){
            throw new CustomException(ResultEnum.NOT_EVALUATED);
        }
        Elements trs = table.select("tr");
        trs.remove(0);
        return trs.stream()
                .map(tr -> {
                    Score score = new Score();
                    score.setOrderNum(tr.child(0).text());
                    score.setStartSemester(tr.child(1).text());
                    score.setCourseNum(tr.child(2).text());
                    score.setCourseName(tr.child(3).text());
                    score.setScore(tr.child(4).text());
                    score.setCredit(tr.child(5).text());
                    score.setTotalHours(tr.child(6).text());
                    score.setExaminationMethod(tr.child(7).text());
                    score.setCourseAttribute(tr.child(8).text());
                    score.setCourseNature(tr.child(9).text());
                    score.setAlternativeCourseNumber(tr.child(10).text());
                    score.setAlternativeCourseName(tr.child(11).text());
                    score.setMarkOfScore(tr.child(12).text());
                    return score;
                }).collect(Collectors.toList());
    }

    @Override
    public Integer addStudent(Student student) {
        mapper.insertSelective(student);
        return student.getId();
    }
}
