package com.player.blog.Controller;

import com.player.blog.Captcha;
import com.player.blog.POJO.DO.UserDO;
import com.player.blog.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;
    Map<String,String> data=new HashMap<>();

    /**
     * 不需要登录
     * @return
     */


    @RequestMapping("/loginview")
    public String loginView(){
        Cookie[] cookies=request.getCookies();

        String target="login";
        if (cookies!=null){
            for (Cookie cookie:cookies){
                if("user".equals(cookie.getName())){
                    target="user";

                }
            }
        }

           return target;

    }
    /**
     * 不需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(@RequestParam(value = "username") String username,
                    @RequestParam(value = "password") String password,
                    @RequestParam(value = "remember",required = false) String remember,
            HttpServletResponse response){

        UserDO userDO=new UserDO();
        userDO.setUsername(username);
        userDO.setEmail(username);
        userDO.setPassword(userService.encryption(password));
        String result=userService.login(userDO);
        if("success".equals(result)){
            UserDO DO=userService.getUsernameByEmail(username);
            if(DO!=null){
                username=DO.getUsername();
            }
            Cookie user=new Cookie("user",username);
            user.setPath("/");
            if (remember!=null){

                user.setMaxAge(60*60*24*7);
                response.addCookie(user);

            }
            response.addCookie(user);

        }

        return result;
    }

    /**
     * 不需要登录
     * @return
     */
    @RequestMapping("/visitview")
    public String visitView(){
        return "visit";
    }

    /**
     * 不需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public String register(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password,
                            @RequestParam(value = "email") String email,
                            @RequestParam(value = "captcha") String captcha
    ){
        if(!( captcha.equals(session.getAttribute("captcha")))

        ){
            return "error0";
        }else {
            UserDO userDO=new UserDO();
            userDO.setUsername(username);
            userDO.setPassword(userService.encryption(password));
            userDO.setEmail(email);
            userDO.setRole("user");

            String result=userService.register(userDO);
            return result;
        }

    }
    /**
     * 不需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/getcaptcha")
    public void captcha(@RequestParam(value = "email") String email){
        session.setAttribute("captcha", Captcha.getCaptcha(email));
        return ;
    }


    /**
     * 需要登录
     * @return
     */
    @RequestMapping("/userview")
    public String userView(){
        Cookie[] cookies=request.getCookies();


        if (cookies!=null){
            for (Cookie cookie:cookies){
                if("user".equals(cookie.getName())){

                }
            }
        }
        return "user";
    }
    /**
     * 需要登录
     * @return
     */
    @RequestMapping("/quit")
    public String quit(HttpServletResponse response){
        Cookie[] cookies=request.getCookies();

        for (Cookie cookie:cookies){
            if("user".equals(cookie.getName())){
                cookie.setMaxAge(0);
                cookie.setPath("/");

                response.addCookie(cookie);
            }
        }

        return "redirect:/user/loginview";

    }
    /**
     * 不需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/forgetfirst")
    public String forgetFirst(@RequestParam(value = "username")String username,@RequestParam(value = "email") String email){
        UserDO userDO=new UserDO();
        userDO.setUsername(username);
        data.put("user",username);
        userDO.setEmail(email);
        String result=userService.forgetFirst(userDO);

        return result;
}
    /**
     * 不需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/forgetsecond")
    public String forgetSecond(@RequestParam(value = "password")String password,@RequestParam(value = "captcha")String captcha,
                               @RequestParam(value = "username",required = false)String username ){
        if(!( captcha.equals(session.getAttribute("captcha")))

        ){
            return "error0";
        }else {
            UserDO userDO=new UserDO();
            String newPassword=userService.encryption(password);
            if(data.get("user")!=null){
                userDO.setUsername(data.get("user"));

            }else {
                userDO.setUsername(username);
            }
            userDO.setPassword(newPassword);
            String result=userService.forgetSecond(userDO);
            return result;
        }

    }

    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/editpass")
    public String editPass(@RequestParam(value = "pastpass")String pastpass,@RequestParam(value = "captcha")String captcha,
                               @RequestParam(value = "username")String username ,
                           @RequestParam(value = "email")String email,@RequestParam(value = "newpass")String newpass ){
        if(!( captcha.equals(session.getAttribute("captcha")))

        ){
            return "error0";
        }else if(!(userService.getPasswordByUsername(username).equals(userService.encryption(pastpass)))){

            return "error2";
        }
        else {
            UserDO userDO=new UserDO();
            String newPassword=userService.encryption(newpass);

                userDO.setUsername(username);

            userDO.setPassword(newPassword);
            String result=userService.forgetSecond(userDO);
            return result;
        }

    }

    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("pastemail")
    public String pastEmail(@RequestParam("captcha") String captcha,@RequestParam("email")String email,
                            @RequestParam("username")String username){
        if(!( captcha.equals(session.getAttribute("captcha")))

        ){
            return "error0";
        }else {
        String result=userService.pastEmail(email,username);
        return result;
        }
    }
    /**
     * 需要登录
     * @return
     */
    @ResponseBody
    @RequestMapping("newemail")
    public String newEmail(@RequestParam("captcha") String captcha,@RequestParam("email")String email,
                           @RequestParam("username")String username){
        if(!( captcha.equals(session.getAttribute("captcha")))

        ){
            return "error0";
        }else {
            UserDO userDO=new UserDO();
            userDO.setUsername(username);
            userDO.setEmail(email);
            String result=userService.newEmail(userDO,username);
            return result;
        }
    }
}
