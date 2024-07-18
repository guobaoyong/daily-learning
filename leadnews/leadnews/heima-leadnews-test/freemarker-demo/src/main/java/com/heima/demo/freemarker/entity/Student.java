package com.heima.demo.freemarker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 高翔宇
 * @since 2024-02-12, 周一, 15:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    // 姓名
    private String name;

    // 年龄
    private int age;

    // 生日
    private Date birthday;

    // 钱包
    private Float money;
}