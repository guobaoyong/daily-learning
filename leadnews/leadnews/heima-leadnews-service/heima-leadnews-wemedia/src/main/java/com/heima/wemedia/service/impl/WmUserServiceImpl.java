package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.entity.WmUser;
import com.heima.model.wemedia.dto.WmLoginDto;
import com.heima.model.wemedia.vo.LoginVo;
import com.heima.utils.common.AppJwtUtil;
import com.heima.wemedia.mapper.WmUserMapper;
import com.heima.wemedia.service.WmUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * @author 高翔宇
 * @since 2024/2/15 周四 下午4:15
 */
@Service
@RequiredArgsConstructor
public class WmUserServiceImpl extends ServiceImpl<WmUserMapper, WmUser> implements WmUserService {
    private final WmUserMapper wmUserMapper;

    /**
     * 自媒体用户登录
     *
     * @param wmLoginDto 自媒体用户登录数据传输对象
     */
    @Override
    public ResponseResult<LoginVo> login(WmLoginDto wmLoginDto) {
        if (wmLoginDto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        } else {
            // 用户名
            String name = wmLoginDto.getName();
            if (!StringUtils.hasLength(name)) {
                return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
            } else {
                WmUser dbWmUser = wmUserMapper.selectOne(new LambdaQueryWrapper<WmUser>().eq(WmUser::getName, name));
                if (dbWmUser == null) {
                    return ResponseResult.errorResult(AppHttpCodeEnum.AP_USER_DATA_NOT_EXIST);
                } else {
                    // 数据库中的盐
                    String salt = dbWmUser.getSalt();
                    // 对 (用户输入的密码 + 盐) 进行 MD5 加密，数据库中的密码也经过了类似的处理
                    String md5DigestAsHex = DigestUtils.md5DigestAsHex(wmLoginDto.getPassword().concat(salt).getBytes());
                    // 如果两个 MD5 加密后的密码不相等，说明用户输入的密码错误
                    if (!md5DigestAsHex.equals(dbWmUser.getPassword())) {
                        return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
                    } else {
                        Long id = dbWmUser.getId();
                        return ResponseResult.okResult(new LoginVo(LoginVo.LoginUserInfo.builder()
                                .id(id)
                                .name(dbWmUser.getName())
                                .phone(dbWmUser.getPhone()).build(),
                                AppJwtUtil.generateToken(String.valueOf(id)))
                        );
                    }
                }
            }
        }
    }
}
