package com.dingmj.bigmall.db.dao;

import com.dingmj.bigmall.db.domain.BigmallRole;
import com.dingmj.bigmall.db.domain.BigmallRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BigmallRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    long countByExample(BigmallRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    int deleteByExample(BigmallRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    int insert(BigmallRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    int insertSelective(BigmallRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    BigmallRole selectOneByExample(BigmallRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    BigmallRole selectOneByExampleSelective(@Param("example") BigmallRoleExample example, @Param("selective") BigmallRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    List<BigmallRole> selectByExampleSelective(@Param("example") BigmallRoleExample example, @Param("selective") BigmallRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    List<BigmallRole> selectByExample(BigmallRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    BigmallRole selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") BigmallRole.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    BigmallRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    BigmallRole selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") BigmallRole record, @Param("example") BigmallRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") BigmallRole record, @Param("example") BigmallRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BigmallRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(BigmallRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") BigmallRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_role
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}