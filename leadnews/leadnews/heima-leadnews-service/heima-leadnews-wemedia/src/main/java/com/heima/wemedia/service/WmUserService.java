package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.wemedia.entity.WmUser;
import com.heima.model.wemedia.dto.WmLoginDto;
import com.heima.model.wemedia.vo.LoginVo;

/**
 * @author 高翔宇
 * @since 2024/2/15 周四 下午4:14
 */
public interface WmUserService extends IService<WmUser> {
    /**
     * 自媒体用户登录
     */
    ResponseResult<LoginVo> login(WmLoginDto wmLoginDto);
}
