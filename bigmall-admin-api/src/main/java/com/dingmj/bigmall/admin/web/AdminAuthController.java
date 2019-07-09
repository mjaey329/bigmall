package com.dingmj.bigmall.admin.web;

import com.dingmj.bigmall.admin.service.LogHelper;
import com.dingmj.bigmall.admin.util.Permission;
import com.dingmj.bigmall.admin.util.PermissionUtil;
import com.dingmj.bigmall.core.util.JacksonUtil;
import com.dingmj.bigmall.core.util.ResponseUtil;
import com.dingmj.bigmall.db.domain.BigmallAdmin;
import com.dingmj.bigmall.db.service.BigmallAdminService;
import com.dingmj.bigmall.db.service.BigmallPermissionService;
import com.dingmj.bigmall.db.service.BigmallRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.apache.shiro.subject.Subject;
import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.*;

import static com.dingmj.bigmall.admin.util.AdminResponseCode.ADMIN_INVALID_ACCOUNT;

/**
 * @author DMJ
 * @date 2019-07-09 16:57
 */
@RestController
@RequestMapping("/admin/auth")
@Validated
public class AdminAuthController {
    private final Log logger = LogFactory.getLog(AdminAuthController.class);

    @Autowired
    private BigmallAdminService adminService;
    @Autowired
    private BigmallRoleService roleService;
    @Autowired
    private BigmallPermissionService permissionService;
    @Autowired
    private LogHelper logHelper;

    /**
     * <h2>登录</h2>
     * @param body
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody String body, HttpServletRequest request){
        String username = JacksonUtil.parseString(body,"username");
        String password = JacksonUtil.parseString(body,"password");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return ResponseUtil.badArgument();
        }

        Subject currentUser = SecurityUtils.getSubject();
        Boolean rememberMe = true;
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,rememberMe);
        try {
            currentUser.login(token);
        }catch (UnknownAccountException ex1){
            logHelper.logAuthFail("登录", "用户帐号或密码不正确");
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "用户帐号或密码不正确");
        } catch (LockedAccountException lae) {
            logHelper.logAuthFail("登录", "用户帐号已锁定不可用");
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "用户帐号已锁定不可用");

        }
        catch (AuthenticationException ae) {
            logHelper.logAuthFail("登录", "认证失败");
            return ResponseUtil.fail(ADMIN_INVALID_ACCOUNT, "认证失败");
        }

        currentUser = SecurityUtils.getSubject();
        BigmallAdmin admin = (BigmallAdmin) currentUser.getPrincipal();

        //IpUtil
        admin.setLastLoginIp("0.0.0.0");

        admin.setLastLoginTime(LocalDateTime.now());
        adminService.updateById(admin);

        logHelper.logAuthSucceed("登录");

        //返回参数
        //userInfo
        Map<String, Object> adminInfo = new HashMap<String, Object>();
        adminInfo.put("nickName", admin.getUsername());
        adminInfo.put("avatar", admin.getAvatar());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", currentUser.getSession().getId());
        result.put("adminInfo", adminInfo);
        return ResponseUtil.ok(result);
    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public Object logout(){
        Subject currentUser = SecurityUtils.getSubject();
        logHelper.logAuthSucceed("退出");
        currentUser.logout();
        return ResponseUtil.ok();
    }

    @RequiresAuthentication
    @GetMapping("/info")
    public Object info(){
        Subject currentUser = SecurityUtils.getSubject();
        BigmallAdmin admin = (BigmallAdmin) currentUser.getPrincipal();

        Map<String,Object> data = new HashMap<>();
        data.put("name",admin.getUsername());
        data.put("avatar",admin.getAvatar());

        Integer[] roleIds = admin.getRoleIds();
        Set<String> roles = roleService.queryByIds(roleIds);
        Set<String> permissions = permissionService.queryByRoleIds(roleIds);
        data.put("roles",roleIds);
        data.put("permission",toAPI(permissions));

        return ResponseUtil.ok(data);
    }

    @Autowired
    private ApplicationContext context;
    private HashMap<String, String> systemPermissionsMap = null;

    private Collection<String> toAPI(Set<String> permissions) {
        if (systemPermissionsMap == null) {
            systemPermissionsMap = new HashMap<>();
            final String basicPackage = "com.dingmj.bigmall.admin";
            List<Permission> systemPermissions = PermissionUtil.listPermission(context, basicPackage);
            for (Permission permission : systemPermissions) {
                String perm = permission.getRequiresPermissions().value()[0];
                String api = permission.getApi();
                systemPermissionsMap.put(perm, api);
            }
        }

        Collection<String> apis = new HashSet<>();
        for (String perm : permissions) {
            String api = systemPermissionsMap.get(perm);
            apis.add(api);

            if (perm.equals("*")) {
                apis.clear();
                apis.add("*");
                return apis;
//                return systemPermissionsMap.values();

            }
        }
        return apis;
    }

    @GetMapping("/401")
    public Object page401(){return ResponseUtil.unlogin();}

    @GetMapping("/405")
    public Object page405(){return ResponseUtil.unauthz();}

    @GetMapping("/index")
    public Object pageIndex() {
        return ResponseUtil.ok();
    }

    @GetMapping("/403")
    public Object page403() {
        return ResponseUtil.unauthz();
    }
}
