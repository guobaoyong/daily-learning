package qqzsh.top.addressbook.service;

import qqzsh.top.addressbook.entity.PhoneBook;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-16 19:47
 * @Description 通讯录Service接口
 */
public interface PhoneBookService {

    /**
     * 根据姓名首字母查询通讯录记录
     * @param initial
     * @return
     */
    List<PhoneBook> loadByInitial(String initial,Integer userId);

    /**
     * 添加通讯记录
     * @param phoneBook
     * @return
     */
    Integer add(PhoneBook phoneBook);

    /**
     * 修改信息
     * @param phoneBook
     * @return
     */
    Integer update(PhoneBook phoneBook);

    /**
     * 删除信息
     * @param id
     * @return
     */
    Integer delete(Integer id);

    /**
     * 根据id查询通讯录记录
     * @param id
     * @return
     */
    PhoneBook findById(Integer id);

}
