package qqzsh.top.dianping.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import qqzsh.top.dianping.common.AdminPermission;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.common.CommonUtil;
import qqzsh.top.dianping.common.EmBusinessError;
import qqzsh.top.dianping.pojo.Category;
import qqzsh.top.dianping.request.CategoryCreateReq;
import qqzsh.top.dianping.request.PageQuery;
import qqzsh.top.dianping.service.CategoryService;

import javax.validation.Valid;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 23:51
 * @Description
 */
@RestController("/admin/category")
@RequestMapping("/admin/category")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    //品类列表
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){
        Page<Category> categoryPage = new Page<>(pageQuery.getPage(),pageQuery.getSize());
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        IPage<Category> iPage = categoryService.page(categoryPage, wrapper);
        ModelAndView modelAndView = new ModelAndView("/admin/category/index");
        modelAndView.addObject("data",iPage);
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/category/create");
        modelAndView.addObject("CONTROLLER_NAME","category");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @AdminPermission
    public ModelAndView create(@Valid CategoryCreateReq categoryCreateReq, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        categoryService.create(Category.builder()
                .name(categoryCreateReq.getName())
                .iconUrl(categoryCreateReq.getIconUrl())
                .sort(categoryCreateReq.getSort())
                .build());

        return new ModelAndView("redirect:/admin/category/index");
    }

}
