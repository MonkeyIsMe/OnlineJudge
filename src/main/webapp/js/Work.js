var row = 1;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountWork.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.WorkCount;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});

$(function(){
	$.post(
			"QueryAllWork.action",
			{
				page:row,
				limit:30
			}, 
			function(data) {
				var data = JSON.parse(data);
				//console.log(data);
				for(var i = 0 ; i < data.length ; i ++){
				    for( var i = 0; i < data.length; i++ ) {
				        //动态创建一个tr行标签,并且转换成jQuery对象
				        var $trTemp = $("<tr ></tr>");
				        //往行里面追加 td单元格
				        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">"+ data[i].workId +"</td>");
				        $trTemp.append("<td onclick=SeeProblem('"+data[i].workId+"') style=" + "text-align:center;font-size:16px"  + ">"  +data[i].workName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" +data[i].workOwner  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" + data[i].workCreatTime+"</td>");
				        // $("#J_TbData").append($trTemp);
				        $trTemp.appendTo("#WorkList");
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
				"QueryAllWork.action",
				{
					page:row,
					limit:30
				}, 
				function(data) {
					var data = JSON.parse(data);
					//console.log(data);
					for(var i = 0 ; i < data.length ; i ++){
					    for( var i = 0; i < data.length; i++ ) {
					        //动态创建一个tr行标签,并且转换成jQuery对象
					        var $trTemp = $("<tr ></tr>");
					        //往行里面追加 td单元格
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">"+ data[i].workId +"</td>");
					        $trTemp.append("<td onclick=SeeProblem('"+data[i].workId+"') style=" + "text-align:center;font-size:16px"  + ">"  +data[i].workName +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" +data[i].workOwner  +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" + data[i].workCreatTime+"</td>");
					        // $("#J_TbData").append($trTemp);
					        $trTemp.appendTo("#WorkList");
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
				"QueryAllWork.action",
				{
					page:row,
					limit:30
				}, 
				function(data) {
					var data = JSON.parse(data);
					//console.log(data);
					for(var i = 0 ; i < data.length ; i ++){
					    for( var i = 0; i < data.length; i++ ) {
					        //动态创建一个tr行标签,并且转换成jQuery对象
					        var $trTemp = $("<tr ></tr>");
					        //往行里面追加 td单元格
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">"+ data[i].workId +"</td>");
					        $trTemp.append("<td onclick=SeeProblem('"+data[i].workId+"') style=" + "text-align:center;font-size:16px"  + ">"  +data[i].workName +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" +data[i].workOwner  +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" + data[i].workCreatTime+"</td>");
					        // $("#J_TbData").append($trTemp);
					        $trTemp.appendTo("#WorkList");
					    }
					}
			}
		);
	}

}

$(function(){
	$.post(
			"QueryWorkByUserAccount.action",
			{
			}, 
			function(data) {
				var data = JSON.parse(data);
				//console.log(data);
				for(var i = 0 ; i < data.length ; i ++){
				    for( var i = 0; i < data.length; i++ ) {
				        //动态创建一个tr行标签,并且转换成jQuery对象
				        var $trTemp = $("<tr ></tr>");
				        //往行里面追加 td单元格
				        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].workId +"</td>");
				        $trTemp.append("<td onclick=SeeProblem('"+data[i].ProblemId+"') style=" + "text-align:center;color:white"  + ">"  +data[i].workName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workOwner  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + data[i].workCreatTime+"</td>");
				        // $("#J_TbData").append($trTemp);
				        $trTemp.appendTo("#MyWorkList");
				    }
				}
		}
	);
	
});

$(document).ready(function(){
	
	$("#add_work").click(function(){
		//console.log(cid);
		
		var name = $("#add_name").val();
		var begin = $("#add_begin").val();
		var end = $("#add_end").val();
		var info = $("#add_info").val(); 
		var type = $("input[name='work']:checked").val();
		
		var flag = 0;
		if(type == "考试") flag = 1;
		
		if(name == null || name == "" || begin == null || begin == "" || end == null || end == "" || info == null || info == "" || type == null || type == ""){
			alert("所有项均为非空!");
		}
		else{
			$.post(
					"AddWork.action",
					{
						work_name:name,
						work_starttime:begin,
						work_endtime:end,
						work_info:info,
						work_flag:flag,
					},
					function(data){
						var data = JSON.parse(data);
						var WorkId = data.WorkId;
						//console.log(data);
						if(WorkId != null){
							alert("添加成功！");
						    var url = "AddProblemForWork.html?WorkId=" + WorkId;
						    window.location.replace(url);
						}
						else{
							alert("添加失败!");
							window.location.replace("ManagerWorkSet.html");
						}
					}
					);
		}
		
		//console.log(name);

	})
	
})

function SeeProblem(wid){
	var url = "WorkPage.html?WorkId=" + wid;
	window.location.href = url;
}