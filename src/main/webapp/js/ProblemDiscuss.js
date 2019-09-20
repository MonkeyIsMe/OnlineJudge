var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?ProblemId=");
var pid = argsIndex[1];
var row = 1;  //页数

window.onload = function () {
	content = CKEDITOR.replace("content"); //参数‘content’是textarea元素的name属性值，而非id属性值
}

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountAnswerByProblem.action",
			{
				problem_id:pid,
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.AnswerCount;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});

$(function(){
	$.post(
			"QuerySingleProblem.action",
			{
				problem_id:pid,
			}, 
			function(data) {
				var datas = JSON.parse(data);
				
				$("#pname").append(datas.problemName);
				$("#pinfo").append(datas.problemInfo);
			}
	);
	
});

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryAnswerByProblemId.action",
			{
				problem_id:pid,
				page:row,
				limit:20
			},
			function(data){
				var data = JSON.parse(data);
				for(var i = 0 ; i < data.length ; i ++){
				    for( var i = 0; i < data.length; i++ ) {
				        //动态创建一个tr行标签,并且转换成jQuery对象
				        var $trTemp = $("<tr ></tr>");
				        //往行里面追加 td单元格
				        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].answerId +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].answerName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].answerTime +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
				        		'<a ><span class=" see_comment glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:15px" ></span></a>'
				        		+"</td>");
				        $trTemp.appendTo("#KnowList");
				    }
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
		$("#KnowList").html("");
		$.post(
				"QueryAnswerByProblemId.action",
				{
					problem_id:pid,
					page:row,
					limit:20
				},
				function(data){
					var data = JSON.parse(data);
					for(var i = 0 ; i < data.length ; i ++){
					    for( var i = 0; i < data.length; i++ ) {
					        //动态创建一个tr行标签,并且转换成jQuery对象
					        var $trTemp = $("<tr ></tr>");
					        //往行里面追加 td单元格
					        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].answerId +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].answerName +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].answerTime +"</td>");
					        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
					        		'<a ><span class="see_comment glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:15px" ></span></a>'
					        		+"</td>");
					        $trTemp.appendTo("#KnowList");
					    }
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
		$("#KnowList").html("");
		$.post(
				"QueryAnswerByProblemId.action",
				{
					problem_id:pid,
					page:row,
					limit:20
				},
				function(data){
					var data = JSON.parse(data);
					for(var i = 0 ; i < data.length ; i ++){
					    for( var i = 0; i < data.length; i++ ) {
					        //动态创建一个tr行标签,并且转换成jQuery对象
					        var $trTemp = $("<tr ></tr>");
					        //往行里面追加 td单元格
					        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].answerId +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].answerName +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].answerTime +"</td>");
					        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
					        		'<a ><span class="see_comment glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:15px" ></span></a>'
					        		+"</td>");
					        $trTemp.appendTo("#KnowList");
					    }
					}
				}
				);
	}

}

$(document).ready(function(){
	
	  //用于读取所选表行单元格数据（值）的代码
	  $("#myTable").on('click','.see_comment',function(){
	    //获得当前行
	    var currentRow=$(this).closest("tr"); 
	    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
	    
	    aid = col1;
	    var url = "SeeComment.html?AnswerId=" + aid;
	    window.location.replace(url);
	    
	  });
	  
	  $("#add_answer").click(function(){
			var answer =  CKEDITOR.instances.content.getData(); //获取值
			var name = $("#answer_name").val();
			
			if(answer == null || answer =="" || name == null || name == ""){
				alert("所有输入均为非空！");
			}
			else{
				$.post(
						"AddAnswer.action",
						{
							problem_id:pid,
							answer_info:answer,
							answer_name:name
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