package com.player.blog.Dao;

import com.player.blog.POJO.DO.LikeDO;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table like
     *
     * @mbg.generated Wed Jun 26 16:05:50 CST 2019
     */
    int deleteByPrimaryKey(Long articleid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table like
     *
     * @mbg.generated Wed Jun 26 16:05:50 CST 2019
     */
    int insert(LikeDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table like
     *
     * @mbg.generated Wed Jun 26 16:05:50 CST 2019
     */
    int insertSelective(LikeDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table like
     *
     * @mbg.generated Wed Jun 26 16:05:50 CST 2019
     */
    LikeDO select(LikeDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table like
     *
     * @mbg.generated Wed Jun 26 16:05:50 CST 2019
     */
    int updateByPrimaryKeySelective(LikeDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table like
     *
     * @mbg.generated Wed Jun 26 16:05:50 CST 2019
     */
    int updateByPrimaryKey(LikeDO record);
}