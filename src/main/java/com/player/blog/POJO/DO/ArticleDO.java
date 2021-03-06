package com.player.blog.POJO.DO;

import java.util.Date;

public class ArticleDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.id
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.username
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.time
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    private Date time;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.title
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.content
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.like
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    private Long like;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.open
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    private Byte open;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column article.recommend
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    private Byte recommend;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.id
     *
     * @return the value of article.id
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.id
     *
     * @param id the value for article.id
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.username
     *
     * @return the value of article.username
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.username
     *
     * @param username the value for article.username
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.time
     *
     * @return the value of article.time
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public Date getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.time
     *
     * @param time the value for article.time
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.title
     *
     * @return the value of article.title
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.title
     *
     * @param title the value for article.title
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.content
     *
     * @return the value of article.content
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.content
     *
     * @param content the value for article.content
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.like
     *
     * @return the value of article.like
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public Long getLike() {
        return like;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.like
     *
     * @param like the value for article.like
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public void setLike(Long like) {
        this.like = like;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.open
     *
     * @return the value of article.open
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public Byte getOpen() {
        return open;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.open
     *
     * @param open the value for article.open
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public void setOpen(Byte open) {
        this.open = open;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column article.recommend
     *
     * @return the value of article.recommend
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public Byte getRecommend() {
        return recommend;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column article.recommend
     *
     * @param recommend the value for article.recommend
     *
     * @mbg.generated Sat Jun 08 20:47:46 CST 2019
     */
    public void setRecommend(Byte recommend) {
        this.recommend = recommend;
    }
}