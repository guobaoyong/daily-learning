package qqzsh.top.addressbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-11-16 19:39
 * @Description 通讯录实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneBook {

    private Integer id; // 编号

    private String name; // 姓名

    private String phoneNumber; // 手机号码

    private String teleNumber; // 电话号码

    private String workAddress; // 工作单位地址

    private String homeAddress; // 家庭地址

    private String image;  // 头像图片名称

    private String remark; // 备注其他

    private String initial; // 姓名首字母

    private Integer userId; //所属用户

}
