package qqzsh.top.addressbook.controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import qqzsh.top.addressbook.entity.PhoneBook;
import qqzsh.top.addressbook.entity.R;
import qqzsh.top.addressbook.service.PhoneBookService;
import qqzsh.top.addressbook.util.JwtUtils;
import qqzsh.top.addressbook.util.PinYinUtil;
import qqzsh.top.addressbook.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-16 17:04
 * @Description 通讯录Controller类
 */
@RestController
@RequestMapping("/addressbook")
public class IndexController {

    @Autowired
    private PhoneBookService phoneBookService;

    /**
     * 查询所有电话簿信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/loadAll")
    public R loadAll(HttpServletRequest request) {
        //根据token获取用户id
        Claims claims = JwtUtils.validateJWT(request.getHeader("token")).getClaims();
        Map<String,Object> map=new LinkedHashMap<>();
        char []letters={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','#'};
        for(int i=0;i<letters.length;i++){
            String letter=String.valueOf(letters[i]);
            List<PhoneBook> phoneBooks = phoneBookService.loadByInitial(letter,Integer.parseInt(claims.getId()));
            if(phoneBooks.size()>0){
                map.put(letter,phoneBooks);
            }
        }
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("data",map);
        return R.ok(resultMap);
    }

    /**
     * 添加或者修改通讯记录
     * @param phoneBook
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public R save(@RequestBody PhoneBook phoneBook,
                  HttpServletRequest request)throws Exception{
        //根据token获取用户id
        Claims claims = JwtUtils.validateJWT(request.getHeader("token")).getClaims();
        phoneBook.setUserId(Integer.parseInt(claims.getId()));
        int resultTotal=0;
        String initial=String.valueOf(PinYinUtil.getPinYin(phoneBook.getName()).charAt(0)).toUpperCase();
        if(StringUtil.isAlpha(initial)){
            phoneBook.setInitial(initial);
        }else{
            phoneBook.setInitial("#");
        }
        if(phoneBook.getId()==null){
            resultTotal=phoneBookService.add(phoneBook);
        }else{
            resultTotal=phoneBookService.update(phoneBook);
        }
        if(resultTotal>0){
            return R.ok();
        }else{
            return R.error(-1,"保存失败，请联系管理员");
        }
    }

    /**
     * 根据id查询电话簿信息
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById")
    public R findById(Integer id)throws Exception{
        PhoneBook phoneBook=phoneBookService.findById(id);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("phoneBook",phoneBook);
        return R.ok(resultMap);
    }

    /**
     * 根据id删除记录
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public R delete(Integer id)throws Exception{
        int resultTotal=phoneBookService.delete(id);
        if(resultTotal>0){
            return R.ok();
        }else{
            return R.error(-1,"删除失败，请联系管理员");
        }
    }

}
