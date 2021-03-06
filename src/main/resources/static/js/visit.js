
       
   
$(function () {
    var page=1;



    var id = GetQueryString("username");
    if(id==$.cookie("user")){
        $(location).attr('href', '/user/userview');

    }

    $.ajax({
        data: {
            username: id
        },
        url: "/info/getinfo",
        type: "GET",
        success: function (data) {
            $(document).attr("title",data.nickname+"的博客");
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
            page:page,

            username: id,
            public:0
        },
        url: "/article/getarticlelist",
        type: "GET",
        success: function (data) {
            if(data.last==true){
                $("#next").css("visibility","hidden");
            }

            if (data.list == null) {
                return false;
            } else {
                $.each(data.list, function (i, val) {
                    $("#center").append("<li><a href=\"/article/readview?id="+val.id+"\">" + val.title + "</a></li>")
                })
            }
        }
    })

   
   
    

    if($.cookie("user")!=null){
 
        $.ajax({
            data: {
                username : id,
                friend:$.cookie("user")
            },
            url: "/friend/isfriend",
            type: "POST",
            success: function (data) {
                if (data == 1) {
                    $("#add").text("已申请好友");
                    $('#add').attr("disabled",true);
                } else if(data==2){
                    $("#add").text("互为好友");
                    $('#add').attr("disabled",true);
                }
            }
        })
    }else{
        $("#add").text("请先登录！");
        $('#add').attr("disabled",true);
    }


    $("#add").click(function(){
        if($.cookie("user")!=null){
            $.ajax({
                data: {
                    username: id,
                    friend:$.cookie("user")
                },
                url: "/friend/apply",
                type: "POST",
                success: function () {
                    $("#add").text("已申请好友");
                    $('#add').attr("disabled",true);
                }
            })
        }
    })

    $("#next").click(function(){
        page=page+1;
       
        $.ajax({
            data: {
                page:page,
                username: id,
                public:0
            },
            url: "/article/getarticlelist",
            type: "GET",
            success: function (data) {
                if (data.list == null) {
                    return false;
                } else {
                    $.each(data.list, function (i, val) {
                        $("#center").empty();
                        $("#center").append("<li><a href=\"/article/readview?id="+val.id+"\">" + val.title + "</a></li>")
                    })
                }
    
                if(data.first==true&&data.last==true){
                    $("#next").css("visibility","hidden");
                    $("#last").css("visibility","hidden");
                    
                }else
                if(data.last==true){
    
                    $("#next").css("visibility","hidden");
                    $("#last").css("visibility","visible");
                }else
    
                if(data.first==true){
                    
                    $("#last").css("visibility","hidden");
                    $("#next").css("visibility","visible");
    
                }else{
                    $("#last").css("visibility","visible");
                    $("#next").css("visibility","visible");
                }
                
    
    
            }
        })
    })
    
    
    
    $("#last").click(function(){
        page=page-1;
        $.ajax({
            data: {
                page:page,
                username:id,
                public:0
            },
            url: "/article/getarticlelist",
            type: "GET",
            success: function (data) {
    
                if (data.list == null) {
                    return false;
                } else {
                    $.each(data.list, function (i, val) {
                        $("#center").empty();
                        $("#center").append("<li><a href=\"/article/readview?id="+val.id+"\">" + val.title + "</a></li>")
                    })
                }
    
                if(data.first==true&&data.last==true){
                    $("#next").css("visibility","hidden");
                    $("#last").css("visibility","hidden");
    
                }else
                if(data.last==true){
    
                    $("#next").css("visibility","hidden");
                    $("#last").css("visibility","visible");
    
                }else
    
                if(data.first==true){
    
                    $("#last").css("visibility","hidden");
                    $("#next").css("visibility","visible");
    
                }else{
                    $("#last").css("visibility","visible");
                    $("#next").css("visibility","visible");
                }
    
               
                
    
    
            }
        })
    })

})
