package com.joe.dao;

import com.joe.model.PermissionDto;
import com.joe.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springboot-learn
 * @description:
 * @author: xiaoqiaohui
 * @create: 2019-12-30 18:51
 **/
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDto getUserByUsername(String username){
        String sql = "select id,username,password,fullname,mobile from t_user where username = ?";
        //链接数据库查询用户
        List<UserDto> list = jdbcTemplate.query(sql,new Object[]{username},new BeanPropertyRowMapper<>(UserDto.class));
        if(!CollectionUtils.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }

    /**
     * 根据用户id查询用户权限
     * @param userid
     * @return
     */
    public List<String> getPremissionByUserId(String userid){
        String sql = "SELECT * FROM t_permission tp where tp.id in(\n" +
                "\n" +
                "select permission_id from t_role_permission trp where trp.role_id in(\n" +
                "select tur.role_id from t_user_role tur where tur.user_id=?)\n" +
                "\n" +
                ")";
        //链接数据库查询用户
        List<PermissionDto> list = jdbcTemplate.query(sql,new Object[]{userid},new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permissionlist = new ArrayList<>();
        list.forEach(a->permissionlist.add(a.getCode()));
        return permissionlist;
    }

}
