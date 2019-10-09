var row = 1;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountWork.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				var sum = data.WorkCount;
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
			"QueryAllCourse.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
		           for(var i=0 ;i<data.length;i++){ //几个人有几个checkbox
		        	   $("#allTime").append(
				                "<span>"+
				                    "<input  type='checkbox' class='time' name='course' value='"+data[i].courseId+"' title='"+data[i].courseName+"'>" 
				                 +"</span>");
		           }
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
  		var json =[];
		$('input[name="course"]:checked').each(function(){
			var obj = {};
			obj.courseId = $(this).val();
			json.push(obj);//向数组中添加元素  
			});
		var jsonText = JSON.stringify(json);
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
							
							$.post(
									"AddWorkForCourse.action",
									{
										work_id:wid,
										course_info:jsonText,
									},
									function(data){
										alert("添加成功！");
									    var url = "AddProblemForWork.html?WorkId=" + WorkId;
									    window.location.replace(url);
									}
									);

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
	
	$.post(
			"QuerySingleWork.action",
			{
				work_id:wid
			},
			function(data){
				var data = JSON.parse(data);
				console.log(data);
				var flag = "考试";
				if(data.workFlag == '0'){
					flag = "作业";
				}
				var time = getNowFormatDate();
				if(data.workBeginTime > time){
					alert("该" + flag + data.workName +"还未开始，请等待开始！");
				}
				else if(data.workEndTime < time){
					alert("该" + flag + data.workName +"已经结束！");
				}
				else{
					var url = "WorkPage.html?WorkId=" + wid;
					window.location.href = url;
				}
			}
			);


}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}