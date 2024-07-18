package qqzsh.top.project.admin.user.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qqzsh.top.common.utils.poi.ExcelUtil;
import qqzsh.top.framework.aspectj.lang.annotation.Log;
import qqzsh.top.framework.aspectj.lang.enums.BusinessType;
import qqzsh.top.framework.web.controller.BaseController;
import qqzsh.top.framework.web.domain.AjaxResult;
import qqzsh.top.framework.web.page.TableDataInfo;
import qqzsh.top.project.admin.user.domain.TUser;
import qqzsh.top.project.admin.user.service.ITUserService;

/**
 * 普通用户Controller
 * 
 * @author zsh
 * @date 2019-11-17
 */
@Controller
@RequestMapping("/admin/user")
public class TUserController extends BaseController
{
    private String prefix = "admin/user";

    @Autowired
    private ITUserService tUserService;

    @RequiresPermissions("admin:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询普通用户列表
     */
    @RequiresPermissions("admin:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TUser tUser)
    {
        startPage();
        List<TUser> list = tUserService.selectTUserList(tUser);
        return getDataTable(list);
    }

    /**
     * 导出普通用户列表
     */
    @RequiresPermissions("admin:user:export")
    @Log(title = "普通用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TUser tUser)
    {
        List<TUser> list = tUserService.selectTUserList(tUser);
        ExcelUtil<TUser> util = new ExcelUtil<>(TUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增普通用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存普通用户
     */
    @RequiresPermissions("admin:user:add")
    @Log(title = "普通用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TUser tUser) {
        //查询用户名是否重复
        if (tUserService.findByUserName(tUser.getUsername()) == null){
            return toAjax(tUserService.insertTUser(tUser));
        }else {
            return AjaxResult.error("用户名重复！");
        }
    }

    /**
     * 修改普通用户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TUser tUser = tUserService.selectTUserById(id);
        mmap.put("tUser", tUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存普通用户
     */
    @RequiresPermissions("admin:user:edit")
    @Log(title = "普通用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TUser tUser) {
        //判断用户名是否重复
        if (tUserService.findByUserName(tUser.getUsername()) == null
        || (tUser.getId() == tUserService.findByUserName(tUser.getUsername()).getId())){
            return toAjax(tUserService.updateTUser(tUser));
        }else {
            return AjaxResult.error("用户名重复！");
        }
    }

    /**
     * 删除普通用户
     */
    @RequiresPermissions("admin:user:remove")
    @Log(title = "普通用户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tUserService.deleteTUserByIds(ids));
    }
}
