package com.heima.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.user.entity.ApUser;
import com.heima.model.user.dto.LoginDto;
import com.heima.model.user.vo.LoginVo;

/**
 * @author 高翔宇
 * @since 2024/2/7 周三 15:52
 */
public interface ApUserService extends IService<ApUser> {
    ResponseResult<LoginVo> login(LoginDto loginDto);
}
