package com.player.blog.Service;

import com.player.blog.POJO.DO.FriendDO;
import com.player.blog.POJO.DO.LikeDO;

public interface LikeService  {
    void like(LikeDO likeDO);
    String isLike(LikeDO likeDO);
}
