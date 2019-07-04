package com.player.blog.Controller;

import com.player.blog.POJO.DO.InformationDO;
import com.player.blog.Service.FriendService;
import com.player.blog.Service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

@Autowired
private InfoService infoService;
    /**
     * 需要登录
     * @return
     */
    @RequestMapping("/friendview")
    public String friendView(){
        return "friend";
    }

    /**
     * 需要登录
     * @return
     */
    @RequestMapping("/addview")
    public String addView(){
        return "add";
    }
    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/newfriend")
    public boolean newFriend(@RequestParam(value = "username")String username){
        boolean result=friendService.newFriend(username);
        return result;
    }
    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/isfriend")
    public byte  isFriend(@RequestParam(value = "username")String username,@RequestParam( "friend")String friend){



        byte result=friendService.isFriend(username,friend);
        return result;
    }
    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/apply")
    public void  apply(@RequestParam(value = "username")String username,@RequestParam( "friend")String friend){
        friendService.apply(username, friend);
    }

    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkadd")
    public List<InformationDO>  checkAdd(@RequestParam(value = "username")String username){
        List<String> applyList=friendService.getApplyList(username);
        if(applyList.size()==0){
            return null;
        }
        List<InformationDO> list= infoService.getListInfo(applyList);
        return list;
    }

    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/getlist")
    public List<InformationDO>  getList(@RequestParam(value = "username")String username){
        List<String> friendList=friendService.getFriendList(username);
        List<InformationDO> list=new ArrayList<>();

        if (friendList.size()==0){
            return list;
        }else {
            list=infoService.getListInfo(friendList);
        }

        return list;

    }

    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public void  add(@RequestParam(value = "username")String username,@RequestParam( "friend")String friend){
        friendService.add(username,friend);
        InformationDO informationDO1=infoService.getInfo(username);
        InformationDO informationDO2=infoService.getInfo(friend);
        informationDO1.setFriend(informationDO1.getFriend()+1);
        informationDO2.setFriend(informationDO2.getFriend()+1);
        infoService.editInfo(informationDO1);
        infoService.editInfo(informationDO2);


    }
}
