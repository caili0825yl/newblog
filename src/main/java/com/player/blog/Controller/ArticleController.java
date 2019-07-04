package com.player.blog.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.player.blog.POJO.DO.ArticleDO;
import com.player.blog.POJO.VO.ArticleVO;
import com.player.blog.Service.ArticleService;
import com.player.blog.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private    ArticleService articleService;
   @Autowired
    private FriendService friendService;

    /**
     * 不需要登录
     * @return
     */
    @RequestMapping("/hotview")
    public String hotView(){
        return "hot";
    }
    /**
     * 需要登录
     * @return
     */
    @RequestMapping("/writeview")
    public String writeView(){
        return "writeedit";
    }
    /**
     * 不需要登录
     * @return
     */
    @RequestMapping("/readview")
    public String readView(){
        return "read";
    }

    /**
     * 不需要登录
     * @return
     */
@ResponseBody
    @RequestMapping("/recommend")
    public List<ArticleVO> recommend(){
        List<ArticleVO> list=articleService.getRecommend();
        return list;
    }

    /**
     * 不需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/hot")
    public List hot(@RequestParam(value = "page")int page){
        PageHelper.startPage(page,3);
        List<ArticleVO> list=articleService.getHot();

        return new PageInfo<>(list).getList();
    }

    /**
     * 不需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/recommendcontent")
    public String recommendContent(@RequestParam(value = "id") String id){
      String content=articleService.getContent(Long.parseLong(id));
      return content;
    }

    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public void delete(@RequestParam(value = "id") Long id,@RequestParam(value = "username") String username){
        ArticleDO articleDO=new ArticleDO();
        articleDO.setUsername(username);
        articleDO.setId(id);
        articleService.delete(articleDO);
    }
    /**
     * 不需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/getarticlelist")
    public List<ArticleVO> getArticleList(@RequestParam(value = "username") String username,@RequestParam(value = "public",required = false) Byte open){
        List<ArticleVO> list;
        if (open!=null){
            list=articleService.getArticleList(username);

        }else {
            list=articleService.getArticleList(username);

        }
            return list;
    }

    /**
     * 不需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/getarticle")
    public ArticleVO getArticle(@RequestParam(value = "id") Long id){

        ArticleDO articleDO=new ArticleDO();
        articleDO.setId(id);
        ArticleVO  articleVO= articleService.getArticle(articleDO);
        return articleVO;
    }

    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/editarticle")
    public void editArticle(@RequestParam(value = "username") String username,@RequestParam(value = "id",required = false) Long id,
                            @RequestParam(value = "title") String title,@RequestParam(value = "content") String  content,
                            @RequestParam(value = "privacy",required = false) boolean privacy){
        ArticleDO articleDO=new ArticleDO();
        articleDO.setId(id);
        articleDO.setUsername(username);
        articleDO.setTitle(title);
        articleDO.setContent(content);
        if(id==null){
            articleDO.setTime(new Date());
        }
        if (privacy==true){
            articleDO.setOpen((byte)0);
        }

        if(id==null){
            articleService.write(articleDO);
        }else {
            articleService.edit(articleDO);
        }

    }

    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/getfriendarticle")
    public List<ArticleVO> getFriendArticle(@RequestParam(value = "username") String username){

        List<String> friendList=friendService.getFriendList(username);
        List<ArticleVO> list=new ArrayList<>();
        ArticleVO articleVO=new ArticleVO();

        if (friendList.size()==0){
            articleVO.setTitle("error7");

            list.add(articleVO);
        }else {
            list=articleService.getFriendArticle(friendList);
            if(list.size()==0){
                articleVO.setTitle("error8");

                list.add(articleVO);
            }
        }

        return list;
    }
}
