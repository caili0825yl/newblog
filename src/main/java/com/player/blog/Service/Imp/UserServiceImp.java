package com.player.blog.Service.Imp;

import com.player.blog.Dao.InformationDOMapper;
import com.player.blog.Dao.UserDOMapper;
import com.player.blog.POJO.DO.InformationDO;
import com.player.blog.POJO.DO.UserDO;
import com.player.blog.Service.UserService;
import org.apache.catalina.User;
import org.apache.commons.mail.HtmlEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;

@Transactional(isolation = Isolation.READ_COMMITTED)
@Service
public class UserServiceImp implements UserService {
@Autowired
private UserDOMapper userDOMapper;

    @Override
    public String login(UserDO userDO) {
        String result="success";
        UserDO user=userDOMapper.selectByUsername(userDO.getUsername());
        if(user==null){
            user=userDOMapper.selectByEmail(userDO.getEmail());
            if(user==null){
                result="error3";
            }else {
                user=userDOMapper.loginByEmail(userDO);
                if (user==null){
                    result="error2";
                }
            }

        }else {
            user=userDOMapper.login(userDO);
            if (user==null){
                result="error2";
            }
        }
        return result;
    }

    @Override
    public String  register(UserDO userDO) {
       UserDO user=userDOMapper.selectByUsername(userDO.getUsername());
       String  result="error1";
       if(user==null){
           userDOMapper.insert(userDO);

           result="success";
       }
    return result;
    }

    @Override
    public String encryption(String password) {
        String encode="";
        try{
            MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes("UTF-8"));
            encode=byte2Hex(messageDigest.digest());

        }catch (Exception e){
            e.printStackTrace();
        }
        return encode;
    }


    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();

    }

    @Override
    public String forgetFirst(UserDO userDO) {
        UserDO user=userDOMapper.selectByUsername(userDO.getUsername());
        if(user==null){
            return "error3";
        }else {
            user=userDOMapper.selectByUsernameAndEmail(userDO);
            if(user==null){
                return "error4";
            }else {
                return "success";
            }
        }
    }

    @Override
    public String forgetSecond(UserDO userDO) {
        if((userDO.getPassword().equals(userDOMapper.checkPassword(userDO.getUsername())))){
            return "error5";
        }else
        {
            userDOMapper.updatePassword(userDO);
            return "success";
        }
    }

    @Override
    public String pastEmail(String email,String username) {
        String result="success";
        if (!(userDOMapper.selectByUsername(username).getEmail().equals(email))){
            result="error4";
        }
        return result;
    }

    @Override
    public String newEmail(UserDO userDO,String username) {
        String result="success";
        if (userDOMapper.selectByUsername(username).getEmail().equals(userDO.getEmail())){
            result="error6";
        }else {
            userDOMapper.updateByPrimaryKeySelective(userDO);
        }
        return result;

    }

    @Override
    public UserDO getUsernameByEmail(String email) {
        UserDO userDO=userDOMapper.selectByEmail(email);
        return userDO;
    }

    @Override
    public String getPasswordByUsername(String username) {
        String password=userDOMapper.selectByUsername(username).getPassword();
        return password;
    }
}
