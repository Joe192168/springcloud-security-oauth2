package com.joe.service;

import com.joe.dao.UserDao;
import com.joe.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: springboot-learn
 * @description:
 * @author: xiaoqiaohui
 * @create: 2019-12-30 18:31
 **/
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //从数据库查询用户信息
        UserDto userDto = userDao.getUserByUsername(username);
        if (userDto == null){
            //如果用户查不到，返回null，由provider来抛异常
            return null;
        }
        System.out.println("username="+username);
        //根据用户id查询用户权限
        List<String> permissionlist = userDao.getPremissionByUserId(userDto.getId());
        //将permissionlist转成数组
        String[] permissionArray = new String[permissionlist.size()];
        permissionlist.toArray(permissionArray);
        UserDetails userDetails = User.withUsername(userDto.getUsername()).password(userDto.getPassword()).authorities(permissionArray).build();
        return userDetails;
    }
}
