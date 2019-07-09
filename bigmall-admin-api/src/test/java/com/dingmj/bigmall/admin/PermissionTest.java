package com.dingmj.bigmall.admin;

import com.dingmj.bigmall.admin.util.Permission;
import com.dingmj.bigmall.admin.util.PermissionUtil;
import com.dingmj.bigmall.admin.vo.PermVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @author DMJ
 * @date 2019-07-05 15:11
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PermissionTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void test(){
        final String basicPackage = "com.dingmj.bigmall.admin";
        List<Permission> permissionList = PermissionUtil.listPermission(context,basicPackage);
        List<PermVo> permVoList = PermissionUtil.listPermVo(permissionList);
        permVoList.stream().forEach(System.out::println);


    }
}
