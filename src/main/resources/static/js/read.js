$(function(){
    var id = GetQueryString("id");
    var bloguser;
    if($.cookie("user")==null){
        $("#likebt").attr("disabled","true");

    }
    
        $.ajax({
            data:{"id":id},
            type:"GET",
            url:"/article/getarticle",
            success:function(data){
                bloguser=data.username;
                
                if(data.username!=$.cookie("user")){
                    
                    
                    $.ajax({
                        data:{"id":id,"username":$.cookie("user")},
                        type:"GET",
                        url:"/like/islike",
                        success:function(data){
                           if(data=="yes"){
                            $("#likebt").attr("disabled","true");
                            $("#likebt").text("已点赞");
        
                           }
                        }
                    })  
                }else{
                    $("#admin").css("display","block");

                    $("#likebt").attr("disabled","true");
                }
                $("#like").text(data.like);
                $("#title").text(data.title);
                $("#time").text(data.time);
                $("#content").html(data.content);
            }
        })  
    
       
        $("#edit").click(function(){
            $(location).attr("href","/article/writeview?id="+id)

        })

        $("#back").click(function(){
            $(location).attr("href","/user/visitview?username="+bloguser)
        })

        $("#confirm").click(function(){
            $.ajax({
                data:{"id":id,"username":$.cookie("user")},
                type:"POST",
                url:"/article/delete",
                success:function(){
                    $(location).attr("href","/user/userview")



                }
               
            })  
        })

        $("#likebt").click(function(){
            $.ajax({
                data:{"id":id,"username":$.cookie("user")},
                type:"POST",
                url:"/like/like",
                success:function(){
                    $("#likebt").attr("disabled","true");
                    $("#likebt").text("已点赞");
                    var like=$("#like").text();
                    $("#like").text(parseInt(like)+1);


                }
               
            })  
        })
})