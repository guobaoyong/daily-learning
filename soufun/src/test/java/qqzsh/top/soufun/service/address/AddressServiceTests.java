package qqzsh.top.soufun.service.address;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import qqzsh.top.soufun.ApplicationTests;
import qqzsh.top.soufun.service.ServiceResult;
import qqzsh.top.soufun.service.house.IAddressService;
import qqzsh.top.soufun.service.search.BaiduMapLocation;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-09-04 9:05
 * @Description
 */
public class AddressServiceTests extends ApplicationTests {
    @Autowired
    private IAddressService addressService;

    @Test
    public void testGetMapLocation() {
        String city = "北京";
        String address = "北京市昌平区巩华家园1号楼2单元";
        ServiceResult<BaiduMapLocation> serviceResult = addressService.getBaiduMapLocation(city, address);

        Assert.assertTrue(serviceResult.isSuccess());

        Assert.assertTrue(serviceResult.getResult().getLongitude() > 0 );
        Assert.assertTrue(serviceResult.getResult().getLatitude() > 0 );

    }
}
