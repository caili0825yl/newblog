


(window.onresize = function() {
    var win_height = $(window).height();
    var win_width = $(window).width();
    if (win_width <= 768) {
        $(".tailoring-content").css(
            {
                "top" : (win_height - $(".tailoring-content")
                    .outerHeight()) / 2,
                "left" : 0
            });
    } else {
        $(".tailoring-content").css(
            {
                "top" : (win_height - $(".tailoring-content")
                    .outerHeight()) / 2,
                "left" : (win_width - $(".tailoring-content")
                    .outerWidth()) / 2
            });
    }
})();

// 选择文件触发事件
function selectImg(file) {
    //文件为空，返回
    if (!file.files || !file.files[0]) {
        return;
    }
    $(".tailoring-container").toggle();
    var reader = new FileReader();
    reader.onload = function(evt) {
        var replaceSrc = evt.target.result;
        // 更换cropper的图片
        $('#tailoringImg').cropper('replace', replaceSrc, false);// 默认false，适应高度，不失真
    }
    reader.readAsDataURL(file.files[0]);
}
// cropper图片裁剪
$('#tailoringImg').cropper({
    aspectRatio : 1 / 1,// 默认比例
    preview : '.previewImg',// 预览视图
    guides : true, // 裁剪框的虚线(九宫格)
    autoCropArea : 0.5, // 0-1之间的数值，定义自动剪裁区域的大小，默认0.8
    movable : false, // 是否允许移动图片
    dragCrop : true, // 是否允许移除当前的剪裁框，并通过拖动来新建一个剪裁框区域
    movable : true, // 是否允许移动剪裁框
    resizable : true, // 是否允许改变裁剪框的大小
    zoomable : true, // 是否允许缩放图片大小
    mouseWheelZoom : true, // 是否允许通过鼠标滚轮来缩放图片
    touchDragZoom : true, // 是否允许通过触摸移动来缩放图片
    rotatable : true, // 是否允许旋转图片
    crop : function(e) {
        // 输出结果数据裁剪图像。
    }
});
// 旋转
$(".cropper-rotate-btn").on("click", function() {
    $('#tailoringImg').cropper("rotate", 45);
});
// 复位
$(".cropper-reset-btn").on("click", function() {
    $('#tailoringImg').cropper("reset");
});
// 换向
var flagX = true;
$(".cropper-scaleX-btn").on("click", function() {
    if (flagX) {
        $('#tailoringImg').cropper("scaleX", -1);
        flagX = false;
    } else {
        $('#tailoringImg').cropper("scaleX", 1);
        flagX = true;
    }
    flagX != flagX;
});
var cas;
var base64;
// 确定按钮点击事件
$("#sureCut").on("click", function() {
    if ($("#tailoringImg").attr("src") == null) {
        return false;
    } else {

        cas = $('#tailoringImg').cropper('getCroppedCanvas');// 获取被裁剪后的canvas
        base64 = cas.toDataURL('image/jpeg'); // 转换为base64

        $("#finalImg").prop("src", base64);// 显示图片
        closeTailor();// 关闭裁剪框
    }
});

$("#sure").on("click", function() {
    uploadFile(encodeURIComponent(base64))//编码后上传服务器
    alert("成功修改！");


});

// 关闭裁剪框
function closeTailor() {
    $(".tailoring-container").toggle();
}

//ajax请求上传
function uploadFile(file) {
    $.ajax({
        url : '/info/editavatar',
        type : 'POST',
        data : "avatar=" + file+"&username="+$.cookie("user"),



        async : true,
        success : function() {
            console.log(data)
        },
        
    });
}


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