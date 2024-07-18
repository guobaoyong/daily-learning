package qqzsh.top.dianping.controller.admin;

import com.alibaba.druid.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import qqzsh.top.dianping.common.AdminPermission;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.common.EmBusinessError;
import qqzsh.top.dianping.pojo.User;
import qqzsh.top.dianping.service.CategoryService;
import qqzsh.top.dianping.service.SellerService;
import qqzsh.top.dianping.service.ShopService;
import qqzsh.top.dianping.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 18:05
 * @Description 管理员
 */
@RestController
@RequestMapping("/admin/admin")
@AllArgsConstructor
public class AdminController {

    private HttpServletRequest httpServletRequest;
    private UserService userService;
    private CategoryService categoryService;
    private SellerService sellerService;
    private ShopService shopService;

    public static final String CURRENT_ADMIN_SESSION = "currentAdminSession";

    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/admin/admin/index");
        //用户总数
        modelAndView.addObject("userCount",userService.count());
        modelAndView.addObject("shopCount",shopService.count());
        modelAndView.addObject("categoryCount",categoryService.count());
        modelAndView.addObject("sellerCount",sellerService.count());
        modelAndView.addObject("CONTROLLER_NAME","admin");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    @RequestMapping("/loginpage")
    public ModelAndView loginpage(){
        return new ModelAndView("/admin/admin/login");
    }

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(name="email")String email,
                        @RequestParam(name="password")String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "用户名密码不能为空");
        }
        User login = userService.login(email, password);
        if (login != null && "admin".equals(login.getRole())){
            //登录成功
            httpServletRequest.getSession().setAttribute(CURRENT_ADMIN_SESSION,email);
            return new ModelAndView("redirect:/admin/admin/index");
        }else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"当前用户无管理员权限");
        }
    }




}
