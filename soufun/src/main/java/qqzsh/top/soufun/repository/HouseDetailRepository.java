package qqzsh.top.soufun.repository;

import org.springframework.data.repository.CrudRepository;
import qqzsh.top.soufun.entity.HouseDetail;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-09-02 19:06
 * @Description
 */
public interface HouseDetailRepository extends CrudRepository<HouseDetail, Long> {
    HouseDetail findByHouseId(Long houseId);

    List<HouseDetail> findAllByHouseIdIn(List<Long> houseIds);
}
