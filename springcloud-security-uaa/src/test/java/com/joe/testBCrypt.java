package com.joe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: springcloud-security-oauth2
 * @description:
 * @author: xiaoqiaohui
 * @create: 2020-01-07 14:11
 **/
@RunWith(SpringRunner.class)
public class testBCrypt {

    @Test
    public void testJiaMi(){
        //对原始密码加密
        String hastr = BCrypt.hashpw("123",BCrypt.gensalt());
        System.out.println("加密后："+hastr);
        //校验原始密码和加密后的是否一致
        boolean checkpw = BCrypt.checkpw("123",hastr);
        System.out.println("校验结果："+checkpw);
    }

}
