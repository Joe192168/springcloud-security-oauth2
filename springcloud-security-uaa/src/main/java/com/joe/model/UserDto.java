package com.joe.model;

import lombok.Data;

/**
 * @program: springboot-learn
 * @description:
 * @author: xiaoqiaohui
 * @create: 2019-12-30 18:48
 **/
@Data
public class UserDto {

    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;

}
