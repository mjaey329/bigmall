package com.dingmj.bigmall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.dingmj.bigmall.db.dao.BigmallLogMapper;
import com.dingmj.bigmall.db.domain.BigmallAdminExample;
import com.dingmj.bigmall.db.domain.BigmallLog;
import com.dingmj.bigmall.db.domain.BigmallLogExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <h1>日志服务类</h1>
 * @author DMJ
 * @date 2019-06-29 16:25
 */
@Service
public class BigmallLogService {
    @Autowired
    private BigmallLogMapper logMapper;

    public void deleteById(Integer id){logMapper.deleteByPrimaryKey(id);}

    public void add(BigmallLog log){
        log.setAddTime(LocalDateTime.now());
        log.setUpdateTime(LocalDateTime.now());
        logMapper.insertSelective(log);
    }

    public List<BigmallLog> querySelective(String name , Integer page,Integer size,String sort,String order){
        BigmallLogExample example = new BigmallLogExample();
        BigmallLogExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)){
            criteria.andAdminLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page,size);

        return logMapper.selectByExample(example);
    }

}
