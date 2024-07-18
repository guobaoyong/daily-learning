package qqzsh.top.project.admin.phonebook.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import qqzsh.top.framework.aspectj.lang.annotation.Excel;
import qqzsh.top.framework.web.domain.BaseEntity;

/**
 * 通讯录对象 t_phonebook
 * 
 * @author zsh
 * @date 2019-11-17
 */
public class TPhonebook extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String name;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String phonenumber;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String telenumber;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String workaddress;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String homeaddress;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String image;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String initial;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userid;

    private String userName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPhonenumber(String phonenumber) 
    {
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber() 
    {
        return phonenumber;
    }
    public void setTelenumber(String telenumber) 
    {
        this.telenumber = telenumber;
    }

    public String getTelenumber() 
    {
        return telenumber;
    }
    public void setWorkaddress(String workaddress) 
    {
        this.workaddress = workaddress;
    }

    public String getWorkaddress() 
    {
        return workaddress;
    }
    public void setHomeaddress(String homeaddress) 
    {
        this.homeaddress = homeaddress;
    }

    public String getHomeaddress() 
    {
        return homeaddress;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setInitial(String initial) 
    {
        this.initial = initial;
    }

    public String getInitial() 
    {
        return initial;
    }
    public void setUserid(Long userid) 
    {
        this.userid = userid;
    }

    public Long getUserid() 
    {
        return userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("phonenumber", getPhonenumber())
            .append("telenumber", getTelenumber())
            .append("workaddress", getWorkaddress())
            .append("homeaddress", getHomeaddress())
            .append("image", getImage())
            .append("remark", getRemark())
            .append("initial", getInitial())
            .append("userid", getUserid())
            .toString();
    }
}
