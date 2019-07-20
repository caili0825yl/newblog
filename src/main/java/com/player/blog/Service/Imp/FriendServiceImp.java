package com.player.blog.Service.Imp;

import com.player.blog.Dao.FriendDOMapper;
import com.player.blog.POJO.DO.FriendDO;
import com.player.blog.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Transactional(isolation = Isolation.READ_COMMITTED)
@Service
public class FriendServiceImp implements FriendService {
    @Autowired
    private FriendDOMapper friendDOMapper;

    @Override
    public List<String> getFriendList(String username) {
        List<String> friendList=new ArrayList<>();
        List<FriendDO> list=friendDOMapper.selectByUsername(username);
        for(FriendDO friendDO:list){
            if(friendDO.getFriend().equals(username)){
                friendList.add(friendDO.getUsername());
            }else {
                friendList.add(friendDO.getFriend());
            }
        }
        return friendList;
    }

    @Override
    public boolean newFriend(String username) {
        List result=friendDOMapper.newFriend(username);
        if(result.size()==0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public byte isFriend(String username, String friend) {
        byte result=0;
        String a=friendDOMapper.isApply(username,friend);
        if (friendDOMapper.isApply(username,friend)!=null){

            result=1;
        }


        if (friendDOMapper.isFriend(username,friend)!=null||friendDOMapper.isFriend(friend,username)!=null){
            result=2;
        }
       return result;
    }

    @Override
    public void apply(String username, String friend) {
        FriendDO friendDO=new FriendDO();
        friendDO.setApply((byte)0);
        friendDO.setUsername(username);
        friendDO.setFriend(friend);
        friendDOMapper.insert(friendDO);
    }

    @Override
    public List<String> getApplyList(String username) {
        List<String> applyList=friendDOMapper.newFriend(username);
        return applyList;
    }

    @Override
    public void add(String username, String friend) {
        FriendDO friendDO=new FriendDO();
        friendDO.setUsername(username);
        friendDO.setFriend(friend);
        friendDO.setApply((byte)1);
        friendDOMapper.updateByPrimaryKeySelective(friendDO);
    }

    @Override
    public void deny(String username, String friend) {
        FriendDO friendDO=new FriendDO();
        friendDO.setUsername(username);
        friendDO.setFriend(friend);

        friendDOMapper.deny(friendDO);
    }
}
