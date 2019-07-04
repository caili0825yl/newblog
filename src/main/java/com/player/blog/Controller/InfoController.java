package com.player.blog.Controller;

import com.player.blog.DateFormat;
import com.player.blog.POJO.DO.InformationDO;
import com.player.blog.POJO.VO.InformationVO;
import com.player.blog.Service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileOutputStream;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;

@Controller
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private InfoService infoService;

    /**
     * 需要登录
     * @return
     */
    @RequestMapping("/editinfoview")
    public String editInfoView(){
        return "infoedit";
    }
    /**
     * 不需要登录
     * @return
     */
    @ResponseBody
@RequestMapping("/getinfo")
    public InformationVO getInfo(@RequestParam(value = "username") String username){
         InformationDO infoDO =infoService.getInfo(username);
         InformationVO info=new InformationVO();
         info.setAvatar(infoDO.getAvatar());
         info.setBirthday(DateFormat.dateFormat(infoDO.getBirthday()));
         info.setBlogname(infoDO.getBlogname());
         info.setEssay(infoDO.getEssay());
         info.setFriend(infoDO.getFriend());
         info.setGender(infoDO.getGender());
         info.setNickname(infoDO.getNickname());
         info.setSlogan(infoDO.getSlogan());

         return info;
    }
    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/editavatar")
    public void editAvatar(@RequestParam( "avatar") String avatar,@RequestParam( "username") String username){
        infoService.editAvatar(avatar,username);
    }
    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/editinfo")
    public void editInfo(@RequestParam( "nickname") String nickname,@RequestParam( "gender") byte gender,
                         @RequestParam("birthday") String birthday,@RequestParam("blogname") String blogname,
                         @RequestParam("slogan") String slogan,@RequestParam("username") String username){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        InformationDO informationDO=new InformationDO();

        try {
            Date date=format.parse(birthday);
            informationDO.setBirthday(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        informationDO.setNickname(nickname);
        informationDO.setGender(gender);

        informationDO.setBlogname(blogname);
        informationDO.setSlogan(slogan);
        informationDO.setUsername(username);
                infoService.editInfo(informationDO);
    }
}
