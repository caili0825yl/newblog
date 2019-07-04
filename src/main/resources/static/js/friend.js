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
               

                $("#main").append("<li><img height=\"50px\" width=\"50px\" src=\""+val.avatar+"\"><a href=\"/user/visitview?username="+val.username+"\">" + val.nickname + "</a></li>");


            })
        }


    })

})