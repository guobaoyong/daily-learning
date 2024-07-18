package qqzsh.top.contentcenter.controller.content;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qqzsh.top.contentcenter.auth.CheckAuthorization;
import qqzsh.top.contentcenter.domain.dto.content.ShareAuditDTO;
import qqzsh.top.contentcenter.domain.entity.content.Share;
import qqzsh.top.contentcenter.service.content.ShareService;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-10 10:05
 * @Description 管理员审核controller
 */
@RestController
@RequestMapping("/admin/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareAdminController {

    private final ShareService shareService;

    @PutMapping("/audit/{id}")
    @CheckAuthorization("admin")
    public Share auditById(@PathVariable Integer id, @RequestBody ShareAuditDTO auditDTO) {
        return this.shareService.auditById(id, auditDTO);
    }

}
