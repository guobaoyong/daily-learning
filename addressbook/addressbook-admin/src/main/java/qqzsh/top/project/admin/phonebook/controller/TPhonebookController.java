package qqzsh.top.project.admin.phonebook.controller;

import java.util.ArrayList;
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
import qqzsh.top.common.utils.StringUtils;
import qqzsh.top.common.utils.poi.ExcelUtil;
import qqzsh.top.framework.aspectj.lang.annotation.Log;
import qqzsh.top.framework.aspectj.lang.enums.BusinessType;
import qqzsh.top.framework.web.controller.BaseController;
import qqzsh.top.framework.web.domain.AjaxResult;
import qqzsh.top.framework.web.page.TableDataInfo;
import qqzsh.top.project.admin.phonebook.domain.TPhonebook;
import qqzsh.top.project.admin.phonebook.service.ITPhonebookService;
import qqzsh.top.project.admin.user.domain.TUser;
import qqzsh.top.project.admin.user.service.ITUserService;

/**
 * 通讯录Controller
 * 
 * @author zsh
 * @date 2019-11-17
 */
@Controller
@RequestMapping("/admin/phonebook")
public class TPhonebookController extends BaseController
{
    private String prefix = "admin/phonebook";

    @Autowired
    private ITPhonebookService tPhonebookService;

    @Autowired
    private ITUserService itUserService;

    @RequiresPermissions("admin:phonebook:view")
    @GetMapping()
    public String phonebook()
    {
        return prefix + "/phonebook";
    }

    /**
     * 查询通讯录列表
     */
    @RequiresPermissions("admin:phonebook:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TPhonebook tPhonebook)
    {
        startPage();
        System.out.println(tPhonebook);
        //根据userName查出userId
        if (StringUtils.isNotBlank(tPhonebook.getUserName())){
            TUser name = itUserService.findByUserName(tPhonebook.getUserName());
            if (name != null){
                tPhonebook.setUserid(name.getId());
            } else {
                return getDataTable(null);
            }
        }
        List<TPhonebook> list = tPhonebookService.selectTPhonebookList(tPhonebook);
        List<TPhonebook> lists = new ArrayList<>();
        list.forEach(tPhonebook1 -> {
            tPhonebook1.setUserName(itUserService.selectTUserById(tPhonebook1.getUserid()).getUsername());
            lists.add(tPhonebook1);
        });
        return getDataTable(lists);
    }

    /**
     * 导出通讯录列表
     */
    @RequiresPermissions("admin:phonebook:export")
    @Log(title = "通讯录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TPhonebook tPhonebook)
    {
        List<TPhonebook> list = tPhonebookService.selectTPhonebookList(tPhonebook);
        ExcelUtil<TPhonebook> util = new ExcelUtil<TPhonebook>(TPhonebook.class);
        return util.exportExcel(list, "phonebook");
    }

    /**
     * 新增通讯录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存通讯录
     */
    @RequiresPermissions("admin:phonebook:add")
    @Log(title = "通讯录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TPhonebook tPhonebook)
    {
        return toAjax(tPhonebookService.insertTPhonebook(tPhonebook));
    }

    /**
     * 修改通讯录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        TPhonebook tPhonebook = tPhonebookService.selectTPhonebookById(id);
        mmap.put("tPhonebook", tPhonebook);
        return prefix + "/edit";
    }

    /**
     * 修改保存通讯录
     */
    @RequiresPermissions("admin:phonebook:edit")
    @Log(title = "通讯录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TPhonebook tPhonebook)
    {
        return toAjax(tPhonebookService.updateTPhonebook(tPhonebook));
    }

    /**
     * 删除通讯录
     */
    @RequiresPermissions("admin:phonebook:remove")
    @Log(title = "通讯录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(tPhonebookService.deleteTPhonebookByIds(ids));
    }
}
