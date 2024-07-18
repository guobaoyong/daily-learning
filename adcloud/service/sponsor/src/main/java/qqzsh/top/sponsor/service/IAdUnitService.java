package qqzsh.top.sponsor.service;

import qqzsh.top.common.exception.AdException;
import qqzsh.top.sponsor.vo.*;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 13:49
 * @Description 推广单元接口
 */
public interface IAdUnitService {

    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeywordRequest request)
            throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request)
            throws AdException;

    AdUnitDistrictResponse createUnitDistrict(AdUnitDistrictRequest request)
            throws AdException;

    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request)
            throws AdException;

}
