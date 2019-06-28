package com.dingmj.bigmall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.dingmj.bigmall.db.dao.BigmallRoleMapper;
import com.dingmj.bigmall.db.domain.BigmallRole;
import com.dingmj.bigmall.db.domain.BigmallRoleExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author DMJ
 * @date 2019-06-23 0:54
 */
@Service
public class BigmallRoleService {

    @Resource
    private BigmallRoleMapper roleMapper;

    public Set<String> queryByIds(Integer[] roleIds){
        Set<String> roles = new HashSet<String>();
        if (roleIds.length == 0) {
        return roles;
        }

        BigmallRoleExample example = new BigmallRoleExample();
        example.or().andIdIn(Arrays.asList(roleIds)).andEnabledEqualTo(true).andDeletedEqualTo(false);
        List<BigmallRole> roleList = roleMapper.selectByExample(example);

        for (BigmallRole role : roleList){
            roles.add(role.getName());
        }
        return roles;
    }

    public List<BigmallRole> querySelective(String name, Integer page, Integer limit, String sort, String order){
        BigmallRoleExample example = new BigmallRoleExample();
        BigmallRoleExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)){
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page,limit);

        return roleMapper.selectByExample(example);
    }

    public BigmallRole findById(Integer id){return roleMapper.selectByPrimaryKey(id);}

    public void add(BigmallRole role){
        role.setAddTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insertSelective(role);
    }

    public void deleteById(Integer id){roleMapper.deleteByPrimaryKey(id);}

    public void updateById(BigmallRole role){
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.updateByPrimaryKey(role);
    }

    public boolean checkExist(String name){
        BigmallRoleExample example = new BigmallRoleExample();
        example.or().andNameEqualTo(name).andDeletedEqualTo(false);
        return roleMapper.countByExample(example) != 0;
    }

    public List<BigmallRole> queryAll(){
        BigmallRoleExample example = new BigmallRoleExample();
        example.or().andDeletedEqualTo(false);
        return roleMapper.selectByExample(example);
    }
}
