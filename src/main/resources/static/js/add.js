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
                    "<button class=\"add btn btn-success \" type=\"button\" style=\"margin:0px 10px\" name=\""+val.username+"\"  id=\"add"+val.username+"\">加为好友</button>"+
                    "<button class=\"deny btn btn-danger\" type=\"button\"  name=\""+val.username+"\" id=\"deny"+val.username+"\">拒绝</button></li>");
    
                })
            }
           
           
        }

    })

    $(".container").on("click",".add",function (){
        var i = $(this).attr('name');

        $(this).attr("disabled",true);
        $(this).text("已添加");
        $("#deny"+i).css("display","none");
        
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
    $(".container").on("click",".deny",function (){
        var i = $(this).attr('name');
        $(this).attr("disabled",true);
        $(this).text("已拒绝");
        $("#add"+i).css("display","none");
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