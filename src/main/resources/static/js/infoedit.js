

$(function(){
    $.ajax({
        url:'/info/getinfo',
        type:"GET",
        data:"username="+$.cookie("user"),
        success:function(data){
                $("#nickname").val(data.nickname);
                if(data.gender==0){
                    $("#female").attr("checked","checked");
                }else if(data.gender==1){
                    $("#male").attr("checked","checked");

                }else if(data.gender==1){
                    $("#unknown").attr("checked","checked");
                }
                $("#birthday").val(data.birthday);
                $("#blogname").val(data.blogname);
                $("#slogan").val(data.slogan);
                $("#finalImg").attr("src",data.avatar);
               
        }
    })
   


    $("#infoSubmit").click(function(){
        
        if (isNull($("#nickname").val()) == true) {
            $('#infoAlert').text('昵称为空！');
            $('#infoAlert').css('display', 'block');
            return false;
        } 
        else if (isNull($("#birthday").val()) == true) {
            $('#infoAlert').text('生日为空！');
            $('#infoAlert').css('display', 'block');
            return false;
        }else if (isNull($("#blogname").val()) == true) {
            $('#infoAlert').text('博客名为空！');
            $('#infoAlert').css('display', 'block');
            return false;
        }else if (isNull($("#slogan").val()) == true) {
            $('#infoAlert').text('个性签名为空！');
            $('#infoAlert').css('display', 'block');
            return false;
        }

        $.ajax({
            url:'/info/editinfo',
            type:"POST",
            data:$("#infoModule").serialize()+"&username="+$.cookie("user"),
            success:function(){
                $('#infoAlert').css('display', 'none');
                alert("成功修改！");
            }
        })
    })

    
 var newInterValObj; //timer变量，控制时间
    var newcount = 60; //间隔函数，1秒执行
    var newcurCount;

    $("#newEmailGetCaptcha").click(function () {
        getCaptcha( $('#newEmail').val())
        newcurCount = newcount;


        $("#getCaptcha").attr("disabled","true" );//设置按钮为禁用状态
        $("#getCaptcha").text(newcurCount + "s");//更改按钮文字
        newInterValObj = window.setInterval(newSetRemainTime, 1000); // 启动计时器timer处理函数，1秒执行一次

    })

    function newSetRemainTime() {
        if (newcurCount == 0) {//超时重新获取验证码
            window.clearInterval(newInterValObj);// 停止计时器
            $("#newEmailGetCaptcha").removeAttr("disabled");//移除禁用状态改为可用
            $("#newEmailGetCaptcha").text("点击获取");
        }else {
            newcurCount--;
            $("#newEmailGetCaptcha").text(newcurCount + "s");
        }
    }



    

    $("#passSubmit").click(function(){

        if (isNull($("#pastPass").val()) == true) {
            $('#passAlert').text('旧密码为空！');
            $('#passAlert').css('display', 'block');
            return false;
        } 
        else if (isNull($("#newPass").val()) == true) {
            $('#passAlert').text('新密码为空！');
            $('#passAlert').css('display', 'block');
            return false;
        }  else if (isNull($("#passCaptcha").val()) == true) {
            $('#passAlert').text('验证码为空！');
            $('#passAlert').css('display', 'block');
            return false;
        } else if (isNull($("#email").val()) == true) {
            $('#passAlert').text('邮箱为空！');
            $('#passAlert').css('display', 'block');
            return false;
        } else if (checkPassword($("#newPass").val()) == true) {
            $('#passAlert').text('密码格式错误！');
            $('#passAlert').css('display', 'block');
            return false;


        }else if ($("#newPass").val() != $("#reNewPass").val()) {
            $('#passAlert').text('新密码两次输入不一致！');
            $('#passAlert').css('display', 'block');
            return false;
        }else if ($("#newPass").val() == $("#pastPass").val()) {
            $('#passAlert').text('跟旧密码一样！');
            $('#passAlert').css('display', 'block');
            return false;
        }
        $.ajax({
            url:'/user/editpass',
            type:"POST",
            data:$("#passModule").serialize()+"&username="+$.cookie("user"),
            success:function(data){
                if (data == 'success') {
                    $('#passAlert').css('display', 'none');
                    alert("成功修改！");


                } else if (data == 'error0') {

                    $('#passAlert').text('验证码错误！');
                    $('#passAlert').css('display', 'block');
                } else if (data == 'error4') {

                    $('#passAlert').text('邮箱错误！');
                    $('#passAlert').css('display', 'block');
                } else if (data == 'error2') {

                    $('#passAlert').text('旧密码错误！');
                    $('#passAlert').css('display', 'block');
                } 

            }
        })
    })
    $("#pastEmailbt").click(function(){
        $.ajax({
            url:'/user/pastemail',
            type:"POST",
            data:$("#emailFirstModule").serialize()+"&username="+$.cookie("user"),
            success:function(data){
                if (data == 'success') {
                    $('#emailFirst').css('display', 'none');
                    $('#emailSecond').css('display', 'block');
                    $('#firstAlert').css('display', 'none');



                } else if (data == 'error0') {

                    $('#firstAlert').text('验证码错误！');
                    $('#firstAlert').css('display', 'block');
                } else if (data == 'error4') {

                    $('#firstAlert').text('邮箱不正确！');
                    $('#firstAlert').css('display', 'block');
                } 
            }
        })
    })



    
    $("#newEmailbt").click(function(){
        $.ajax({
            url:'/user/newemail',
            type:"POST",
            data:$("#emailSecondModule").serialize()+"&username="+$.cookie("user"),
            success:function(data){if (data == 'success') {
                $('#secondAlert').css('display', 'none');
                
                alert("成功修改！");


            } else if (data == 'error0') {

                $('#secondAlert').text('验证码错误！');
                $('#secondAlert').css('display', 'block');
            } 

            }
        })
    })

})