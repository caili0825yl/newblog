package com.player.blog.Dao;

import com.player.blog.POJO.DO.InformationDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface InformationDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table information
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    int deleteByPrimaryKey(String username);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table information
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    int insert(InformationDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table information
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    int insertSelective(InformationDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table information
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    InformationDO selectByPrimaryKey(String username);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table information
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    int updateByPrimaryKeySelective(InformationDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table information
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    int updateByPrimaryKey(InformationDO record);

    List<InformationDO> searchNickname(String name);
    List<InformationDO> searchBlogname(String name);
    String usernameSearchNickname(String username);
    List<InformationDO> getListInfo(List<String> friendList);
}