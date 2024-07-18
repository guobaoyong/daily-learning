package qqzsh.top.dianping.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.dianping.common.CommonRes;
import qqzsh.top.dianping.service.CategoryService;

/**
 * @author zsh
 * @site https://qqzsh
 * @create 2019-12-15 10:14
 * @Description 前端用 品类相关
 */
@RestController("/category")
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    /**
     * 获取所有品类
     * @return
     */
    @RequestMapping("/list")
    public CommonRes list(){
        return CommonRes.create(categoryService.selectAll());
    }

}
