package qqzsh.top.hd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.hd.service.BGMService;
import qqzsh.top.hd.utils.JSONResult;

/**
 * @author zsh
 * @site www.qqzsh.top
 * @company wlgzs
 * @create 2019-05-28 21:04
 * @description
 */
@RestController
@Api(value = "背景音乐业务接口",tags = {"背景音乐业务的controller"})
@RequestMapping("/bgm")
public class BGMController extends BasicController{

    @Autowired
    private BGMService bgmService;

    @ApiOperation(value = "查询背景音乐列表",notes = "查询背景音乐列表接口")
    @PostMapping("/list")
    public JSONResult list(){
        return JSONResult.ok(bgmService.queryAll());
    }
}
