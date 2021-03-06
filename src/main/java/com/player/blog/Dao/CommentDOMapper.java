package com.player.blog.Dao;

import com.player.blog.POJO.DO.CommentDO;
import org.springframework.stereotype.Repository;

@Repository

public interface CommentDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    int deleteByPrimaryKey(Long articleid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    int insert(CommentDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    int insertSelective(CommentDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    CommentDO selectByPrimaryKey(Long articleid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    int updateByPrimaryKeySelective(CommentDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table comment
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    int updateByPrimaryKey(CommentDO record);
}