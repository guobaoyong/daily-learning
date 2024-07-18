package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.entity.ApUser;
import com.heima.model.user.dto.LoginDto;
import com.heima.model.user.vo.LoginVo;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * @author 高翔宇
 * @since 2024/2/7 周三 15:53
 */
@Service
@RequiredArgsConstructor
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {
    private final ApUserMapper apUserMapper;

    @Override
    public ResponseResult<LoginVo> login(LoginDto loginDto) {
        String phone = loginDto.getPhone();
        String password = loginDto.getPassword();
        if (!StringUtils.hasLength(phone) && !StringUtils.hasLength(password)) {
            // 游客登录
            return ResponseResult.okResult(new LoginVo(null, AppJwtUtil.generateToken(String.valueOf(0))));
        } else {
            // 非游客
            ApUser apUser = apUserMapper
                    .selectOne(new LambdaQueryWrapper<ApUser>().eq(ApUser::getPhone, phone));
            if (apUser == null) {
                // 用户不存在
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "用户不存在");
            }
            // 将（用户提交的密码 + 数据库中保存的盐）进行MD5加密。数据库中的密码 = （真正的密码 + 数据库中保存的盐）
            String md5DigestAsHex = DigestUtils.md5DigestAsHex((password + apUser.getSalt()).getBytes());
            if (!md5DigestAsHex.equals(apUser.getPassword())) {
                // 密码错误
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            } else {
                // 登录成功
                return ResponseResult.okResult(new LoginVo(
                        LoginVo.LoginUserInfo.builder()
                                .id(apUser.getId())
                                .name(apUser.getName())
                                .phone(apUser.getPhone()).build(),
                        AppJwtUtil.generateToken(String.valueOf(apUser.getId()))));
            }
        }
    }
}
