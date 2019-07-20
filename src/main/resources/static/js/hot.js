$(function(){
    

  

    $.ajax({
        url: '/article/hot?page=1',
        type: 'GET',
        success: function (data) {
            $.each(data, function (i, val) {

                $('#main').append(" <div class=\"card\"><div style=\"margin-left:20px\">"+

                    "<div><a href=\"/article/readview?id="+val.id+"\"><h4 style=\"display: inline-block\">"+val.title+"</h4></a>"+
                        "<div>作者："+val.username+"</div> </div><div id=\""+val.id+"\">"+val.first+"</div> <button name=\""+val.id+"\"  type=\"button\" class=\"btn btn-outline-primary border-0 expand\">"+
                        "阅读全文</button></div> </div>" )

                
            })
        }
    })
    
    var n=2;
    $("#more").click(function(){
        $.ajax({
            data:{
                page:n
            },
            url: '/article/hot',
            type: 'GET',
            success: function (data) {
                console.log(data.length);
                
                if(data.length==0||n>10){
                    $("#more").css("display","none");
                }else{
                    $.each(data, function (i, val) {
    
                        $('#main').append(" <div class=\"card\"><div style=\"margin-left:20px\">"+
        
                            "<div><a href=\"/article/readview?id="+val.id+"\"><h4 style=\"display: inline-block\">"+val.title+"</h4></a>"+
                                "<div>作者："+val.username+"</div> </div><div id=\""+val.id+"\">"+val.first+"</div> <button name=\""+val.id+"\"  type=\"button\" class=\"btn btn-outline-primary border-0 expand\">"+
                                "阅读全文</button></div> </div>" )
        
                        
                    })
                    n++;
                }
               
            }
        })
    })


    



})