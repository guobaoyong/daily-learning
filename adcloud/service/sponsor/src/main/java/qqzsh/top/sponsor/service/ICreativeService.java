package qqzsh.top.sponsor.service;

import qqzsh.top.sponsor.vo.CreativeRequest;
import qqzsh.top.sponsor.vo.CreativeResponse;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 13:58
 * @Description 创意接口
 */
public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request);
}
