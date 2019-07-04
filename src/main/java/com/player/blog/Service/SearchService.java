package com.player.blog.Service;

import com.player.blog.POJO.DO.InformationDO;
import com.player.blog.POJO.VO.SearchArticleVO;

import java.util.List;

public interface SearchService {
    List<InformationDO> searchNickname(String name);
    List<InformationDO> searchBlogname(String name);
    List<SearchArticleVO> searchArticle(String title);
}
