package com.player.blog.Service.Imp;

import com.player.blog.Dao.ArticleDOMapper;
import com.player.blog.Dao.InformationDOMapper;
import com.player.blog.POJO.DO.ArticleDO;
import com.player.blog.POJO.DO.InformationDO;
import com.player.blog.POJO.VO.SearchArticleVO;
import com.player.blog.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class SearchServiceImp implements SearchService {
    @Autowired
    private InformationDOMapper informationDOMapper;
    @Autowired
    private ArticleDOMapper articleDOMapper;

    @Override
    public List<InformationDO> searchNickname(String name) {
        List<InformationDO> list=informationDOMapper.searchNickname(name);

        return list;
    }

    @Override
    public List<InformationDO> searchBlogname(String name) {
        List<InformationDO> list=informationDOMapper.searchBlogname(name);

        return list;
    }

    @Override
    public List<SearchArticleVO> searchArticle(String title) {
        List<ArticleDO> list=articleDOMapper.searchArticle(title);
        List<SearchArticleVO> voList=new ArrayList<>();
       SearchArticleVO vo;
            for(ArticleDO articleDO:list){
                 vo=new SearchArticleVO();
                 vo.setId(articleDO.getId());
               vo.setNickname(informationDOMapper.usernameSearchNickname(articleDO.getUsername()));
               vo.setTitle(articleDO.getTitle());
               voList.add(vo);
            }

        return voList;
    }
}
