package com.player.blog.Controller;

import com.player.blog.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("search")
public class SearchController {
@Autowired
private SearchService searchService;
    /**
     * 不需要登录
     * @return
     */
    @RequestMapping("/searchview")
    public String searchView(){
        return "searchresult";
    }

    /**
     * 不需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/search")
    public List search(@RequestParam(value = "type") String type,@RequestParam(value = "search")String search ){
        List list=new ArrayList();
        if("article".equals(type)){
           list= searchService.searchArticle(search);
        }else if("nickname".equals(type)){
             list=searchService.searchNickname(search);
        }else if("blog".equals(type)){
             list=searchService.searchBlogname(search);
        }
        return list;
    }

}
