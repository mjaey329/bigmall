package com.dingmj.bigmall.db.service;

import com.dingmj.bigmall.db.domain.BigmallRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @author DMJ
 * @date 2019-06-23 2:14
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class roleServiceTest {
    @Autowired
    private BigmallRoleService roleService;

    @Test
    public void add(){
        BigmallRole role = new BigmallRole();
        role.setName("超级管理员");
        role.setDesc("所有模块的权限");
        role.setDeleted(false);
        role.setEnabled(true);
        roleService.add(role);
    }
}
