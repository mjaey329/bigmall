package com.dingmj.bigmall.db.service;

import com.dingmj.bigmall.db.dao.BigmallCategoryMapper;
import com.dingmj.bigmall.db.domain.BigmallCategory;
import com.dingmj.bigmall.db.domain.BigmallCategoryExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**<h1>类目</h1>
 * @author DMJ
 * @date 2019-07-11 15:51
 */
@Service
public class BigmallCategoryService {
    @Autowired
    private BigmallCategoryMapper categoryMapper;

    private final BigmallCategory.Column[] result = { BigmallCategory.Column.id , BigmallCategory.Column.name, BigmallCategory.Column.iconUrl};

    public List<BigmallCategory> queryL1WitoutRecommend(int offset , int limit){
        BigmallCategoryExample example = new BigmallCategoryExample();
        example.or().andLevelEqualTo("L1").andNameNotEqualTo("推荐").andDeletedEqualTo(false);
        PageHelper.startPage(offset,limit);
        return categoryMapper.selectByExample(example);
    }

    public List<BigmallCategory> queryL1 (int offset,int limit){
        BigmallCategoryExample example = new BigmallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        PageHelper.startPage(offset,limit);
        return categoryMapper.selectByExample(example);
    }

    public List<BigmallCategory> queryL1(){
        BigmallCategoryExample example = new BigmallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    public List<BigmallCategory> queryByPid(Integer pid){
        BigmallCategoryExample example = new BigmallCategoryExample();
        example.or().andPidEqualTo(pid).andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    public List<BigmallCategory> queryL2ById(List<Integer> ids){
        BigmallCategoryExample example = new BigmallCategoryExample();
        example.or().andIdIn(ids).andLevelEqualTo("L2").andDeletedEqualTo(false);
        return categoryMapper.selectByExample(example);
    }

    public BigmallCategory findById(Integer id){
        return categoryMapper.selectByPrimaryKey(id);
    }

    public List<BigmallCategory> querySelective(String id,String name,Integer page,Integer limit,String sort,String order){
        BigmallCategoryExample example = new BigmallCategoryExample();
        BigmallCategoryExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(id)){
            criteria.andIdEqualTo(Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)){
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)){
            example.orderBy(sort + " " + order);
        }
        PageHelper.startPage(page,limit);
        return categoryMapper.selectByExample(example);
    }

    public int updateById(BigmallCategory category){
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    public void deleteById(Integer id){
        categoryMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(BigmallCategory category){
        category.setAddTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.insertSelective(category);
    }

    public List<BigmallCategory> queryResult(){
        BigmallCategoryExample example = new BigmallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return categoryMapper.selectByExampleSelective(example,result);
    }

}
