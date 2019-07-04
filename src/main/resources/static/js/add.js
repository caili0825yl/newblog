$(function(){
    $.ajax({
        data:{
            username:$.cookie("user")
        },
        url:"/friend/checkadd",
        type:"GET",
        success:function(data){
            if(data.length==0){
                $("#main").append("<div>当前没有好友申请</div>");
            }else{
                $.each(data,function(i,val){

                    $("#main").append("<li><img height=\"50px\" width=\"50px\" src=\""+val.avatar+"\"><a href=\"/user/visitview?username="+val.username+"\">" + val.nickname + "</a>"+
                    "<button type=\"button\" id=\""+val.username+"\" class=\"add\">加为好友</button></li>");
    
                })
            }
           
           
        }

    })

    $(".container").on("click",".add",function (){
        $(this).attr("disabled",true);
        $(this).text("已添加");
        var i = $(this).attr('id');
        var bt = $(this);
        $.ajax({
            data:{
                username:$.cookie("user"),
                friend:i
            },
            url:"/friend/add",
            type:"POST"
            
    
        }) 



    })

})