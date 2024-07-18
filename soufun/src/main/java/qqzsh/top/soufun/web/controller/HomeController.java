package qqzsh.top.soufun.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import qqzsh.top.soufun.base.ApiResponse;
import qqzsh.top.soufun.base.LoginUserUtil;
import qqzsh.top.soufun.service.ISmsService;
import qqzsh.top.soufun.service.ServiceMultiResult;
import qqzsh.top.soufun.service.ServiceResult;
import qqzsh.top.soufun.service.house.IAddressService;
import qqzsh.top.soufun.web.dto.SupportAddressDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-30 9:23
 * @description
 */
@Controller
public class HomeController {

    @Autowired
    private ISmsService smsService;

    @Autowired
    private IAddressService addressService;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        //所有的城市
        ServiceMultiResult<SupportAddressDTO> allCities = addressService.findAllCities();
        List<String> chars = new ArrayList<>();
        List<String> chars2 = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            char ch = (char)(65+i);
            char ch2 = (char)(78+i);
            chars.add(String.valueOf(ch).toUpperCase());
            chars2.add(String.valueOf(ch2).toUpperCase());
        }
        model.addAttribute("allCities",allCities.getResult());
        model.addAttribute("chars",chars);
        model.addAttribute("chars2",chars2);
        return "index";
    }

    @GetMapping("/get")
    @ResponseBody
    public ApiResponse get(){
        return ApiResponse.ofMessage(200,"成功了!");
    }

    @GetMapping("/404")
    public String notFoundPage() {
        return "404";
    }

    @GetMapping("/403")
    public String accessError() {
        return "403";
    }

    @GetMapping("/500")
    public String internalError() {
        return "500";
    }

    @GetMapping("/logout/page")
    public String logoutPage() {
        return "index";
    }

    @GetMapping(value = "sms/code")
    @ResponseBody
    public ApiResponse smsCode(@RequestParam("telephone") String telephone) {
        if (!LoginUserUtil.checkTelephone(telephone)) {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), "请输入正确的手机号");
        }
        ServiceResult<String> result = smsService.sendSms(telephone);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess("");
        } else {
            return ApiResponse.ofMessage(HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }
    }

}
