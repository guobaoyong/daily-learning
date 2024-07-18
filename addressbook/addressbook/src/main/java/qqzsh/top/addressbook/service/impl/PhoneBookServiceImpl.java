package qqzsh.top.addressbook.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qqzsh.top.addressbook.entity.PhoneBook;
import qqzsh.top.addressbook.mapper.PhoneBookMapper;
import qqzsh.top.addressbook.service.PhoneBookService;

import java.util.List;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-16 19:48
 * @Description 通讯录Service实现类
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhoneBookServiceImpl implements PhoneBookService {

    private final PhoneBookMapper phoneBookMapper;

    @Override
    public List<PhoneBook> loadByInitial(String initial, Integer userId) {
        return phoneBookMapper.loadByInitial(initial, userId);
    }

    @Override
    public Integer add(PhoneBook phoneBook) {
        return phoneBookMapper.add(phoneBook);
    }

    @Override
    public Integer update(PhoneBook phoneBook) {
        return phoneBookMapper.update(phoneBook);
    }

    @Override
    public Integer delete(Integer id) {
        return phoneBookMapper.delete(id);
    }

    @Override
    public PhoneBook findById(Integer id) {
        return phoneBookMapper.findById(id);
    }
}

