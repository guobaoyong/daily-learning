package qqzsh.top.soufun.service.search;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import qqzsh.top.soufun.ApplicationTests;
import qqzsh.top.soufun.service.ServiceMultiResult;
import qqzsh.top.soufun.web.form.RentSearch;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-09-03 9:12
 * @Description
 */
public class SearchServiceTests extends ApplicationTests {

    @Autowired
    private ISearchService searchService;

    @Test
    public void testIndex() {
        Long targetHouseId = 15L;
        searchService.index(targetHouseId);
    }

    @Test
    public void testRemove() {
        Long targetHouseId = 15L;
        searchService.remove(targetHouseId);
    }

    @Test
    public void testQuery() {
        RentSearch rentSearch = new RentSearch();
        rentSearch.setCityEnName("bj");
        rentSearch.setStart(0);
        rentSearch.setSize(10);
        rentSearch.setKeywords("国贸");
        ServiceMultiResult<Long> serviceResult = searchService.query(rentSearch);
        Assert.assertTrue(serviceResult.getTotal() > 0);
    }
}
