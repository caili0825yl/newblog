package com.player.blog.Service;

import com.player.blog.POJO.ArticlePage;
import com.player.blog.POJO.DO.ArticleDO;
import com.player.blog.POJO.VO.ArticleVO;

import java.util.List;

public interface ArticleService {
    List<ArticleVO> getRecommend();
    List<ArticleVO> getHot();
    String getContent(Long id);
    ArticleVO getArticle(ArticleDO articleDO);
    ArticlePage getArticleList(String username, int page);
    ArticlePage getPublicArticle(String username,int page);
    List<ArticleVO> getFriendArticle(List<String> friendList);
    void write(ArticleDO articleDO);
    void edit(ArticleDO articleDO);
    void delete(ArticleDO articleDO);
}
