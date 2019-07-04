package com.player.blog.Service;

import com.player.blog.POJO.DO.ArticleDO;
import com.player.blog.POJO.VO.ArticleVO;

import java.util.List;

public interface ArticleService {
    List<ArticleVO> getRecommend();
    List<ArticleVO> getHot();
    String getContent(Long id);
    ArticleVO getArticle(ArticleDO articleDO);
    List<ArticleVO> getArticleList(String username);
    List<ArticleVO> getPublicArticle(String username);
    List<ArticleVO> getFriendArticle(List<String> friendList);
    void write(ArticleDO articleDO);
    void edit(ArticleDO articleDO);
    void delete(ArticleDO articleDO);
}
