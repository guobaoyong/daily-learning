package qqzsh.top.sponsor.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.sponsor.service.ICreativeService;
import qqzsh.top.sponsor.vo.CreativeRequest;
import qqzsh.top.sponsor.vo.CreativeResponse;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 14:06
 * @Description 创意Controller
 */
@Slf4j
@RestController
public class CreativeOPController {

    private final ICreativeService creativeService;

    @Autowired
    public CreativeOPController(ICreativeService creativeService) {
        this.creativeService = creativeService;
    }

    @PostMapping("/create/creative")
    public CreativeResponse createCreative(
            @RequestBody CreativeRequest request
    ) {
        log.info("sponsor: createCreative -> {}",
                JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
