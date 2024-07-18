package qqzsh.top.search.service;

import qqzsh.top.search.vo.SearchRequest;
import qqzsh.top.search.vo.SearchResponse;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 17:34
 * @Description
 */
public interface ISearch {

    SearchResponse fetchAds(SearchRequest request);
}

