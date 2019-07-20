package com.player.blog.Service;


import com.player.blog.POJO.DO.FriendDO;

import java.util.List;

public interface FriendService {
    List<String> getFriendList(String username);
    boolean newFriend(String username);
    byte isFriend(String username,String friend);
    void apply(String username,String friend);
    List<String> getApplyList(String username);
    void add(String username,String friend);
    void deny(String username,String friend);
}
