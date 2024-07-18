package qqzsh.top.soufun.service.user;

import com.google.common.collect.Lists;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qqzsh.top.soufun.base.LoginUserUtil;
import qqzsh.top.soufun.entity.Role;
import qqzsh.top.soufun.entity.User;
import qqzsh.top.soufun.repository.RoleRepository;
import qqzsh.top.soufun.repository.UserRepository;
import qqzsh.top.soufun.service.IUserService;
import qqzsh.top.soufun.service.ServiceResult;
import qqzsh.top.soufun.web.dto.UserDTO;
import qqzsh.top.soufun.web.form.UserForm;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zsh
 * @site qqzsh.top
 * @create 2019-08-30 10:18
 * @description
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${qiniu.cdn.prefix}")
    private String cdnPrefix;

    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    @Override
    public User findUserByName(String userName) {
        User user = userRepository.findByName(userName);

        if (user == null) {
            return null;
        }

        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty()) {
            throw new DisabledException("权限非法");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }

    @Override
    public ServiceResult<UserDTO> findById(Long userId) {
        User user = userRepository.findOne(userId);
        if (user == null) {
            return ServiceResult.notFound();
        }
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ServiceResult.of(userDTO);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserByTelephone(String telephone) {
        User user = userRepository.findUserByPhoneNumber(telephone);
        if (user == null) {
            return null;
        }
        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty()) {
            throw new DisabledException("权限非法");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }

    @Override
    @Transactional
    public User addUserByPhone(String telephone) {
        User user = new User();
        user.setPhoneNumber(telephone);
        user.setName(telephone.substring(0, 3) + "****" + telephone.substring(7, telephone.length()));
        Date now = new Date();
        user.setCreateTime(now);
        user.setLastLoginTime(now);
        user.setLastUpdateTime(now);
        user = userRepository.save(user);

        Role role = new Role();
        role.setName("USER");
        role.setUserId(user.getId());
        roleRepository.save(role);
        user.setAuthorityList(Lists.newArrayList(new SimpleGrantedAuthority("ROLE_USER")));
        return user;
    }

    @Override
    @Transactional
    public ServiceResult modifyUserProfile(String profile, String value) {
        Long userId = LoginUserUtil.getLoginUserId();
        if (profile == null || profile.isEmpty()) {
            return new ServiceResult(false, "属性不可以为空");
        }
        switch (profile) {
            case "name":
                userRepository.updateUsername(userId, value);
                break;
            case "email":
                userRepository.updateEmail(userId, value);
                break;
            case "password":
                userRepository.updatePassword(userId, this.passwordEncoder.encodePassword(value, userId));
                break;
            default:
                return new ServiceResult(false, "不支持的属性");
        }
        return ServiceResult.success();
    }

    @Override
    @Transactional
    public void save(UserForm userForm) {
        User user = new User();
        user.setName(userForm.getName());
        if (!userForm.getEmail().isEmpty()){
            user.setEmail(userForm.getEmail());
        }
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setStatus(0);
        user.setCreateTime(new Date());
        user.setLastLoginTime(new Date());
        user.setLastUpdateTime(new Date());
        user.setAvatar(this.cdnPrefix + userForm.getCover());
        userRepository.save(user);
        //获取id，更新密码
        userRepository.updatePassword(user.getId(),this.passwordEncoder.encodePassword(userForm.getPassword(),user.getId()));
        //设置角色
        Role role = new Role();
        role.setUserId(user.getId());
        role.setName(userForm.getRole());
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void update(UserForm userForm) {
        User user = userRepository.findOne(userForm.getId());
        user.setName(userForm.getName());
        if (!userForm.getEmail().isEmpty()){
            user.setEmail(userForm.getEmail());
        }
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setLastUpdateTime(new Date());
        //获取id，更新密码
        if (!userForm.getPassword().isEmpty()){
            userRepository.updatePassword(user.getId(),this.passwordEncoder.encodePassword(userForm.getPassword(),user.getId()));
        }
        if (!(userForm.getCover() == null)){
            user.setAvatar(this.cdnPrefix+userForm.getCover());
        }
        userRepository.save(user);
        //设置角色
        roleRepository.updateById(user.getId(),userForm.getRole());
    }

    @Override
    public Role findByUserId(Long userId) {
        List<Role> rolesByUserId = roleRepository.findRolesByUserId(userId);
        if (rolesByUserId.size() == 1){
            return rolesByUserId.get(0);
        }else {
            return null;
        }
    }

    @Override
    @Transactional
    public ServiceResult deleteUser(Long id) {
        userRepository.delete(id);
        roleRepository.deleteByUserId(id);
        return ServiceResult.success();
    }

    @Override
    @Transactional
    public ServiceResult updateStatus(Long userId, int status) {
        userRepository.updateStatus(userId,status);
        return ServiceResult.success();
    }
}
