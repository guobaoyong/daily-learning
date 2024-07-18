package qqzsh.top.soufun.repository;

import org.springframework.data.repository.CrudRepository;
import qqzsh.top.soufun.entity.HousePicture;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-09-02 19:06
 * @Description
 */
public interface HousePictureRepository extends CrudRepository<HousePicture, Long> {
    List<HousePicture> findAllByHouseId(Long id);
}
