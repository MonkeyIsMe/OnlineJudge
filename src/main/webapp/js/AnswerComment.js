var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?AnswerId=");
var aid = argsIndex[1];
var row = 1;  //页数

window.onload = function () {
	content = CKEDITOR.replace("content"); //参数‘content’是textarea元素的name属性值，而非id属性值
}

$(function(){
	$.post(
			"QuerySingleAnswer.action",
			{
				answer_id:aid,
			}, 
			function(data) {
				var datas = JSON.parse(data);
				//console.log(datas);
				$("#aname").append(datas.answerName);
				$("#ainfo").append(datas.answerInfo);
			}
	);
	
});

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountCommentByAnswerId.action",
			{
				answer_id:aid
			},
			function(data){
				var data = JSON.parse(data);
				var sum = data.CommentCount;
				count = Math.ceil(sum/10);
				var total = "共" + Math.ceil(sum/10) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryCommentByAnswerId.action",
			{
				answer_id:aid,
				page:row,
				limit:10
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				    for( var i = 0; i < data.length; i++ ) {
				    	var str = "<fieldset class=layui-elem-field style=width:80%;margin-left:10%>" +
				    	"<legend>"+"用户 "+data[i].userAccount+" 发表于 "+data[i].commentTime+"</legend>" +
				    			"<div class=layui-field-box>"+data[i].commentInfo+"</div></fieldset>"
				    	$("#comment").append(str);
				}
			}
			);
	
});


function PrevPage(){	
	if(row == 1){
		alert("没有前一页了");
	}
	else{
		row--;
		$("#comment").html("");
		$.post(
				"QueryCommentByAnswerId.action",
				{
					answer_id:aid,
					page:row,
					limit:10
				},
				function(data){
					var data = JSON.parse(data);
					console.log(data);
					    for( var i = 0; i < data.length; i++ ) {
					    	var str = "<fieldset class=layui-elem-field style=width:80%;margin-left:10%>" +
					    	"<legend>"+"用户 "+data[i].userAccount+" 发表于 "+data[i].commentTime+"</legend>" +
					    			"<div class=layui-field-box>"+data[i].commentInfo+"</div></fieldset>"
					    	$("#comment").append(str);
					}
				}
				);
	}
}

function NextPage(){
	if(row == count){
		alert("没有后一页了");
	}
	else{
		row ++;
		$("#comment").html("");
		$.post(
				"QueryCommentByAnswerId.action",
				{
					answer_id:aid,
					page:row,
					limit:10
				},
				function(data){
					var data = JSON.parse(data);
					console.log(data);
					    for( var i = 0; i < data.length; i++ ) {
					    	var str = "<fieldset class=layui-elem-field style=width:80%;margin-left:10%>" +
					    	"<legend>"+"用户 "+data[i].userAccount+" 发表于 "+data[i].commentTime+"</legend>" +
					    			"<div class=layui-field-box>"+data[i].commentInfo+"</div></fieldset>"
					    	$("#comment").append(str);
					}
				}
				);
	}

}




$(function(){

	$("#add_comment").click(function(){
		var comment =  CKEDITOR.instances.content.getData(); //获取值
		
		if(comment == null || comment == ""){
			alert("评论内容不能为空！");
		}
		else{
			$.post(
					"AddComment.action",
					{
						answer_id:aid,
						comment_info:comment,
					}, 
					function(data) {
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("发表失败！");
						    window.location.replace(url);
						}
						else{
							alert("发表成功!");
						    window.location.replace(url);
						}
					}
			);
		}
		

	})
	
});