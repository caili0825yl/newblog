$(function () {
   
    $("#write").click(function(){
        $(location).attr('href', '/article/writeview');
    })

    $.ajax({
        data: {
            username: $.cookie("user")
        },
        url: "/info/getinfo",
        type: "GET",
        success: function (data) {
        
            
            $("#myfriend").attr("href","/friend/friendview");
            $("#blogname").text(data.blogname);
            $("#slogan").text(data.slogan);
            $("#avatar").attr("src", data.avatar);
            $("#nickname").text("昵称：" + data.nickname);
            if (data.gender == 2) {
                $("#gender").text("性别：未知");
            } else if (data.gender == 1) {
                $("#gender").text("性别：男");
            } else if (data.gender == 0) {
                $("#gender").text("性别：女");

            }
            $("#birthday").text("生日：" + data.birthday);
            $("#essay").text(data.essay);
            $("#friend").text(data.friend);

        }
    })

    $.ajax({
        data: {
            username: $.cookie("user")
        },
        url: "/article/getarticlelist",
        type: "GET",
        success: function (data) {
            if (data == null) {
                return false;
            } else {
                $.each(data, function (i, val) {
                    $("#center").append("<li><a href=\"/article/readview?id="+val.id+"\">" + val.title + "</a></li>")
                })
            }



        }
    })


    $.ajax({
        data: {
            username: $.cookie("user")
        },
        url: "/article/getfriendarticle",
        type: "GET",
        success: function (data) {
            $.each(data, function (i, val) {
                if (val.title == "error7") {
                    $("#right").append("<div>您还未添加任何好友</div>");

                    return false;
                } else if (val.title == "error8") {
                    $("#right").append("<div>您的好友尚未发表任何文章</div>");
                    return false;

                } else {
                    
                    $("#right").append("<div><div>您的好友" + val.username + "发表了一篇新文章：<a class=\"text-center\" href=\"/article/readview?id="+val.id+"\">" + val.title + "</a>" + "</div><div class=\"text-center\">" + val.time + "</div><br></div>")

                }

            })

        }
    })
})
