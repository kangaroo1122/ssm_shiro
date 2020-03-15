package com.kangaroohy.ssm_shiro;

import com.kangaroohy.ssm_shiro.mapper.UserMapper;
import com.kangaroohy.ssm_shiro.domain.entity.po.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SsmShiroApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testMD5(){
        String hashName = "md5";
        String password = "123456";
        SimpleHash hash = new SimpleHash(hashName, password, "admin", 1024);
        System.out.println(hash.toString());
    }
}
