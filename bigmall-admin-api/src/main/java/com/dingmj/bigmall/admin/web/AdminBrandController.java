package com.dingmj.bigmall.admin.web;

import com.dingmj.bigmall.admin.annotation.RequiresPermissionsDesc;
import com.dingmj.bigmall.core.util.ResponseUtil;
import com.dingmj.bigmall.core.validator.Order;
import com.dingmj.bigmall.core.validator.Sort;
import com.dingmj.bigmall.db.dao.BigmallAdminMapper;
import com.dingmj.bigmall.db.domain.BigmallBrand;
import com.dingmj.bigmall.db.service.BigmallBrandService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import static com.dingmj.bigmall.admin.util.AdminResponseCode.BRAND_NAME_EXIST;

/**
 * @author DMJ
 * @date 2019-07-10 2:38
 */
@RestController
@RequestMapping("/admin/brand")
@Validated
public class AdminBrandController {
    private final Log logger = LogFactory.getLog(AdminBrandController.class);

    @Autowired
    private BigmallBrandService brandService;


    @RequiresPermissions("admin:brand:list")
    @RequiresPermissionsDesc(menu = {"商场管理","品牌管理"},button = "查询")
    @GetMapping(value = "/list")
    public Object list(String id,
                       String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order){
        List<BigmallBrand> brandList = brandService.querySelective(id,name,page,limit,sort,order);
        return ResponseUtil.okList(brandList);
    }

    /**
     * <h2>参数校对</h2>
     * @param brand
     * @return
     */
    private Object validate(BigmallBrand brand){
        String name = brand.getName();
        String desc = brand.getDesc();
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(desc)){
            return ResponseUtil.badArgument();
        }
        BigDecimal f = brand.getFloorPrice();
        if (f == null){
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @RequiresPermissions("admin:brand:creat")
    @RequiresPermissionsDesc(menu = {"商场管理","品牌管理"},button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody BigmallBrand brand){
        Object error = validate(brand);
        if (error != null){
            return error;
        }
        String name = brand.getName();
        List<BigmallBrand> brandList = brandService.findBrand(name);
        if (brandList.size() > 0 ){
            return ResponseUtil.fail(BRAND_NAME_EXIST,"品牌商名称重复");
        }
        brandService.add(brand);
        return ResponseUtil.ok(brand);
    }

    @RequiresPermissions("admin:brand:read")
    @RequiresPermissionsDesc(menu={"商场管理" , "品牌管理"}, button="详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        BigmallBrand brand = brandService.findById(id);
        return ResponseUtil.ok(brand);
    }

    @RequiresPermissions("admin:brand:update")
    @RequiresPermissionsDesc(menu = {"商场管理","品牌管理"},button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody BigmallBrand brand){
        Object error = validate(brand);
        if (error != null){
            return error;
        }
        if(brandService.updateById(brand) == 0 ){
            return ResponseUtil.updateDataFailed();
        }
        return ResponseUtil.ok(brand);
    }

    @RequiresPermissions("admin:brand:delete")
    @RequiresPermissionsDesc(menu = {"商场管理","品牌管理"},button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody BigmallBrand brand){
        Integer id = brand.getId();
        if (id == null){
            return ResponseUtil.badArgument();
        }
        brandService.deleteById(id);
        return ResponseUtil.ok();
    }
}
