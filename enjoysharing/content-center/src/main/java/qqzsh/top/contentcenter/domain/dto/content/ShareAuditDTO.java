package qqzsh.top.contentcenter.domain.dto.content;

import lombok.Data;
import qqzsh.top.contentcenter.domain.enums.AuditStatusEnum;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 10:06
 * @Description
 */
@Data
public class ShareAuditDTO {
    /**
     * 审核状态
     */
    private AuditStatusEnum auditStatusEnum;
    /**
     * 原因
     */
    private String reason;
}