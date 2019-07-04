package com.player.blog.Service;

import com.player.blog.POJO.DO.UserDO;

public interface UserService {
    String login(UserDO userDO);
    String register(UserDO userDO);
    String forgetFirst(UserDO userDO);
    String forgetSecond(UserDO userDO);
    String encryption(String password);
    String pastEmail(String email,String username);
    String newEmail(UserDO userDO,String username);
    UserDO getUsernameByEmail(String email);
    String getPasswordByUsername(String username);
}
