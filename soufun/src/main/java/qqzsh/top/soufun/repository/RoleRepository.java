package qqzsh.top.soufun.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import qqzsh.top.soufun.entity.Role;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-30 10:26
 * @description 角色数据DAO
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    List<Role> findRolesByUserId(Long userId);

    Long deleteByUserId(Long userId);

    @Modifying
    @Query("update Role as role set role.name = :name where role.userId = :id")
    void updateById(@Param(value = "id") Long id, @Param(value = "name") String name);

}
