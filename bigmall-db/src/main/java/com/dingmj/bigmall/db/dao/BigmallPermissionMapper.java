package com.dingmj.bigmall.db.dao;

import com.dingmj.bigmall.db.domain.BigmallPermission;
import com.dingmj.bigmall.db.domain.BigmallPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BigmallPermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    long countByExample(BigmallPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    int deleteByExample(BigmallPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    int insert(BigmallPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    int insertSelective(BigmallPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    BigmallPermission selectOneByExample(BigmallPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    BigmallPermission selectOneByExampleSelective(@Param("example") BigmallPermissionExample example, @Param("selective") BigmallPermission.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    List<BigmallPermission> selectByExampleSelective(@Param("example") BigmallPermissionExample example, @Param("selective") BigmallPermission.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    List<BigmallPermission> selectByExample(BigmallPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    BigmallPermission selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") BigmallPermission.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    BigmallPermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    BigmallPermission selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") BigmallPermission record, @Param("example") BigmallPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") BigmallPermission record, @Param("example") BigmallPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BigmallPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(BigmallPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") BigmallPermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_permission
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}