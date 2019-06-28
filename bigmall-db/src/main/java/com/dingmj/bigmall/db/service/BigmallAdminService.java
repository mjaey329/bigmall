package com.dingmj.bigmall.db.service;

import com.dingmj.bigmall.db.dao.BigmallAdminMapper;
import com.dingmj.bigmall.db.domain.BigmallAdmin;
import com.dingmj.bigmall.db.domain.BigmallAdminExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.List;

import static com.dingmj.bigmall.db.domain.BigmallAdmin.*;

/**
 * <h1>AdminService</h1>
 * @author DMJ
 * @date 2019-06-20 1:30
 */
@Service
public class BigmallAdminService {

    private final Column[] result = new Column[]{Column.id,Column.username, Column.avatar, Column.roleIds};

    @Resource
    private BigmallAdminMapper adminMapper;

    public List<BigmallAdmin> findAdmin(String username){
        BigmallAdminExample adminExample = new BigmallAdminExample();
        adminExample.or().andUsernameEqualTo(username).andDeletedEqualTo(false);
        return adminMapper.selectByExample(adminExample);
    }


    public BigmallAdmin findAdmin(Integer id){
        return adminMapper.selectByPrimaryKey(id);
    }

    public List<BigmallAdmin> querySelective(String username,Integer page,Integer limit,String sort,String order){
        BigmallAdminExample example = new BigmallAdminExample();
        BigmallAdminExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(username)){
            criteria.andUsernameLike("%"+ username + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page,limit);

        return adminMapper.selectByExampleSelective(example,result);
    }

    public int updateById(BigmallAdmin admin){
        admin.setUpdateTime(LocalDateTime.now());
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    public void deleteById(Integer id){
        adminMapper.logicalDeleteByPrimaryKey(id);
    }

    public BigmallAdmin findById(Integer id){
        return adminMapper.selectByPrimaryKeySelective(id,result);
    }

    public List<BigmallAdmin> all(){
        BigmallAdminExample example = new BigmallAdminExample();
        example.or().andDeletedEqualTo(false);
        return adminMapper.selectByExample(example);
    }
}
