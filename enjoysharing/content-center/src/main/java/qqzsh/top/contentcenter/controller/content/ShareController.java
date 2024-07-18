package qqzsh.top.contentcenter.controller.content;

import com.alibaba.nacos.client.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import qqzsh.top.contentcenter.auth.CheckLogin;
import qqzsh.top.contentcenter.domain.dto.content.ShareDTO;
import qqzsh.top.contentcenter.domain.entity.content.Share;
import qqzsh.top.contentcenter.service.content.ShareService;
import qqzsh.top.contentcenter.util.JwtOperator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-08 21:44
 * @Description 分享controller
 */
@RestController
@RequestMapping("/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {

    private final ShareService shareService;
    private final JwtOperator jwtOperator;

    @GetMapping("/{id}")
    @CheckLogin
    public ShareDTO findById(@PathVariable Integer id) {
        return this.shareService.findById(id);
    }

    @GetMapping("/q")
    public PageInfo<Share> q(
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "X-Token", required = false) String token) {
        if (pageSize > 100) {
            pageSize = 100;
        }
        Integer userId = null;
        if (StringUtils.isNotBlank(token)) {
            Claims claims = this.jwtOperator.getClaimsFromToken(token);
            userId = (Integer) claims.get("id");
        }
        return this.shareService.q(title, pageNo, pageSize, userId);
    }

    @GetMapping("/exchange/{id}")
    @CheckLogin
    public Share exchangeById(@PathVariable Integer id, HttpServletRequest request) {
        return this.shareService.exchangeById(id, request);
    }


}
