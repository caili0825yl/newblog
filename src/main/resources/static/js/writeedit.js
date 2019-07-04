$(function(){
 


function textInit(e) {
  e.preventDefault();//阻止默认事件
  var text;
  var clp = (e.originalEvent || e).clipboardData;
  if (clp === undefined || clp === null) {
      text = window.clipboardData.getData("text") || "";
      if (text !== "") {
          if (window.getSelection) {
              var newNode = document.createElement("span");
              newNode.innerHTML = text;
              window.getSelection().getRangeAt(0).insertNode(newNode);
          } else {
              document.selection.createRange().pasteHTML(text);
          }
      }
  } else {
      text = clp.getData('text/plain') || "";
      if (text !== "") {
          document.execCommand('insertText', false, text);
      }
  }
}
$( document ).on( "paste", "#content", function(e) {
  //去除复制样式
  textInit(e);
  //去除复制过来的 标签
  if($('#content').attr('list-layout') == 'true'){ //如果可编辑div .TextInner中存在li
      //找到所有的li
      var $lichild = $('#content').find('li');
      $lichild.each(function () {
          //获取li 的子元素var htmlunList = '';
          var $childEles = $(this).children().not('br');
          var htmlunList = '';
          htmlunList +=   $(this)
              .clone()    //复制元素
              .children() //获取所有子元素
              .remove()   //删除所有子元素
              .end()  //回到选择的元素
              .html();//获取文本值
          if($childEles>0){
              $childEles.each(function(){
                  htmlunList +=$(this).text();
              })
          }
          $(this).html(htmlunList);
      })
  }else{
      var $childEles = $('#content').children().not('br');
      if($childEles.length>0){
          var htmlunList = '';
          htmlunList +=   $('#content')
              .clone()    //复制元素
              .children() //获取所有子元素
              .remove()   //删除所有子元素
              .end()  //回到选择的元素
              .html();//获取文本值
          $childEles.each(function(){
              var tagname =  $(this)[0].tagName;
              htmlunList += $(this).text();
              /*if(tagname == 'SPAN'){
                  htmlunList += $(this).text();
              }
              if(tagname == 'DIV'){
                  htmlunList +='<br>'+ $(this).text();
              }*/
          });
          $('#content').html(htmlunList);
      }

  }
  //最后将容器高度 调整为自适应撑起的高度
  //$(clickTextThis).css('height',$('#content').css('height'));
  //$('.editTextBox').css('height',$('#content').css('height'));
});




    var id = GetQueryString("id");
    
    if(id!=null){
        $.ajax({
            data:{"id":id,"username":$.cookie('user')},
            type:"GET",
            url:"/article/getarticle",
            success:function(data){
                $("#title").val(data.title);
                $("#content").html(data.content);
            }
        })  
    }
        


    $("#insert").click(function(){
      
        
     $('#content').append("<img src=\""+$("#src").val()+"\"   width=\"60%\"  >");

    })
    


    $("#confirm").click(function(){
      if($("#content").html().length>5000){
        alert("正文超过5000字！请删改");
        return false;
      } else if (isNull($("#content").html()) == true) {
        alert("请输入正文！");
        return false;
    }  else if(isNull($("#title").val()) == true){
        alert("请输入标题！");
        return false;
    }
      
        var data;
      if(id!=null){
        data= {"id":id,"username":$.cookie('user'),"title":$("#title").val(),"content":$("#content").html(),"privacy":$("#privacy").is(':checked')}
      }else{
        data= {"username":$.cookie('user'),"title":$("#title").val(),"content":$("#content").html(),"privacy":$("#privacy").is(':checked')}
      }
    $.ajax({
        data:data,
        type:"POST",
        url:"/article/editarticle",
        success:function(){
           $(location).attr('href', "/user/userview");
         
        }

    })   
       })
})