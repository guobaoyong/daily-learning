package com.heima.model.admin.vo;

import com.heima.model.admin.entity.AdUser;
import com.heima.model.admin.enums.AdminStatus;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * admin用户登录成功后返回的数据模型。除隐私字段外，这些字段应该和{@link AdUser}一致
 *
 * @author 高翔宇
 * @since 2024/4/13 周六 下午2:38
 */
@Data
@Builder
public class AdLoginVo implements Serializable {
    private User user;
    private String token;

    @Data
    public static class User {
        /**
         * 主键
         */
        private Long id;

        /**
         * 登录用户名
         */
        private String name;

        /**
         * 昵称
         */
        private String nickname;

        /**
         * 头像
         */
        private String image;

        /**
         * 手机号
         */
        private String phone;

        /**
         * 状态
         * <ul>
         *     <li>0 暂时不可用</li>
         *     <li>1 永久不可用</li>
         *     <li>9 正常可用</li>
         * </ul>
         */
        private AdminStatus status;

        /**
         * 邮箱
         */
        private String email;

        /**
         * 最后一次登录时间
         */
        private Date loginTime;

        /**
         * 创建时间
         */
        private Date createdTime;
    }
}
