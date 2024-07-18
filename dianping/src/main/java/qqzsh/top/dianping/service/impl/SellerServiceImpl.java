package qqzsh.top.dianping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.common.EmBusinessError;
import qqzsh.top.dianping.mapper.SellerMapper;
import qqzsh.top.dianping.pojo.Seller;
import qqzsh.top.dianping.service.SellerService;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 21:39
 * @Description 商户服务实现类
 */
@Service
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements SellerService {

    @Override
    public Seller changeStatus(Long id, Integer disabledFlag) throws BusinessException {
        Seller seller = baseMapper.selectById(id);
        if (seller == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        seller.setDisabledFlag(disabledFlag);
        baseMapper.updateById(seller);
        return seller;
    }
}
