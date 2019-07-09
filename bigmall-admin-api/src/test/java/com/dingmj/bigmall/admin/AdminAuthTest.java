package com.dingmj.bigmall.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author DMJ
 * @date 2019-07-07 18:42
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AdminAuthTest {


//    @Test
//    public void login(){
//
//        String username = "root";
//        String password = "123";
//
//        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//        //2、得到SecurityManager实例 并绑定给SecurityUtils
//        SecurityManager securityManager = factory.getInstance();
//        SecurityUtils.setSecurityManager(securityManager);
//        //GET Subject
//        Subject subject = SecurityUtils.getSubject();
//        //GET Session
//        Session session = subject.getSession();
//        //Creat User
//        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
//        token.setRememberMe(true);
//
//        try {
//            //登录
//            subject.login(token);
//            //获取登录用户
//            String currenUser = subject.getPrincipal().toString();
//            System.out.println("当前登录的用户是: " + currenUser);
//        }catch (UnknownAccountException uae){
//            System.out.println("用户名不存在");
//        }
//
////        currentUser.login(new UsernamePasswordToken(username, password));
//    }
}
