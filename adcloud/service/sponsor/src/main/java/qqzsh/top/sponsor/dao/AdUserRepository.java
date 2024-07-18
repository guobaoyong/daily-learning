package qqzsh.top.sponsor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import qqzsh.top.sponsor.entity.AdUser;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-20 11:02
 * @Description 用户Repository
 */
public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    //根据用户名查找用户记录
    AdUser findByUsername(String username);
}
