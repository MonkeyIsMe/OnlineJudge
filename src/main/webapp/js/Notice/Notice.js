/**
 * Created by CallMeDad on 2019/9/22.
 */

var row = 1;  //页数
var count; //总记录数
var nid; //记录第几行的编号
var cnt = 0;


$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountNotice.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.NoticeCount;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryNoticeByPageSize.action",
			{
				page:row,
				limit:10
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				    for( var i = 0; i < data.length; i++ ) {
				    	var str = "<fieldset class=layui-elem-field style=width:80%;margin-left:10%>" +
				    	"<legend>"+"用户 "+data[i].noticePeople+" 发表于 "+data[i].noticeTime+"</legend>" +
				    			"<div class=layui-field-box>"+data[i].noticeInfo+"</div></fieldset>"
				    	$("#notice").append(str);
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
		$("#notice").html("");
		$.post(
				"QueryNoticeByPageSize.action",
				{
					page:row,
					limit:10
				},
				function(data){
					var data = JSON.parse(data);
					//console.log(data);
					    for( var i = 0; i < data.length; i++ ) {
					    	var str = "<fieldset class=layui-elem-field style=width:80%;margin-left:10%>" +
					    	"<legend>"+"用户 "+data[i].noticePeople+" 发表于 "+data[i].noticeTime+"</legend>" +
					    			"<div class=layui-field-box>"+data[i].noticeInfo+"</div></fieldset>"
					    	$("#notice").append(str);
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
		$("#notice").html("");
		$.post(
				"QueryNoticeByPageSize.action",
				{
					page:row,
					limit:10
				},
				function(data){
					var data = JSON.parse(data);
					//console.log(data);
					    for( var i = 0; i < data.length; i++ ) {
					    	var str = "<fieldset class=layui-elem-field style=width:80%;margin-left:10%>" +
					    	"<legend>"+"用户 "+data[i].noticePeople+" 发表于 "+data[i].noticeTime+"</legend>" +
					    			"<div class=layui-field-box>"+data[i].noticeInfo+"</div></fieldset>"
					    	$("#notice").append(str);
					}
				}
				);
	}

}