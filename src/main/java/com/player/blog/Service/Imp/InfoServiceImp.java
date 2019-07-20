package com.player.blog.Service.Imp;

import com.player.blog.Dao.InformationDOMapper;
import com.player.blog.POJO.DO.InformationDO;
import com.player.blog.Service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
@Transactional(isolation = Isolation.READ_COMMITTED)
@Service
public class InfoServiceImp implements InfoService {
    @Autowired
    private InformationDOMapper informationDOMapper;
    @Override
    public InformationDO getInfo(String username) {
        InformationDO info=informationDOMapper.selectByPrimaryKey(username);
        return info;
    }

    @Override
    public void editAvatar(String avatar, String username) {
        Base64.Decoder decoder = Base64.getDecoder();
        avatar = avatar.substring(23);
        byte[] imgByte = decoder.decode(avatar);
        if(informationDOMapper.selectByPrimaryKey(username).getAvatar().equals("/img/default.jpg")){
            InformationDO informationDO=new InformationDO();
            informationDO.setUsername(username);
            informationDO.setAvatar("/"+username+".jpg");
            informationDOMapper.updateByPrimaryKeySelective(informationDO);
        }
        try {
            FileOutputStream out = new FileOutputStream("/home/avatar/"+username+".jpg");
            out.write(imgByte);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editInfo(InformationDO informationDO) {
        informationDOMapper.updateByPrimaryKeySelective(informationDO);
    }

    @Override
    public List<InformationDO> getListInfo(List<String> friendList) {
        List<InformationDO> list=informationDOMapper.getListInfo(friendList);

        return list;
    }


}
