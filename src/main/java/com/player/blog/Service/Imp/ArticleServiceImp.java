package com.player.blog.Service.Imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.player.blog.Dao.ArticleDOMapper;
import com.player.blog.Dao.InformationDOMapper;
import com.player.blog.DateFormat;
import com.player.blog.POJO.ArticlePage;
import com.player.blog.POJO.DO.ArticleDO;
import com.player.blog.POJO.VO.ArticleVO;
import com.player.blog.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class ArticleServiceImp implements ArticleService {
    @Autowired
    private ArticleDOMapper articleDOMapper;
    @Autowired
    private InformationDOMapper informationDOMapper;
    @Override
    public List<ArticleVO> getRecommend() {
        List<ArticleDO> doList=articleDOMapper.getRecommend();
        List<ArticleVO> list=new ArrayList<>();
        ArticleVO articleVO=null;
        String nickname="";
        if(doList==null){
            articleVO.setUsername("获取数据发生错误");
            articleVO.setFirst("");
            articleVO.setTitle("");
            articleVO.setTime("");
            list.add(articleVO);

        }else {
            for(ArticleDO articleDO:doList){
                articleVO=new ArticleVO();
              nickname=informationDOMapper.selectByPrimaryKey(articleDO.getUsername()).getNickname();


                articleVO.setId(articleDO.getId());
                articleVO.setTime(DateFormat.dateTimeFormat(articleDO.getTime()));
                articleVO.setTitle(articleDO.getTitle());
                articleVO.setUsername(nickname);
                String first;
                int exist=articleDO.getContent().indexOf("<img");
               
                if(exist!=-1){
                    if(exist<50){
                        first=articleDO.getContent().substring(0,exist)+"...";

                    }else {
                        first=articleDO.getContent().substring(0,50)+"...";

                    }
                }
                else if (articleDO.getContent().length()>=50){
                    first=articleDO.getContent().substring(0,50)+"...";
                }else {
                    first=articleDO.getContent();
                }
                articleVO.setFirst(first);
                list.add(articleVO);
            }
        }
        return list;
    }

    @Override
    public String getContent(Long id) {
        String content=articleDOMapper.getContent(id);
        if(content==null){
            content="获取数据发生错误！";
        }
        return content;
    }

    @Override
    public ArticlePage getArticleList(String username, int page) {
        PageHelper.startPage(page,10);
        List<ArticleDO> doList=articleDOMapper.getArticle(username);
        PageInfo<ArticleDO> pageInfo=new PageInfo<>(doList);
        if (pageInfo.getList()==null){
            return null;
        }else{
            List<ArticleVO> list=new ArrayList<>();

            for(ArticleDO articleDO:pageInfo.getList()){
                ArticleVO  articleVO=new ArticleVO();

                articleVO.setId(articleDO.getId());
                articleVO.setTitle(articleDO.getTitle());
                list.add(articleVO);
            }
            ArticlePage articlePage=new ArticlePage(list,pageInfo.isIsFirstPage(),pageInfo.isIsLastPage(),pageInfo.getPages());
            return articlePage;
        }

    }

    @Override
    public List<ArticleVO> getFriendArticle(List<String> friendList) {
        List<ArticleDO> doList=articleDOMapper.getFriendArticle(friendList);
        List<ArticleVO> list=new ArrayList<>();
        ArticleVO articleVO;

        if(doList==null){

           return null;

        }else {
            for(ArticleDO articleDO:doList){
                articleVO=new ArticleVO();
                articleVO.setId(articleDO.getId());
                articleVO.setTime(DateFormat.dateTimeFormat(articleDO.getTime()));
                articleVO.setTitle(articleDO.getTitle());
                articleVO.setUsername(informationDOMapper.usernameSearchNickname(articleDO.getUsername()));

                list.add(articleVO);
            }
        }
        return list;
    }

    @Override
    public List<ArticleVO> getHot() {


        List<ArticleDO> doList=articleDOMapper.getHot();

        List<ArticleVO> list=new ArrayList<>();
        ArticleVO articleVO=null;
        String nickname="";
        if(doList==null){
            articleVO.setUsername("获取数据发生错误");
            articleVO.setFirst("");
            articleVO.setTitle("");
            articleVO.setTime("");
            list.add(articleVO);

        }else {
            for(ArticleDO articleDO:doList){
               nickname=informationDOMapper.selectByPrimaryKey(articleDO.getUsername()).getNickname();
                articleVO=new ArticleVO();

                articleVO.setId(articleDO.getId());
                articleVO.setTime(DateFormat.dateTimeFormat(articleDO.getTime()));
                articleVO.setTitle(articleDO.getTitle());
               articleVO.setUsername(nickname);
               String first;
                int exist=articleDO.getContent().indexOf("<img");
               if(exist!=-1){
                   if(exist<200){
                       first=articleDO.getContent().substring(0,exist)+"...";

                   }else{
                       first=articleDO.getContent().substring(0,200)+"...";

                   }

               }
               else if (articleDO.getContent().length()>=200){
                    first=articleDO.getContent().substring(0,200)+"...";
                }else {
                    first=articleDO.getContent();
                }
                articleVO.setFirst(first);
                list.add(articleVO);
            }
        }
        return list;
    }

    @Override
    public ArticlePage getPublicArticle(String username,int page) {
        PageHelper.startPage(page,10);
        List<ArticleDO> doList=articleDOMapper.getPublicArticle(username);
        PageInfo<ArticleDO> pageInfo=new PageInfo<>(doList);
        if (pageInfo.getList()==null){
            return null;
        }else{
            List<ArticleVO> list=new ArrayList<>();

            for(ArticleDO articleDO:pageInfo.getList()){
                ArticleVO  articleVO=new ArticleVO();

                articleVO.setId(articleDO.getId());
                articleVO.setTitle(articleDO.getTitle());
                list.add(articleVO);
            }
            List<Object> a=new ArrayList<>();
            a.add(list);

            ArticlePage articlePage=new ArticlePage(list,pageInfo.isIsFirstPage(),pageInfo.isIsLastPage(),pageInfo.getPages());
            return articlePage;
        }
    }

    @Override
    public void write(ArticleDO articleDO) {
        articleDOMapper.insertSelective(articleDO);
    }

    @Override
    public void edit(ArticleDO articleDO) {
        articleDOMapper.updateByPrimaryKeySelective(articleDO);
    }

    @Override
    public ArticleVO getArticle(ArticleDO articleDO) {
        ArticleDO Do=articleDOMapper.selectByPrimaryKey(articleDO);
        ArticleVO articleVO=new ArticleVO();

        if (Do!=null){
            articleVO.setTitle(Do.getTitle());
            articleVO.setContent(Do.getContent());
            articleVO.setTime(DateFormat.dateTimeFormat(Do.getTime()));
            articleVO.setUsername(Do.getUsername());
            articleVO.setLike(Do.getLike());
        }

        return articleVO;
    }

    @Override
    public void delete(ArticleDO articleDO) {
        articleDOMapper.deleteByPrimaryKey(articleDO);
    }
}
