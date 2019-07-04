$(function () {
    $.ajax({
        data: {
            type: localStorage.type,
            search: localStorage.search
        },
        url: "/search/search",
        type: "GET",
        success: function (data) {
            if(data.length==0){
                $("#main").append("<div>搜索结果为空！</div>");

            }
            $.each(data, function (i, val) {
                if (localStorage.type == "blog") {

                    $("#main").append("<div><a href=\"/user/visitview?username="+val.username+"\">" + val.blogname + "</a>&nbsp&nbsp<span>"+val.nickname+"的博客</span></div><br>");

                } else if (localStorage.type == "article") {
                    $("#main").append("<div><a href=\"/article/readview?id="+val.id+"\">" + val.title + "</a></a>&nbsp&nbsp<span>作者："+val.nickname+"</span></div><br>");
                } else if (localStorage.type == "nickname") {
                    $("#main").append("<div><img src=\""+val.avatar+"\"><a href=\"/user/visitview?username="+val.username+"\">" + val.nickname + "</a></div>");
                }

            })
        }


    })

})