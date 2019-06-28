package com.dingmj.bigmall.admin.shiro;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;


/**
 * @author DMJ
 * @date 2019-06-23 23:06
 */

public class AdminAuthorizingRealm{}

//public class AdminAuthorizingRealm extends AuthorizingRealm {
//
//    private static final Logger log = LoggerFactory.getLogger(AdminAuthorizingRealm.class);
//
//    @Autowired
//    private BigmallAdminService adminService;
//    @Autowired
//    private BigmallRoleService roleService;
//    @Autowired
//    private BigmallPermissionService permissionService;
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        if (principalCollection == null){
//            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
//        }
//
//        BigmallAdmin admin = (BigmallAdmin) getAvailablePrincipal(principalCollection);
//        Integer[] roleIds = admin.getRoleIds();
//        Set<String> roles = roleService.queryByIds(roleIds);
//        Set<String> permissions = permissionService.queryByRoleIds(roleIds);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setRoles(roles);
//        info.setStringPermissions(permissions);
//        return info;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//
//        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
//        String username = upToken.getUsername();
//        String password = new String(upToken.getPassword());
//
//        if (StringUtils.isEmpty(username)){
//            throw new AccountException("用户名不能为空");
//        }
//        if (StringUtils.isEmpty(password)){
//            throw new AccountException("密码不能为空");
//        }
//
//        List<BigmallAdmin> adminList = adminService.findAdmin(username);
//        Assert.state(adminList.size() < 2,"同一个用户名存在两个账户");
//        if (adminList.size() == 0){
//            throw new UnknownAccountException("找不到用户（"+username+"）的帐号信息");
//        }
//        BigmallAdmin admin = adminList.get(0);
//
////        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
////        if (!encoder.matches(password, admin.getPassword())) {
////            throw new UnknownAccountException("找不到用户（"+username+"）的帐号信息");
////        }
//
//        return new SimpleAuthenticationInfo(admin,password,getName());
//    }
//}
