package com.player.blog;

import com.player.blog.POJO.DO.UserDO;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/user/quit","/user/userview","/user/editpass","/user/editpass"
        ,"/user/pastemail","/user/newemail","/article/writeview","/article/delete","/article/editarticle","/article/getfriendarticle"
                ,"/friend/**","/info/**","/like/**"
        ) .excludePathPatterns("/info/getinfo","/img/**");
    }
}
class MyInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies=request.getCookies();

        boolean result=false;
        if (cookies!=null){
            for (Cookie cookie:cookies){
                if("user".equals(cookie.getName())){
                    result=true;
                }
            }
        }else{
            response.sendRedirect("errorview");
        }
        return result;
    }
}