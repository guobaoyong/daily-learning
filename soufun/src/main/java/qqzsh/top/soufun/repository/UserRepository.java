package qqzsh.top.soufun.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import qqzsh.top.soufun.entity.User;

import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-29 17:17
 * @description 用户DAO
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String userName);

    User findUserByPhoneNumber(String telephone);

    List<User> findAll();

    //修改用户名
    @Modifying
    @Query("update User as user set user.name = :name where id = :id")
    void updateUsername(@Param(value = "id") Long id, @Param(value = "name") String name);

    //修改邮箱
    @Modifying
    @Query("update User as user set user.email = :email where id = :id")
    void updateEmail(@Param(value = "id") Long id, @Param(value = "email") String email);

    //修改密码
    @Modifying
    @Query("update User as user set user.password = :password where id = :id")
    void updatePassword(@Param(value = "id") Long id, @Param(value = "password") String password);

    //修改状态
    @Modifying
    @Query("update User as user set user.status = :status where id = :id")
    void updateStatus(@Param(value = "id") Long id, @Param(value = "status") int status);

    //获取数据库maxId
    @Query("select id FROM User u WHERE u.id = (SELECT MAX(us.id) FROM User us)")
    Long getMaxId();


}
