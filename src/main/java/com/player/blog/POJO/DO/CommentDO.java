package com.player.blog.POJO.DO;

public class CommentDO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.articleid
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    private Long articleid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.content
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.username
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    private String username;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.articleid
     *
     * @return the value of comment.articleid
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    public Long getArticleid() {
        return articleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.articleid
     *
     * @param articleid the value for comment.articleid
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.content
     *
     * @return the value of comment.content
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.content
     *
     * @param content the value for comment.content
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.username
     *
     * @return the value of comment.username
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.username
     *
     * @param username the value for comment.username
     *
     * @mbg.generated Sat Jun 08 20:22:58 CST 2019
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}