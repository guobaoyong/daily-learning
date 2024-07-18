package qqzsh.top.soufun.repository;

import org.springframework.data.repository.CrudRepository;
import qqzsh.top.soufun.entity.Subway;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-09-02 18:54
 * @Description
 */
public interface SubwayRepository extends CrudRepository<Subway, Long> {
    List<Subway> findAllByCityEnName(String cityEnName);

    Subway findByName(String name);

    Subway findById(Long id);
}
