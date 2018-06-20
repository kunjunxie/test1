package com.test.service;

import com.test.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@ContextConfiguration("classpath*:/test-context.xml")   //启动spring容器;
public class UserServiceTest extends AbstractTestNGSpringContextTests{
    private UserService userService;
    @Autowired   //注入spring容器中的Bean;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    //标注测试方法;
    @Test
    public void hasMatchUser(){
        boolean b1=userService.hashMatchUser("admin","123456");
        boolean b2=userService.hashMatchUser("admin","1111");
        assertTrue(b1);
         assertTrue(b2);
    }
    @Test
    public void findUserByUserName(){
        User user=userService.findUserByUserName("admin");
        assertEquals(user.getUserName(),"admin");
    }
    @org.testng.annotations.Test
    public void testFindUserByUserName()throws Exception{
        for(int i =0; i< 100;i++) {
            User user = userService.findUserByUserName("admin");
            Assert.assertEquals(user.getUserName(), "admin");
        }

    }


    @org.testng.annotations.Test
    public void testAddLoginLog() {
        User user = userService.findUserByUserName("admin");
        user.setUserId(1);
        user.setUserName("admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
    }
}
