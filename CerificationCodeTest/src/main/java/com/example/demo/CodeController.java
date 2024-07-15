package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.TCaptchaVerify;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CodeController {

    @ResponseBody
    @RequestMapping("/check")
    public int check(HttpServletRequest request){
        String ticket = request.getParameter("ticket");
        String randstr = request.getParameter("randstr");
        String userIp = request.getRemoteAddr();
        int i = TCaptchaVerify.verifyTicket(ticket, randstr, userIp);
        return i;
    }



}
