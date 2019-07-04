package com.player.blog.Service;

import com.player.blog.POJO.DO.InformationDO;

import java.util.List;

public interface InfoService {
    InformationDO getInfo(String username);
    void editAvatar(String avatar,String username);
    void editInfo(InformationDO informationDO);
    List<InformationDO> getListInfo( List<String> friendList);

}
