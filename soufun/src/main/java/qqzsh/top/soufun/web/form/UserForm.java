package qqzsh.top.soufun.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-09-04 19:29
 * @Description 用户添加前台传输
 */
public class UserForm {
    private Long id;

    @NotNull(message = "用户名不允许为空!")
    @Size(min = 1, max = 15, message = "用户名必须在1~15之间")
    private String name;

    private String email;

    private String phoneNumber;

    @NotNull(message = "密码不允许为空!")
    private String password;

    private String role;

    private String cover;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
