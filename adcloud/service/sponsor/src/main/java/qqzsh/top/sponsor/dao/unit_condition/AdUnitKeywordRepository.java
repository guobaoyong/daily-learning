package qqzsh.top.sponsor.dao.unit_condition;

import org.springframework.data.jpa.repository.JpaRepository;
import qqzsh.top.sponsor.entity.unit_condition.AdUnitKeyword;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 11:05
 * @Description 关键词Repository
 */
public interface AdUnitKeywordRepository extends
        JpaRepository<AdUnitKeyword, Long> {
}
