package com.guohe3.just.service.impl;

import com.guohe3.just.DO.Score;
import com.guohe3.just.DO.Student;
import com.guohe3.just.common.constants.Constants;
import com.guohe3.just.common.enums.ResultEnum;
import com.guohe3.just.common.execption.CustomException;
import com.guohe3.just.craw.CrawService;
import com.guohe3.just.mapper.StudentMapper;
import com.guohe3.just.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 浦希成
 * 2018/10/24
 */
@Service
@Slf4j
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
        String result = crawService.getSourceHtml(client, Constants.SCORE_URL);
        Document doc = Jsoup.parse(result);
        Element table = doc.getElementById("dataList");
        if (table == null) {
            log.info("页面数据：{}",result);
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

    @Override
    public Student getStudentInfo(String username, String password) throws IOException {
        OkHttpClient client = crawService.login(username, password);
        if (client == null) {
            throw new CustomException(ResultEnum.LOGIN_FAIL);
        }
        String html = crawService.getSourceHtml(client, Constants.STUDENT_INFO_URL);
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

    @Override
    public List<Map<String, String>> getSchoolTimetable(String username, String password, String semester) throws IOException {
        String[] weekList = new String[]{
                "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"
        };
        OkHttpClient client = crawService.login(username, password);
        if (client == null) {
            throw new CustomException(ResultEnum.LOGIN_FAIL);
        }
        String html = crawService.getSourceHtml(client, Constants.KB_URL + "?xnxq01id=" + semester);
        Document doc = Jsoup.parse(html);
        Elements trs = doc.select("#kbtable tr");
        trs.remove(0);
        List<Map<String, String>> result = new ArrayList<>(6);
        for (Element tr : trs) {

            Elements tds = tr.select(".kbcontent");
            int i = 0;
            Map<String, String> map = new HashMap<>(7);
            for (Element td : tds) {

                StringBuilder courseAll = new StringBuilder();
                for (String courseString : td.html().split("---------------------")) {
                    if (courseString.length() > 10) {
                        String course = courseString.replaceAll("<br>|<font title=\"老师\">|<font title=\"教室\">|<font title=\"周次\\(节次\\)\">|<\\/font>", "")
                                .replaceAll("(?m)^\\s*$" + System.lineSeparator(), "")
                                .replaceAll("[\r\n]", "@");
                        courseAll.append(course).append("---------------------");
                    }

                }
                int index = courseAll.lastIndexOf("---------------------");
                if (index != -1) {
                    String courseStrTemp = courseAll.toString().substring(0, index);
                    int indexTemp = courseStrTemp.lastIndexOf("@");
                    int lengthTemp = courseStrTemp.length();
                    String courseStr = courseStrTemp.substring(0, indexTemp) +
                            courseStrTemp.substring(indexTemp + 1, lengthTemp);
                    map.put(weekList[i], courseStr);
                }

                i++;
            }
            result.add(map);
        }
        return result;
    }

    @Override
    public List<Map<String, String>> getJidian(String username, String password) throws IOException {

        //返回结果
        List<Map<String, String>> result=new ArrayList<>();

        //成绩集合
        List<Score> scoreList = getScoreAll(username, password);

        //总绩点
        double creditPointSumAll = scoreList.stream().mapToDouble(item -> Double.parseDouble(item.getCredit())*getScoreNum(item.getScore())).sum();
        double creditSUmAll = scoreList.stream().mapToDouble(item -> Double.parseDouble(item.getCredit())).sum();
        Map<String,String> mapAll=new HashMap<>(2);
        mapAll.put("year","all");
        mapAll.put("point",new DecimalFormat("#.###").format(creditPointSumAll/creditSUmAll)+"");
        result.add(mapAll);

        //学期分组绩点
        List<List<Score>> oneSemesterList = new ArrayList<>();

        group(result,scoreList,oneSemesterList);

        //学年分组绩点
        List<List<Score>> twoSemesterlist = new ArrayList<>();
        for (Score score : scoreList) {
           score.setStartSemester(score.getStartSemester().substring(0,9));
        }
        group(result,scoreList,twoSemesterlist);
        return result;
    }

    /**
     *
     * @param result 最终返回结果
     * @param scoreList 总数据
     * @param semesterList 学年数据
     */
    private void group(List<Map<String, String>> result, List<Score> scoreList, List<List<Score>> semesterList){
        scoreList.stream()
                //只计算必修科目
                .filter(item -> "必修".equalsIgnoreCase(item.getCourseAttribute()))
                //体育不算分
                .filter(item -> !item.getCourseName().contains("体育"))
                //不及格的不算绩点
                .filter(item -> isPass(item.getScore()))
                //按学年分组
                .collect(Collectors.groupingBy(Score::getStartSemester, Collectors.toList()))
                .forEach((name, scores) -> semesterList.add(scores));
        //分组求平均绩点 平均绩点的计算公式是 绩点*学分/学分之和
        for (List<Score> scores : semesterList) {
            Map<String,String> map=new HashMap<>(2);
            //学分绩点之和
            double creditPointSum = scores.stream().mapToDouble(item -> Double.parseDouble(item.getCredit())*getScoreNum(item.getScore())).sum();
            //学分之和
            double creditSum = scores.stream().mapToDouble(item -> Double.parseDouble(item.getCredit())).sum();
            map.put("year",scores.get(0).getStartSemester());
            map.put("point",new DecimalFormat("#.###").format(creditPointSum/creditSum)+"");
            result.add(map);
        }
    }

    private boolean isPass(String score) {
        if (StringUtils.isNumeric(score)) {
            return Double.parseDouble(score)>=60;
        }  else if ("不及格".equals(score)) {
            return false;
        } else if ("通过".equals(score)) {
            return true;
        } else if ("不通过".equals(score)) {
            return false;
        } else {
            return true;
        }
    }

    private double getScoreNum(String score) {
        if (StringUtils.isNumeric(score)) {
            return Double.parseDouble(score) / 10 - 5;
        } else if ("优".equals(score)) {
            return 4.5;
        } else if ("良".equals(score)) {
            return 3.5;
        } else if ("中".equals(score)) {
            return 2.5;
        } else if ("及格".equals(score)) {
            return 1.5;
        } else if ("不及格".equals(score)) {
            return 0;
        } else if ("通过".equals(score)) {
            return 2.5;
        } else if ("不通过".equals(score)) {
            return 0;
        } else {
            return 3;
        }
    }


}
