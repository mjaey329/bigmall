package com.dingmj.bigmall.db.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author DMJ
 * @date 2019-07-11 16:03
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BrandTest {
    @Autowired
    private BigmallBrandService brandService;

    @Test
    public void findById(){
//        brandService.findById(1001000);
        System.out.println(brandService.findById(1001000));
    }
}
