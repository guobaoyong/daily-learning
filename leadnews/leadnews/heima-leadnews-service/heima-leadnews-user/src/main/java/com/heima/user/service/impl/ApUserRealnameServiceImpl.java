package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dto.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dto.ApUserRealnamePageQueryDto;
import com.heima.model.user.dto.ApUserRealnamePassDto;
import com.heima.model.user.dto.ApUserRealnameRejectDto;
import com.heima.model.user.entity.ApUser;
import com.heima.model.user.entity.ApUserRealname;
import com.heima.model.user.enums.ApUserRealnameStatus;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.mapper.ApUserRealnameMapper;
import com.heima.user.service.ApUserRealnameService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 高翔宇
 * @since 2024/4/15 周一 下午1:33
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {
    private final ApUserMapper apUserMapper;
    private final ApUserRealnameMapper apUserRealnameMapper;

    /**
     * 查询实名认证列表
     */
    @Override
    public ResponseResult<List<ApUserRealname>> list(@NonNull ApUserRealnamePageQueryDto apUserRealnameDto) {
        Integer page = apUserRealnameDto.getPage();
        Integer size = apUserRealnameDto.getSize();
        Short status = apUserRealnameDto.getStatus();
        if (page == null || page < 1 || size == null || size < 1) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return new ResponseResult<List<ApUserRealname>>().ok(apUserRealnameMapper
                .selectPage(new Page<>(page, size), new LambdaQueryWrapper<ApUserRealname>()
                        .eq(status != null, ApUserRealname::getStatus, ApUserRealnameStatus.fromValue(status)))
                .getRecords());
    }

    /**
     * 实名认证通过
     */
    @Override
    public ResponseResult<?> pass(@NotNull ApUserRealnamePassDto apUserRealnamePassDto) {
        if (apUserRealnamePassDto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        ApUserRealname apUserRealname = apUserRealnameMapper.selectById(apUserRealnamePassDto.getId());
        if (apUserRealname == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        } else if (apUserRealname.getStatus() == ApUserRealnameStatus.APPROVED) {
            return ResponseResult.errorResult(500, "实名认证信息已通过");
        }
        apUserRealname.setStatus(ApUserRealnameStatus.APPROVED);
        int updatedById = apUserRealnameMapper.updateById(apUserRealname);
        if (updatedById == 0) {
            return ResponseResult.errorResult(500, "实名认证信息更新失败");
        } else {
            // 更新用户表实名认证状态
            ApUser apUser = apUserMapper.selectById(apUserRealname.getUserId());
            if (apUser == null) {
                throw new RuntimeException("用户不存在");
            }
            apUser.setCertification(true);
            int updated = apUserMapper.updateById(apUser);
            if (updated == 0) {
                return ResponseResult.errorResult(500, "用户实名认证状态更新失败");
            }
        }
        return new ResponseResult<ApUserRealname>().ok(200, apUserRealname, "实名认证信息更新成功");
    }

    /**
     * 实名认证驳回
     */
    @Override
    public ResponseResult<?> reject(@NonNull ApUserRealnameRejectDto apUserRealnameRejectDto) {
        if (apUserRealnameRejectDto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        ApUserRealname apUserRealname = apUserRealnameMapper.selectById(apUserRealnameRejectDto.getId());
        if (apUserRealname == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        } else if (apUserRealname.getStatus() == ApUserRealnameStatus.REJECTED) {
            return ResponseResult.errorResult(500, "实名认证信息已被驳回");
        }
        apUserRealname.setStatus(ApUserRealnameStatus.REJECTED);
        int updatedById = apUserRealnameMapper.updateById(apUserRealname);
        if (updatedById == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return new ResponseResult<ApUserRealname>().ok(200, apUserRealname, "实名认证信息更新成功");
    }
}
