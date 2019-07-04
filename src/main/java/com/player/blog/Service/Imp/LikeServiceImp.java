package com.player.blog.Service.Imp;

import com.player.blog.Dao.ArticleDOMapper;
import com.player.blog.Dao.LikeDOMapper;
import com.player.blog.POJO.DO.ArticleDO;
import com.player.blog.POJO.DO.FriendDO;
import com.player.blog.POJO.DO.LikeDO;
import com.player.blog.Service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.READ_COMMITTED)
@Service
public class LikeServiceImp implements LikeService {
    @Autowired
    private LikeDOMapper likeDOMapper;
    @Autowired
    private ArticleDOMapper articleDOMapper;
    @Override
    public void like(LikeDO likeDO) {
        likeDOMapper.insert(likeDO);
        ArticleDO search=new ArticleDO();
        search.setId(likeDO.getArticleid());
        ArticleDO result=articleDOMapper.selectByPrimaryKey(search);
        result.setLike(result.getLike()+1);

        articleDOMapper.updateByPrimaryKeySelective(result);
    }

    @Override
    public String isLike(LikeDO likeDO) {
        String result="no";
        LikeDO search=likeDOMapper.select(likeDO);
        if(search!=null){
            result="yes";
        }

        return result;
    }
}
