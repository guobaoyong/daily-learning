package qqzsh.top.project.system.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import qqzsh.top.framework.config.RuoYiConfig;
import qqzsh.top.framework.web.controller.BaseController;
import qqzsh.top.project.admin.phonebook.service.ITPhonebookService;
import qqzsh.top.project.admin.user.service.ITUserService;
import qqzsh.top.project.monitor.server.domain.Jvm;
import qqzsh.top.project.system.menu.domain.Menu;
import qqzsh.top.project.system.menu.service.IMenuService;
import qqzsh.top.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    @Autowired
    private RuoYiConfig ruoYiConfig;

    @Autowired
    private ITUserService userService;

    @Autowired
    private ITPhonebookService phonebookService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getSysUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", ruoYiConfig.getCopyrightYear());
        mmap.put("demoEnabled", ruoYiConfig.isDemoEnabled());
        return "index";
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {
        mmap.put("userNum", userService.count());
        mmap.put("articleNums", phonebookService.count());
        //计算系统运行时长
        mmap.put("bookNums", new Jvm().getRunTime());
        return "main_v1";
    }
}
