package qqzsh.top.soufun.service.house;

import qqzsh.top.soufun.entity.SupportAddress;
import qqzsh.top.soufun.service.ServiceMultiResult;
import qqzsh.top.soufun.service.ServiceResult;
import qqzsh.top.soufun.service.search.BaiduMapLocation;
import qqzsh.top.soufun.web.dto.SubwayDTO;
import qqzsh.top.soufun.web.dto.SubwayStationDTO;
import qqzsh.top.soufun.web.dto.SupportAddressDTO;
import qqzsh.top.soufun.web.form.CityForm;
import qqzsh.top.soufun.web.form.SubwayForm;

import java.util.List;
import java.util.Map;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-30 11:53
 * @description 地址服务接口
 */
public interface IAddressService {

    /**
     * 获取所有支持的城市列表
     * @return
     */
    ServiceMultiResult<SupportAddressDTO> findAllCities();

    /**
     * 根据英文简写获取具体区域的信息
     * @param cityEnName
     * @param regionEnName
     * @return
     */
    Map<SupportAddress.Level, SupportAddressDTO> findCityAndRegion(String cityEnName, String regionEnName);

    /**
     * 根据城市英文简写获取该城市所有支持的区域信息
     * @param cityName
     * @return
     */
    ServiceMultiResult findAllRegionsByCityName(String cityName);

    /**
     * 获取该城市所有的地铁线路
     * @param cityEnName
     * @return
     */
    List<SubwayDTO> findAllSubwayByCity(String cityEnName);

    /**
     * 获取地铁线路所有的站点
     * @param subwayId
     * @return
     */
    List<SubwayStationDTO> findAllStationBySubway(Long subwayId);

    /**
     * 获取地铁线信息
     * @param subwayId
     * @return
     */
    ServiceResult<SubwayDTO> findSubway(Long subwayId);

    /**
     * 获取地铁站点信息
     * @param stationId
     * @return
     */
    ServiceResult<SubwayStationDTO> findSubwayStation(Long stationId);

    /**
     * 根据城市英文简写获取城市详细信息
     * @param cityEnName
     * @return
     */
    ServiceResult<SupportAddressDTO> findCity(String cityEnName);

    /**
     * 根据城市以及具体地位获取百度地图的经纬度
     */
    ServiceResult<BaiduMapLocation> getBaiduMapLocation(String city, String address);

    /**
     * 上传百度LBS数据
     */
    ServiceResult lbsUpload(BaiduMapLocation location, String title, String address,
                            long houseId, int price, int area);

    /**
     * 移除百度LBS数据
     * @param houseId
     * @return
     */
    ServiceResult removeLbs(Long houseId);

    /**
     * 保存城市/区域
     * @param city
     * @param region
     */
    void save(String city,List<String> region);

    /**
     * 获得所有城市/区域
     * @return
     */
    List<CityForm> findAll();

    /**
     * 根据id删除城市/区域
     */
    void delete(Long id);

    /**
     * 添加地铁/站点
     */
    void saveSubway(SubwayForm subwayForm, List<String> station);

    /**
     * 获得所有地铁/站点
     */
    List<SubwayForm> findAllSubway();

    void deleteSubway(Long id);
}
