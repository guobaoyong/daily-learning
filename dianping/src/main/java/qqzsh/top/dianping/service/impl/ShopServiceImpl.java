package qqzsh.top.dianping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qqzsh.top.dianping.common.BusinessException;
import qqzsh.top.dianping.common.EmBusinessError;
import qqzsh.top.dianping.mapper.CategoryMapper;
import qqzsh.top.dianping.mapper.SellerMapper;
import qqzsh.top.dianping.mapper.ShopMapper;
import qqzsh.top.dianping.pojo.Category;
import qqzsh.top.dianping.pojo.Seller;
import qqzsh.top.dianping.pojo.Shop;
import qqzsh.top.dianping.service.ShopService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-15 10:48
 * @Description 门店服务实现类
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    private final SellerMapper sellerMapper;
    private final CategoryMapper categoryMapper;
    private final ShopMapper shopMapper;

    @Override
    @Transactional
    public Shop create(Shop shop) throws BusinessException{
        shop.setCreatedAt(LocalDateTime.now());
        shop.setUpdatedAt(LocalDateTime.now());

        //校验商户是否存在正确
        Seller seller = sellerMapper.selectById(shop.getSellerId());
        if(seller == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商户不存在");
        }
        //商户是否禁用
        if(seller.getDisabledFlag().intValue() == 1){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商户已禁用");
        }
        //校验品类
        Category category = categoryMapper.selectById(shop.getCategoryId());
        if(category == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"品类不存在");
        }
        //保存
        super.save(shop);
        return shop;
    }

    @Override
    public Shop get(Long id) {
        Shop shop = baseMapper.selectById(id);
        if(shop == null){
            return null;
        }
        shop.setSeller(sellerMapper.selectById(shop.getSellerId()));
        shop.setCategory(categoryMapper.selectById(shop.getCategoryId()));
        return shop;
    }

    @Override
    public List<Shop> selectAll() {
        List<Shop> shopList = baseMapper.selectList(null);
        shopList.forEach(shop -> {
            shop.setSeller(sellerMapper.selectById(shop.getSellerId()));
            shop.setCategory(categoryMapper.selectById(shop.getCategoryId()));
        });
        return shopList;
    }

    @Override
    public List<Shop> recommend(BigDecimal longitude, BigDecimal latitude) {
        List<Shop> shopList = shopMapper.recommend(longitude, latitude);
        shopList.forEach(shop -> {
            shop.setSeller(sellerMapper.selectById(shop.getSellerId()));
            shop.setCategory(categoryMapper.selectById(shop.getCategoryId()));
        });
        return shopList;
    }

    @Override
    public List<Map<String, Object>> searchGroupByTags(String keyword, Integer categoryId, String tags) {
        return shopMapper.searchGroupByTags(keyword,categoryId,tags);
    }

    @Override
    public List<Shop> search(BigDecimal longitude, BigDecimal latitude, String keyword, Integer orderby, Integer categoryId, String tags) {
        List<Shop> shopList = shopMapper.search(longitude,latitude,keyword,orderby,categoryId,tags);
        shopList.forEach(shop -> {
            shop.setSeller(sellerMapper.selectById(shop.getSellerId()));
            shop.setCategory(categoryMapper.selectById(shop.getCategoryId()));
        });
        return shopList;
    }
}
