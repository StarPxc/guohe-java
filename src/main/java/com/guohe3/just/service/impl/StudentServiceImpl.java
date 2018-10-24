package com.guohe3.just.service.impl;

import com.guohe3.just.DO.Score;
import com.guohe3.just.common.enums.ResultEnum;
import com.guohe3.just.common.execption.CustomException;
import com.guohe3.just.craw.CrawService;
import com.guohe3.just.service.StudentService;
import okhttp3.OkHttpClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<Score> getScoreAll(String username, String password) throws IOException {
        OkHttpClient client=crawService.justLoginNormal(username,password);
        if (client==null){
            throw new CustomException(ResultEnum.LOGIN_FAIL);
        }
        String result=crawService.getScoreHtml(client);
        Document doc= Jsoup.parse(result);
        Element table=doc.getElementById("dataList");
        Elements trs=table.select("tr");
        trs.remove(0);
        List<Score> scoreList=trs.stream()
                .map(tr->{
                    Score score=new Score();
                    score.setOrder_num(tr.child(0).text());
                    score.setStart_semester(tr.child(1).text());
                    score.setCourse_num(tr.child(2).text());
                    score.setCourse_name(tr.child(3).text());
                    score.setScore(tr.child(4).text());
                    score.setCredit(tr.child(5).text());
                    score.setTotal_hours(tr.child(6).text());
                    score.setExamination_method(tr.child(7).text());
                    score.setCourse_attribute(tr.child(8).text());
                    score.setCourse_nature(tr.child(9).text());
                    score.setAlternative_course_number(tr.child(10).text());
                    score.setAlternative_course_name(tr.child(11).text());
                    score.setMark_of_score(tr.child(12).text());
                    return score;
                }).collect(Collectors.toList());
        System.out.println(result);
        System.out.println(scoreList);
        return null;
    }
}
