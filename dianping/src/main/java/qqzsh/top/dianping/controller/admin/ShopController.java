package qqzsh.top.dianping.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import qqzsh.top.dianping.common.AdminPermission;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.common.CommonUtil;
import qqzsh.top.dianping.common.EmBusinessError;
import qqzsh.top.dianping.pojo.Shop;
import qqzsh.top.dianping.request.PageQuery;
import qqzsh.top.dianping.request.ShopCreateReq;
import qqzsh.top.dianping.service.ShopService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-15 11:03
 * @Description
 */
@RestController()
@RequestMapping("/admin/shop")
@AllArgsConstructor
public class ShopController {

    private ShopService shopService;

    //门店列表
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){
        Page<Shop> shopPage = new Page<>(pageQuery.getPage(),pageQuery.getSize());
        QueryWrapper<Shop> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        IPage<Shop> iPage = shopService.page(shopPage, wrapper);
        List<Shop> records = iPage.getRecords();
        List<Shop> results = new ArrayList<>();
        records.forEach(shop -> {
            shop = shopService.get(shop.getId());
            results.add(shop);
        });
        iPage.setRecords(results);
        ModelAndView modelAndView = new ModelAndView("/admin/shop/index");
        modelAndView.addObject("data",iPage);
        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/shop/create");
        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @AdminPermission
    public ModelAndView create(@Valid ShopCreateReq shopCreateReq, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        shopService.create(Shop.builder()
                .iconUrl(shopCreateReq.getIconUrl())
                .address(shopCreateReq.getAddress())
                .categoryId(Long.parseLong(shopCreateReq.getCategoryId()))
                .endTime(shopCreateReq.getEndTime())
                .startTime(shopCreateReq.getStartTime())
                .longitude(shopCreateReq.getLongitude())
                .latitude(shopCreateReq.getLatitude())
                .name(shopCreateReq.getName())
                .pricePerMan(shopCreateReq.getPricePerMan())
                .sellerId(Long.parseLong(shopCreateReq.getSellerId()))
                .build());
        return new ModelAndView("redirect:/admin/shop/index");
    }

}
