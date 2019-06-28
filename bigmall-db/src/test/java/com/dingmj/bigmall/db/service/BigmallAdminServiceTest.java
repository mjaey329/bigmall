package com.dingmj.bigmall.db.service;

import com.dingmj.bigmall.db.domain.BigmallAdmin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author DMJ
 * @date 2019-06-20 4:45
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BigmallAdminServiceTest {

    @Autowired
    private BigmallAdminService adminService;

    @Test
    public void findById(){
        System.out.println(adminService.findById(1));
    }

    @Test
    public void findPage(){
        System.out.println(adminService.querySelective(adminService.findById(1).getUsername(),2,1,"add_time","desc"));
    }
}
