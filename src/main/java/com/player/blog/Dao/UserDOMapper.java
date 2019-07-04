package com.player.blog.Dao;

import com.player.blog.POJO.DO.UserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Tue Jun 04 22:27:45 CST 2019
     */
    int deleteByPrimaryKey(String username);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Tue Jun 04 22:27:45 CST 2019
     */
    int insert(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Tue Jun 04 22:27:45 CST 2019
     */
    int insertSelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Tue Jun 04 22:27:45 CST 2019
     */
    UserDO selectByUsername(String username);
    UserDO selectByEmail(String email);
    UserDO selectByUsernameAndEmail(UserDO record);
    UserDO login(UserDO record);
    UserDO loginByEmail(UserDO record);
    String checkPassword(String password);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Tue Jun 04 22:27:45 CST 2019
     */
    int updateByPrimaryKeySelective(UserDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Tue Jun 04 22:27:45 CST 2019
     */
    int updateByPrimaryKey(UserDO record);
    int updatePassword(UserDO record);
    int updateEmail(UserDO record);
}