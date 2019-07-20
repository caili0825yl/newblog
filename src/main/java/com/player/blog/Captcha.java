package com.player.blog;

import org.apache.commons.mail.HtmlEmail;

import java.util.Random;

public class Captcha {
    public static String getCaptcha(String email) {
        HtmlEmail htmlEmail=new HtmlEmail();
        htmlEmail.setHostName("smtp.qq.com");
        htmlEmail.setCharset("utf-8");
        try {
            htmlEmail.addTo(email);
            htmlEmail.setSSLOnConnect(true);
            htmlEmail.setSslSmtpPort("465");
            System.setProperty("mail.smtp.ssl.enable", "true");

            htmlEmail.setFrom("465203412@qq.com","OTAKU@部落格");
            htmlEmail.setAuthentication("465203412@qq.com","dwevqchqprplbijc");
            htmlEmail.setSubject("验证码");
            String code=getCode(4);
            htmlEmail.setMsg("您的验证码为："+code);
            htmlEmail.send();
            return  code;
        }catch (Exception f){
            f.printStackTrace();
            return "false";
        }
    }

    public static String getCode(int n) {
        String string = "0123456789abcdefghijklmnopqrstuvwxyz";//保存数字0-9 和 大小写字母
        char[] ch = new char[n]; //声明一个字符数组对象ch 保存 验证码
        for (int i = 0; i < n; i++) {
            Random random = new Random();//创建一个新的随机数生成器

            int index = random.nextInt(string.length());//返回[0,string.length)范围的int值    作用：保存下标
            ch[i] = string.charAt(index);//charAt() : 返回指定索引处的 char 值   ==》保存到字符数组对象ch里面
        }
        //将char数组类型转换为String类型保存到result
        //String result = new String(ch);//方法一：直接使用构造方法      String(char[] value) ：分配一个新的 String，使其表示字符数组参数中当前包含的字符序列。
        String result = String.valueOf(ch);//方法二： String方法   valueOf(char c) ：返回 char 参数的字符串表示形式。
        return result;
    }
}
