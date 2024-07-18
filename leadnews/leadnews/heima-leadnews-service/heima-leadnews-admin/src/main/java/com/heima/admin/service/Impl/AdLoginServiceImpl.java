package com.heima.admin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.AdLoginMapper;
import com.heima.admin.service.AdLoginService;
import com.heima.model.admin.dto.AdLoginDto;
import com.heima.model.admin.entity.AdUser;
import com.heima.model.admin.vo.AdLoginVo;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.utils.common.AppJwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * @author 高翔宇
 * @since 2024/4/13 周六 下午2:35
 */
@Service
@RequiredArgsConstructor
public class AdLoginServiceImpl extends ServiceImpl<AdLoginMapper, AdUser> implements AdLoginService {
    private final AdLoginMapper adLoginMapper;

    /**
     * 登录
     */
    @Override
    public ResponseResult<AdLoginVo> login(@NonNull AdLoginDto adLoginDto) {
        String name = adLoginDto.getName();
        String password = adLoginDto.getPassword();
        if (!StringUtils.hasLength(password) || !StringUtils.hasLength(name)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        AdUser adUser = adLoginMapper.selectOne(new LambdaQueryWrapper<AdUser>().eq(AdUser::getName, name));
        if (adUser == null) {
            return ResponseResult.errorResult(10001, "用户不存在");
        } else if (!DigestUtils.md5DigestAsHex(password.concat(adUser.getSalt()).getBytes()).equals(adUser.getPassword())) {
            return ResponseResult.errorResult(10002, "密码错误");
        }
        AdLoginVo.User user = new AdLoginVo.User();
        BeanUtils.copyProperties(adUser, user);
        return ResponseResult.okResult(AdLoginVo
                .builder()
                .user(user)
                .token(AppJwtUtil.generateToken(String.valueOf(adUser.getId())))
                .build());
    }
}
