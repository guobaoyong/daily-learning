package qqzsh.top.soufun.repository;

import org.springframework.data.repository.CrudRepository;
import qqzsh.top.soufun.entity.SupportAddress;

import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-30 11:51
 * @description 城市地址DAO
 */
public interface SupportAddressRepository extends CrudRepository<SupportAddress, Long> {

    /**
     * 获取所有对应行政级别的信息
     * @return
     */
    List<SupportAddress> findAllByLevel(String level);

    SupportAddress findByEnNameAndLevel(String enName, String level);

    SupportAddress findByEnNameAndBelongTo(String enName, String belongTo);

    List<SupportAddress> findAllByLevelAndBelongTo(String level, String belongTo);

    SupportAddress findByCnName(String name);

    List<SupportAddress> findAll();
}
