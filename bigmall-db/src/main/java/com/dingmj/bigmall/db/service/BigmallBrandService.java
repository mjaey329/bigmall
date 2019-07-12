package com.dingmj.bigmall.db.service;

import com.dingmj.bigmall.db.dao.BigmallBrandMapper;
import com.dingmj.bigmall.db.domain.BigmallBrand;
import com.dingmj.bigmall.db.domain.BigmallBrandExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @author DMJ
 * @date 2019-07-10 1:47
 */
@Service
public class BigmallBrandService {

    private final BigmallBrand.Column[] result = {BigmallBrand.Column.id,BigmallBrand.Column.name, BigmallBrand.Column.desc, BigmallBrand.Column.picUrl, BigmallBrand.Column.floorPrice};

    @Autowired
    private BigmallBrandMapper brandMapper;

    public List<BigmallBrand> querySelective(String id,String name,Integer page,Integer limit,String order,String desc){
        BigmallBrandExample example = new BigmallBrandExample();
        BigmallBrandExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)){
            criteria.andIdEqualTo(Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)){
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(desc)){
            example.setOrderByClause(order + " " + desc);
        }

        PageHelper.startPage(page,limit);
        return brandMapper.selectByExampleSelective(example,result);
    }

    public List<BigmallBrand> query(Integer page,Integer limit,String order,String desc){
        BigmallBrandExample example = new BigmallBrandExample();
        example.or().andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(order) && !StringUtils.isEmpty(desc)){
            example.setOrderByClause(order + " " + desc);
        }

        PageHelper.startPage(page,limit);

        return brandMapper.selectByExample(example);
    }

    public List<BigmallBrand> query(Integer page, Integer limit){
        return query(page,limit,null,null);
    }

    public BigmallBrand findById(Integer id){
        return brandMapper.selectByPrimaryKey(id);
    }

    public int updateById(BigmallBrand brand){
        brand.setUpdateTime(LocalDateTime.now());
        return brandMapper.updateByPrimaryKeySelective(brand);

    }

    public void deleteById(Integer id){
       brandMapper.deleteByPrimaryKey(id);
    }

    public void add(BigmallBrand brand){
        brand.setAddTime(LocalDateTime.now());
        brand.setUpdateTime(LocalDateTime.now());
        brandMapper.insertSelective(brand);
    }

    public List<BigmallBrand> all(){
        BigmallBrandExample example = new BigmallBrandExample();
        example.or().andDeletedEqualTo(false);
        return brandMapper.selectByExample(example);
    }

    public List<BigmallBrand> findBrand(String name){
        BigmallBrandExample example = new BigmallBrandExample();
        example.or().andNameEqualTo(name);
        return brandMapper.selectByExample(example);
    }

}
