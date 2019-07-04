
$(function () {

    $.ajax({
        url: '/article/recommend',
        type: 'GET',
        success: function (data) {
            $.each(data, function (i, val) {
                console.log(i);
                

                if (i == 0) {
                    
                    $('#link1').attr("href", "/article/readview?id="+val.id);
                    $("#bt1").attr("name",val.id);
                    $('#title1').text(val.title);
                   $('#username1').text("作者："+val.username);
                    $('#content1').html(val.first);
                    $("#content1").attr("id",val.id);

                } else if (i == 1) {
                    $('#link2').attr("href", "/article/readview?id="+val.id);
                    $("#bt2").attr("name",val.id);

                    $('#title2').text(val.title);
                    $('#username2').text("作者："+val.username);
                    $('#content2').html(val.first);
                    $("#content2").attr("id",val.id);

                } else if (i == 2) {
                    $('#link3').attr("href", "/article/readview?id="+val.id);
                    $("#bt3").attr("name",val.id);
                    $('#title3').text(val.title);
                   $('#username3').text("作者："+val.username);
                    $('#content3').html(val.first);
                    $("#content3").attr("id",val.id);

                }
            })
        }
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



