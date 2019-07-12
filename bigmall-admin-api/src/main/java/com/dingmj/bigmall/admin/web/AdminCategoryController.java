package com.dingmj.bigmall.admin.web;

import com.dingmj.bigmall.admin.annotation.RequiresPermissionsDesc;
import com.dingmj.bigmall.admin.vo.CategoryVo;
import com.dingmj.bigmall.core.util.ResponseUtil;
import com.dingmj.bigmall.db.domain.BigmallCategory;
import com.dingmj.bigmall.db.service.BigmallCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DMJ
 * @date 2019-07-11 16:57
 */
@RestController
@RequestMapping("/admin/category")
@Validated
public class AdminCategoryController {
    @Autowired
    private BigmallCategoryService categoryService;

    @RequiresPermissions("admin:category:list")
    @RequiresPermissionsDesc(menu = {"商场管理","类目管理"},button = "查询")
    @GetMapping("/list")
    public Object list(){
        List<CategoryVo> categoryVoList = new ArrayList<>();
        List<BigmallCategory> categoryList = categoryService.queryByPid(0);
        for (BigmallCategory category : categoryList){
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setId(category.getId());
            categoryVo.setName(category.getName());
            categoryVo.setKeywords(category.getKeywords());
            categoryVo.setDesc(category.getDesc());
            categoryVo.setIconUrl(category.getIconUrl());
            categoryVo.setPicUrl(category.getPicUrl());
            categoryVo.setLevel(category.getLevel());

            List<CategoryVo> children = new ArrayList<>();
            List<BigmallCategory> subCategoryList = categoryService.queryByPid(category.getId());
            for (BigmallCategory subCategory : subCategoryList){
                CategoryVo subCategoryVo = new CategoryVo();
                subCategoryVo.setId(subCategory.getId());
                subCategoryVo.setName(subCategory.getName());
                subCategoryVo.setKeywords(subCategory.getKeywords());
                subCategoryVo.setDesc(subCategory.getDesc());
                subCategoryVo.setIconUrl(subCategory.getIconUrl());
                subCategoryVo.setPicUrl(subCategory.getPicUrl());
                subCategoryVo.setLevel(subCategory.getLevel());

                children.add(subCategoryVo);
            }
            categoryVo.setChildren(children);
            categoryVoList.add(categoryVo);

        }
        return ResponseUtil.ok(categoryVoList);
    }

    private Object validate(BigmallCategory category){
        String name = category.getName();
        if (StringUtils.isEmpty(name)){
            return ResponseUtil.badArgument();
        }

        String level = category.getLevel();
        if (StringUtils.isEmpty(level)){
            return ResponseUtil.badArgument();
        }
        if (!level.equals("L1") && !level.equals("L2")){
            return ResponseUtil.badArgument();
        }

        Integer pid = category.getPid();
        if (level.equals("L2") && (pid ==null)){
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @RequiresPermissions("admin:category:create")
    @RequiresPermissionsDesc(menu = {"商场管理","类目管理"},button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody BigmallCategory category){
        Object error = validate(category);
        if (error != null){
            return error;
        }
        categoryService.add(category);
        return ResponseUtil.ok(category);
    }

    @RequiresPermissions("admin:category:read")
    @RequiresPermissionsDesc(menu = {"商场管理","类目管理"},button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        BigmallCategory category = categoryService.findById(id);
        return ResponseUtil.ok(category);
    }

    @RequiresPermissions("admin:category:update")
    @RequiresPermissionsDesc(menu = {"商场管理","类目管理"},button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody BigmallCategory category){
        Object error = validate(category);
        if (error != null){
            return error;
        }
        if (categoryService.updateById(category) == 0 ){
            return ResponseUtil.updateDataFailed();
        }
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:category:delete")
    @RequiresPermissionsDesc(menu = {"商场管理","类目管理"},button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody BigmallCategory category){
        Integer id = category.getId();
        if (id == null){
            return ResponseUtil.badArgument();
        }
        categoryService.deleteById(id);
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:category:list")
    @GetMapping("/l1")
    public Object catl1(){
        List<BigmallCategory> l1List = categoryService.queryL1();
        List<Map<String,Object >> data = new ArrayList<>(l1List.size());
        for (BigmallCategory category : l1List){
            Map<String,Object> map = new HashMap<>(2);
            map.put("value",category.getId());
            map.put("label",category.getName());
            data.add(map);
        }
        return ResponseUtil.ok(data);
    }

}
