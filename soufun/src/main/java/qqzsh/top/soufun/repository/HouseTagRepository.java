package qqzsh.top.soufun.repository;

import org.springframework.data.repository.CrudRepository;
import qqzsh.top.soufun.entity.HouseTag;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-09-02 19:07
 * @Description
 */
public interface HouseTagRepository extends CrudRepository<HouseTag, Long> {
    HouseTag findByNameAndHouseId(String name, Long houseId);

    List<HouseTag> findAllByHouseId(Long id);

    List<HouseTag> findAllByHouseIdIn(List<Long> houseIds);
}
