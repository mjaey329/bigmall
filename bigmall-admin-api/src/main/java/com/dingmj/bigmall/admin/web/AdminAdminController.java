package com.dingmj.bigmall.admin.web;

import com.alibaba.druid.util.StringUtils;

import com.dingmj.bigmall.admin.annotation.RequiresPermissionsDesc;
import com.dingmj.bigmall.admin.service.LogHelper;
import com.dingmj.bigmall.core.util.RegexUtil;
import com.dingmj.bigmall.core.util.ResponseUtil;
import com.dingmj.bigmall.core.util.bcrypt.BCryptPasswordEncoder;
import com.dingmj.bigmall.db.domain.BigmallAdmin;
import com.dingmj.bigmall.db.service.BigmallAdminService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

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
    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("admin:admin:list")
    @RequiresPermissionsDesc(menu = {"系统管理","管理员管理"},button = "查询")
    @GetMapping(value = "/list")
    public Object list(String username,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @RequestParam(defaultValue = "add_time") String sort,
                             @RequestParam(defaultValue = "desc") String order){

        List<BigmallAdmin> adminList = adminService.querySelective(username,page,limit,sort,order);

        return ResponseUtil.okList(adminList);
    }

    /**
     * <h2>验证用户</h2>
     * @param admin
     * @return
     */
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
    @RequiresPermissions(value = "admin:admin:create")
    @RequiresPermissionsDesc(menu = {"系统管理","管理员管理"},button = "添加")
    @PostMapping(value = "/create")
    public Object create(@RequestBody BigmallAdmin admin){
        Object error= validate(admin);
        if (error != null){
            return error;
        }

        String username = admin.getUsername();
        List<BigmallAdmin> adminList = adminService.findAdmin(username);
        if (adminList.size() > 0){
            return ResponseUtil.fail(ADMIN_NAME_EXIST,"管理员名称重复");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoderPassword = encoder.encode(admin.getPassword());
        admin.setPassword(encoderPassword);
        adminService.add(admin);
        logHelper.logAuthSucceed("添加管理员",username);
        return ResponseUtil.ok(admin);
    }

    @RequiresPermissions(value = "admin:admin:read")
    @RequiresPermissionsDesc(menu = {"系统管理","管理员管理"},button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id){
        BigmallAdmin admin = adminService.findById(id);
        return ResponseUtil.ok(admin);
    }
    @PostMapping("/update")
    public Object update(@RequestBody BigmallAdmin admin){
        Object error = validate(admin);
        if (error != null){
            return error;
        }

        Integer anotherAdminId = admin.getId();
        if (anotherAdminId == null){
            return ResponseUtil.badArgument();
        }

        if (adminService.updateById(admin) == 0) {
            return ResponseUtil.updateDataFailed();
        }

        logHelper.logAuthSucceed("更新管理员信息",admin.getUsername());
        return ResponseUtil.ok(admin);
    }

    @RequiresPermissions(value = "admin:admin:delete")
    @RequiresPermissionsDesc(menu = {"系统管理","管理员管理"},button = "删除")
    @PostMapping("/delete")
    public Object delete (@RequestBody BigmallAdmin admin){
        Integer anotherAdminId = admin.getId();
        if (anotherAdminId == null){
            return ResponseUtil.badArgument();
        }

        return ResponseUtil.ok();

    }



}
