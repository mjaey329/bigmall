package com.dingmj.bigmall.admin.web;

import com.alibaba.druid.util.StringUtils;

import com.dingmj.bigmall.core.util.RegexUtil;
import com.dingmj.bigmall.core.util.ResponseUtil;
import com.dingmj.bigmall.db.domain.BigmallAdmin;
import com.dingmj.bigmall.db.service.BigmallAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.dingmj.bigmall.admin.util.AdminResponseCode.*;
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

    private Object validate(BigmallAdmin admin){
        String name = admin.getUsername();
        if (StringUtils.isEmpty(name)){
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isUsername(name)){
            return ResponseUtil.fail(ADMIN_INVALID_NAME,"管理员名称不符合规定");
        }
        String password = admin.getPassword();
        if (StringUtils.isEmpty(password) || password.length() < 6){
            return ResponseUtil.fail(ADMIN_INVALID_PASSWORD,"管理员密码长度不能小于6");
        }
        return null;
    }

    /**
     * <h1>添加管理员</h1>
     * @param admin
     * @return {@link ResponseUtil}
     */
    @PostMapping(value = "/create")
    public Object create(@RequestBody BigmallAdmin admin){
        System.out.println("添加管理员用户中........");
        Object error= validate(admin);
        if (error != null){
            return error;
        }

        String username = admin.getUsername();
        List<BigmallAdmin> adminList = adminService.findAdmin(username);
        if (adminList.size() > 0){
            return ResponseUtil.fail(ADMIN_NAME_EXIST,"管理员名称重复");
        }

        adminService.add(admin);
        return ResponseUtil.ok(admin);
    }



}
