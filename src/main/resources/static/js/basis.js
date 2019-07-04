function isNull(val) {
    var str = val.replace(/(^\s*)|(\s*$)/g, '');

    if (str == '' || str == undefined || str == null) {
        return true;
    } else {
        return false;
    }
}
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

function checkUsername(val) {
    var str = /^[a-z][a-z0-9]{5,12}$/;
    if (str.exec(val)==null) {
        return true;
    } else {
        return false;
    }
}

function checkPassword(val) {
    var str = /([a-z0-9]{5,}$)/;
    if (str.exec(val)==null) {
        return true;
    } else {
        return false;
    }
}
function checkEmail(val) {
    var str = /(^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$)/;
    if (str.exec(val)==null) {
        return true;
    } else {
        return false;
    }
}

function getCaptcha(i){
    $.ajax({
        data: {
            email: i
        },
        url: '/user/getcaptcha',
        type: 'GET'
    })
}
$(function () {


    if($.cookie("user")==null){
        $(".item ").css("display", 'block');
    }else{
        $("#myblog").css("display", 'inline-block');

        
        $(".group").css("display", 'block');
        $.ajax({
            data: {
                username:$.cookie("user")
            },
            url: '/friend/newfriend',
            type: 'GET',
            success: function (data) {

                if (data == true) {
                    $("#friendButton").css("display", 'inline-block');


                } 
            }
        })
    

    }


    $('#registerSubmit').click(function () {
        if (isNull($("#registerName").val()) == true) {
            $('#registerAlert').text('用户名为空！');
            $('#registerAlert').css('display', 'block');
            return false;
        } else if (checkUsername($("#registerName").val()) == true) {

            $('#registerAlert').text('用户名格式错误！');
            $('#registerAlert').css('display', 'block');
            return false;
        }
        else if (isNull($("#registerPass").val()) == true) {
            $('#registerAlert').text('密码为空！');
            $('#registerAlert').css('display', 'block');
            return false;


        } else if (checkPassword($("#registerPass").val()) == true) {
            $('#registerAlert').text('密码格式错误！');
            $('#registerAlert').css('display', 'block');
            return false;


        } else if (isNull($("#registerRepass").val()) == true) {
            $('#registerAlert').text('确定密码为空！');
            $('#registerAlert').css('display', 'block');
            return false;
        } else if (isNull($("#email").val()) == true) {
            $('#registerAlert').text('邮箱为空！');
            $('#registerAlert').css('display', 'block');
            return false;
        } else if (checkEmail($("#email").val()) == true) {
            $('#registerAlert').text('邮箱格式错误！');
            $('#registerAlert').css('display', 'block');
            return false;
        } else if (isNull($("#captcha").val()) == true) {
            $('#registerAlert').text('验证码为空！');
            $('#registerAlert').css('display', 'block');
            return false;
        } else if ($("#registerPass").val() != $("#registerRepass").val()) {
            $('#registerAlert').text('两次密码不一致！');
            $('#registerAlert').css('display', 'block');
            return false;
        }


        $.ajax({
            data: $('#registerForm').serialize(),
            url: '/user/register',
            type: 'POST',
            success: function (data) {

                if (data == 'success') {
                    $('#registerAlert').css('display', 'none');
                    $('#successAlert').css('display', 'block');

                } else if (data == 'error0') {

                    $('#registerAlert').text('验证码错误！');
                    $('#registerAlert').css('display', 'block');
                } else if (data == 'error1') {

                    $('#registerAlert').text('用户已存在！');
                    $('#registerAlert').css('display', 'block');
                }
            }
        })
    })


    $('#forgetNext').click(function () {
        if (isNull($("#forgetName").val()) == true) {
            $('#forgetAlert').text('用户名为空！');
            $('#forgetAlert').css('display', 'block');
            return false;
        }
        else if (isNull($("#forgetEmail").val()) == true) {
            $('#forgetAlert').text('邮箱为空！');
            $('#forgetAlert').css('display', 'block');
            return false;
        }


        $.ajax({
            data: $('#forgetFirstForm').serialize(),
            url: '/user/forgetfirst',
            type: 'POST',
            success: function (data) {

                if (data == 'success') {
                    $('#forgetAlert').css('display', 'none');
                    $('#forgetFirst').css('display', 'none');
                    $('#forgetSecond').css('display', 'block');
                    $('#forgetSubmit').css('display', 'block');

                } else if (data == 'error3') {

                    $('#forgetAlert').text('用户不存在！');
                    $('#forgetAlert').css('display', 'block');
                } else if (data == 'error4') {

                    $('#forgetAlert').text('邮箱不正确！');
                    $('#forgetAlert').css('display', 'block');
                }
            }
        })
    })

    $('#forgetSubmit').click(function () {
        if (isNull($("#forgetPass").val()) == true) {
            $('#forgetAlert').text('密码为空！');
            $('#forgetAlert').css('display', 'block');
            return false;
        }  else if (checkPassword($("#forgetPass").val()) == true) {
            $('#forgetAlert').text('密码格式错误！');
            $('#forgetAlert').css('display', 'block');
            return false;


        }
        else if (isNull($("#forgetRepass").val()) == true) {
            $('#forgetAlert').text('确认密码为空！');
            $('#forgetAlert').css('display', 'block');
            return false;
        } else if ($("#forgetPass").val() != $("#forgetRepass").val()) {
            $('#forgetAlert').text('两次密码不一致！');
            $('#forgetAlert').css('display', 'block');
            return false;
        }


        $.ajax({
            data: $('#forgetSecondForm').serialize(),
            url: '/user/forgetsecond',
            type: 'POST',
            success: function (data) {

                if (data == 'success') {
                    $('#forgetAlert').css('display', 'none');

                    $('#forgetsuccessAlert').css('display', 'block');


                } else if (data == 'error0') {

                    $('#forgetAlert').text('验证码错误！');
                    $('#forgetAlert').css('display', 'block');
                } else if (data == 'error5') {

                    $('#forgetAlert').text('与旧密码重复！');
                    $('#forgetAlert').css('display', 'block');
                }
            }
        })
    })


   






    $('#search').click(function () {
        if (isNull($("#searchContent").val()) == true) {
            return false;
        }

        localStorage.type= $('#searchResult').val();
        localStorage.search =$('#searchContent').val();


        $(location).attr('href', '/search/searchview');



    })



    var registerInterValObj; //timer变量，控制时间
    var registercount = 60; //间隔函数，1秒执行
    var registercurCount;

    $("#getCaptcha").click(function () {
        getCaptcha( $('#email').val())
        registercurCount = registercount;


        $("#getCaptcha").attr("disabled","true" );//设置按钮为禁用状态
        $("#getCaptcha").text(registercurCount + "s");//更改按钮文字
        registerInterValObj = window.setInterval(registerSetRemainTime, 1000); // 启动计时器timer处理函数，1秒执行一次

    })

    function registerSetRemainTime() {
        if (registercurCount == 0) {//超时重新获取验证码
            window.clearInterval(registerInterValObj);// 停止计时器
            $("#getCaptcha").removeAttr("disabled");//移除禁用状态改为可用
            $("#getCaptcha").text("点击获取");
        }else {
            registercurCount--;
            $("#getCaptcha").text(registercurCount + "s");
        }
    }

    var forgetInterValObj; //timer变量，控制时间
    var forgetcount = 60; //间隔函数，1秒执行
    var forgetcurCount;

    $("#forgetGetCaptcha").click(function () {
        getCaptcha( $('#forgetEmail').val())

        forgetcurCount = forgetcount;


        $("#forgetGetCaptcha").attr("disabled","true" );//设置按钮为禁用状态
        $("#forgetGetCaptcha").text(forgetcurCount + "s");//更改按钮文字
        forgetInterValObj = window.setInterval(forgetSetRemainTime, 1000); // 启动计时器timer处理函数，1秒执行一次

    })

    function forgetSetRemainTime() {
        if (forgetcurCount == 0) {//超时重新获取验证码
            window.clearInterval(forgetInterValObj);// 停止计时器
            $("#forgetGetCaptcha").removeAttr("disabled");//移除禁用状态改为可用
            $("#forgetGetCaptcha").text("点击获取");
        }else {
            forgetcurCount--;
            $("#forgetGetCaptcha").text(forgetcurCount + "s");
        }
    }



    $(".container").on("click",".expand",function () {
        $(this).css("display", 'none');
        var i = $(this).attr('name');
        var bt = $(this);


        $.ajax({
            data: {
                id: i
            },
            url: '/article/recommendcontent',
            type: 'GET',
            success: function (data) {

                $("#"+i).html(data);
                var h = $("#"+i).parent().height() + 100;
                $("#"+i).parent().parent().height(h);
                
                


            }
        })

    })

    $('#friendButton').click(function(){
        $(location).attr('href', '/friend/addview');

    })


    $('#loginLogin').click(function () {
        if (isNull($("#loginName").val()) == true) {
            $('#alert').text('用户名为空！');
            $('#alert').css('display', 'block');
            return false;
        } else if (isNull($("#loginPass").val()) == true) {
            $('#alert').text('密码为空！');
            $('#alert').css('display', 'block');
            return false;
        }
        $.ajax({
            data: $('#loginform').serialize(),
            url: '/user/login',
            type: 'POST',
            success: function (data) {

                if (data == 'success') {

                    $(location).attr('href', '/user/userview');
                } else if (data == 'error3') {
                    $('#alert').text('用户不存在！');
                    $('#alert').css('display', 'block');
                } else if (data == 'error2') {
                    $('#alert').text('密码错误！');
                    $('#alert').css('display', 'block');
                }
            }
        })
    })

})

