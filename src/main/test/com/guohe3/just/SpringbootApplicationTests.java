package com.guohe3.just;

import com.guohe3.just.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {
    @Autowired
    private StudentService studentService;

    @Test
    public void contextLoads() throws IOException {
        studentService.getSchoolTimetable("152210702119","935377012pxc","2017-2018-2");

    }

}
