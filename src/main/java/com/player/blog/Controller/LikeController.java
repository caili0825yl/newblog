package com.player.blog.Controller;

import com.player.blog.POJO.DO.FriendDO;
import com.player.blog.POJO.DO.LikeDO;
import com.player.blog.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/like")
@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    /**
     * 需要登录
     * @return
     */
    @RequestMapping("/like")
    public void like(@RequestParam("username") String username,@RequestParam("id") Long id){
        LikeDO likeDO=new LikeDO();
        likeDO.setArticleid(id);
        likeDO.setUsername(username);
        likeService.like(likeDO);
    }
    /**
     * 需要登录
     * @return
     */
    @RequestMapping("/islike")
    public String islike(@RequestParam("username") String username,@RequestParam("id") Long id){
        LikeDO likeDO=new LikeDO();
        likeDO.setArticleid(id);
        likeDO.setUsername(username);
        String result=likeService.isLike(likeDO);
        return result;
    }
}
