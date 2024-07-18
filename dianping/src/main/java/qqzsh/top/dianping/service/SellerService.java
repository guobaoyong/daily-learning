package qqzsh.top.dianping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.pojo.Seller;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 21:38
 * @Description 商户接口
 */
public interface SellerService extends IService<Seller> {

    /**
     * 改变商户状态
     * @param id
     * @param disabledFlag
     * @return
     */
    Seller changeStatus(Long id,Integer disabledFlag) throws BusinessException;
}
