package com.heima.demo.freemarker.controller;

import com.heima.demo.freemarker.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/2/12 周一 下午3:57
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model) {
        LocalDateTime localDateTime = LocalDateTime.of(2000, 1, 1, 0, 0);
        Date birthday = Date.from(localDateTime.toInstant(ZoneOffset.ofHours(8)));
        Student student = Student.builder()
                .name("张三")
                .age(18)
                .birthday(birthday)
                .money(100.0f)
                .build();
        model.addAttribute("name", "freemarker");
        model.addAttribute("stu", student);
        return "01-basic";
    }

    @GetMapping("/list")
    public String list(Model model) {
        Student stu1 = new Student();
        stu1.setName("小强");
        stu1.setAge(18);
        stu1.setMoney(1000.86f);
        stu1.setBirthday(new Date());

        //小红对象模型数据
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1f);
        stu2.setAge(19);

        Student stu3 = new Student();
        stu3.setMoney(300.1f);
        stu3.setAge(20);

        //将两个对象模型数据存放到List集合中
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);

        //向model中存放List集合数据
        model.addAttribute("stus", stus);

        //------------------------------------

        //创建Map数据
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        stuMap.put("stu3", stu3);
        // 3.1 向model中存放Map数据
        model.addAttribute("stuMap", stuMap);

        model.addAttribute("today", new Date());

        model.addAttribute("point", 102920122);

        return "02-list";
    }
}
