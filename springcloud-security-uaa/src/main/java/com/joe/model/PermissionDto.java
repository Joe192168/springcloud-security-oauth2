package com.joe.model;

import lombok.Data;

/**
 * @program: springboot-learn
 * @description:
 * @author: xiaoqiaohui
 * @create: 2019-12-31 12:47
 **/
@Data
public class PermissionDto {

    private String id;
    private String code;
    private String description;
    private String url;
    private String create_time;
    private String update_time;

}
