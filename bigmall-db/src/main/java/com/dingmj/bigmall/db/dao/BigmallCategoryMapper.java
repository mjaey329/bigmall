package com.dingmj.bigmall.db.dao;

import com.dingmj.bigmall.db.domain.BigmallCategory;
import com.dingmj.bigmall.db.domain.BigmallCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BigmallCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    long countByExample(BigmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    int deleteByExample(BigmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    int insert(BigmallCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    int insertSelective(BigmallCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    BigmallCategory selectOneByExample(BigmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    BigmallCategory selectOneByExampleSelective(@Param("example") BigmallCategoryExample example, @Param("selective") BigmallCategory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    List<BigmallCategory> selectByExampleSelective(@Param("example") BigmallCategoryExample example, @Param("selective") BigmallCategory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    List<BigmallCategory> selectByExample(BigmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    BigmallCategory selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") BigmallCategory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    BigmallCategory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    BigmallCategory selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") BigmallCategory record, @Param("example") BigmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") BigmallCategory record, @Param("example") BigmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BigmallCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(BigmallCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") BigmallCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bigmall_category
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}