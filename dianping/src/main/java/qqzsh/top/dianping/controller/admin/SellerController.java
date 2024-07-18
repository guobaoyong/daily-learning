package qqzsh.top.dianping.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import qqzsh.top.dianping.common.*;
import qqzsh.top.dianping.pojo.Seller;
import qqzsh.top.dianping.request.PageQuery;
import qqzsh.top.dianping.request.SellerCreateReq;
import qqzsh.top.dianping.service.SellerService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 21:41
 * @Description 商户
 */
@RestController
@RequestMapping("/admin/seller")
@AllArgsConstructor
public class SellerController {

    private SellerService sellerService;

    //商户列表
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){
        Page<Seller> sellerPage = new Page<>(pageQuery.getPage(), pageQuery.getSize());
        QueryWrapper<Seller> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        IPage<Seller> iPage = sellerService.page(sellerPage, wrapper);
        ModelAndView modelAndView = new ModelAndView("/admin/seller/index");
        modelAndView.addObject("data",iPage);
        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/seller/create");
        modelAndView.addObject("CONTROLLER_NAME","seller");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @AdminPermission
    public ModelAndView create(@Valid SellerCreateReq sellerCreateReq, BindingResult bindingResult) throws BusinessException {
        //有错误直接返回
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        //保存
        sellerService.save(Seller.builder()
                .name(sellerCreateReq.getName())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .remarkScore(new BigDecimal(0))
                .disabledFlag(0)
                .build());
        return new ModelAndView("redirect:/admin/seller/index");
    }

    @RequestMapping(value="down",method = RequestMethod.POST)
    @AdminPermission
    public CommonRes down(@RequestParam(value="id")String id) throws BusinessException {
        return CommonRes.create(sellerService.changeStatus(Long.valueOf(id),1));
    }

    @RequestMapping(value="up",method = RequestMethod.POST)
    @AdminPermission
    public CommonRes up(@RequestParam(value="id")String id) throws BusinessException {
        return CommonRes.create(sellerService.changeStatus(Long.valueOf(id),0));
    }
}
