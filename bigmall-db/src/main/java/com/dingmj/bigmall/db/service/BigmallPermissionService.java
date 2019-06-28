package com.dingmj.bigmall.db.service;

import com.dingmj.bigmall.db.dao.BigmallPermissionMapper;
import com.dingmj.bigmall.db.domain.BigmallPermission;
import com.dingmj.bigmall.db.domain.BigmallPermissionExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author DMJ
 * @date 2019-06-24 1:17
 */
@Service
public class BigmallPermissionService {

    @Resource
    private BigmallPermissionMapper permissionMapper;

    public Set<String> queryByRoleIds(Integer[] roleIds){
        Set<String> permissions = new HashSet<String>();
        if (roleIds.length == 0){
            return permissions;
        }

        BigmallPermissionExample example = new BigmallPermissionExample();
        example.or().andRoleIdIn(Arrays.asList(roleIds)).andDeletedEqualTo(false);
        List<BigmallPermission> permissionList = permissionMapper.selectByExample(example);

        for (BigmallPermission permission : permissionList){
            permissions.add(permission.getPermission());
        }

        return permissions;
    }

    public Set<String> queryByRoleId(Integer roleId){
        Set<String> permissions = new HashSet<String>();
        if (roleId ==null){
            return permissions;
        }

        BigmallPermissionExample example = new BigmallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        List<BigmallPermission> permissionList = permissionMapper.selectByExample(example);
        for (BigmallPermission permission :permissionList){
            permissions.add(permission.getPermission());
        }
        return permissions;
    }

    public boolean checkSuperPermission(Integer roleId){
        if (roleId == null){
            return false;
        }
        BigmallPermissionExample example = new BigmallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andPermissionEqualTo("*").andDeletedEqualTo(false);
        return permissionMapper.countByExample(example) != 0;
    }

    public void deleteByRoleId(Integer roleId){
        BigmallPermissionExample example = new BigmallPermissionExample();
        example.or().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        permissionMapper.logicalDeleteByExample(example);
    }

    public void add(BigmallPermission permission){
        permission.setAddTime(LocalDateTime.now());
        permission.setUpdateTime(LocalDateTime.now());
        permissionMapper.insertSelective(permission);
    }
}
