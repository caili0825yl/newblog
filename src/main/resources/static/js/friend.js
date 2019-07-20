$(function () {
   

    $.ajax({
        data: {
           username:$.cookie("user")
        },
        url: "/friend/getlist",
        type: "GET",
        success: function (data) {
            if(data.length==0){
                $("#main").append("<div>未添加过好友！</div>");

            }
            $.each(data, function (i, val) {
               

                $("#main").append("<li><img height=\"50px\" width=\"50px\" src=\""+val.avatar+"\"><a href=\"/user/visitview?username="+val.username+"\">" + val.nickname + "</a>"+
                "<button class=\"delete btn btn-danger \" type=\"button\" style=\"margin:0px 10px\" id=\""+val.username+"\">删除好友</button></li>");


            })
        }


    })



    $(".container").on("click",".delete",function (){
        var i = $(this).attr('id');
        $(this).attr("disabled",true);
        $(this).text("已删除");
        var bt = $(this);
        $.ajax({
            data:{
                username:$.cookie("user"),
                friend:i
            },
            url:"/friend/deny",
            type:"POST"
            
    
        }) 



    })
})