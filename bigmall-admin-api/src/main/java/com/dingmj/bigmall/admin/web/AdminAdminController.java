package com.dingmj.bigmall.admin.web;

import com.bigmall.core.util.ResponseUtil;
import com.dingmj.bigmall.db.domain.BigmallAdmin;
import com.dingmj.bigmall.db.service.BigmallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DMJ
 * @date 2019-06-23 18:20
 */
@RestController
@RequestMapping(value = "/admin/admin")
@Validated
public class AdminAdminController {

    @Autowired
    private BigmallAdminService adminService;


    @GetMapping(value = "/test")
    public String test(){
        return "Hello world!";
    }

    @GetMapping(value = "/list")
    public Object list(String username,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @RequestParam(defaultValue = "add_time") String sort,
                             @RequestParam(defaultValue = "desc") String order){

        List<BigmallAdmin> adminList = adminService.querySelective(username,page,limit,sort,order);

        return ResponseUtil.okList(adminList);
    }



}
