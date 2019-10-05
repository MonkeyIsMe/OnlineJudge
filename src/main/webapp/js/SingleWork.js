var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?WorkId=");
var wid = argsIndex[1];

var row = 1;
var select_know = "none";
var count = 1;
var pid;
var pflag;
var pname;
var pdegree;
var ppeople;
var wp = [];
var select_wp = [];



$(document).ready(function(){
	

	
	$(function(){
		$.ajaxSettings.async = false;
		$.post(
				"QueryAllKnowledge.action",
				{
					
				}, 
				function(data) {
					var datas = JSON.parse(data);
					//console.log(datas);
					for(var i = 0; i < datas.length; i ++){
						$("#know").append("<option value='"+datas[i].knowledgeId+"'>"+datas[i].knowledgeName+"</option>");
					}
				}
		);
		
	});
	
	$(function(){
		$.ajaxSettings.async = false;
		$.post(
				"CountProblemByKnowledgeId.action",
				{
					knowledge_id:select_know,
				},
				function(data){
					var data = JSON.parse(data);
					//console.log(data);
					var sum = data.count;
					count = Math.ceil(sum/15);
					$("#TotalPage").html("");
					$("#NowPage").html("");
					var total = "共" + Math.ceil(sum/15) + "页";
					$("#TotalPage").append(total);
					$("#NowPage").append("，当前第" + row + "页");
				}
				);
		
	});
	
	
	$.post(
			"QuerySingleWork.action",
			{
				work_id:wid,
			}, 
			function(data) {
				var datas = JSON.parse(data);
				//console.log(datas);
				$("#update_name").val(datas.workName);
				$("#update_owner").val(datas.workOwner);
				$("#update_begin").val(datas.workBeginTime);
				$("#update_end").val(datas.workEndTime);
				$("#update_info").append(datas.workInfo);
				if(datas.workFlag == 1){
					$("input[name=work][value=exam]").attr("checked",true);
				}
				else {
					$("input[name=work][value=homework]").attr("checked",true);
				}
			}
	);
	
	
    $.post(
            "QueryPublicProblem.action",
            {
            	work_id:wid
            },
            function(data) {
                var data = JSON.parse(data);
                //console.log(data);
                    for( var i = 0; i < data.length; i++ ) {
                    	var json = {};
                    	json.ProblemId = data[i].problemId;
                    	select_wp.push(json);
                        //动态创建一个tr行标签,并且转换成jQuery对象
                        var $trTemp = $("<tr ></tr>");
                        //往行里面追加 td单元格
                        var WorkFlag;
                        if(data[i].workFlag == 0) WorkFlag = "不公开";
                        else WorkFlag = "公开";
    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].problemId +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemName  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemDegree  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemPeople  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
    			        		'<span class="choosed glyphicon glyphicon-trash" style="cursor:pointer;margin-left:15px"></span>'
    			        		+"</td>");
                        // $("#J_TbData").append($trTemp);
                        $trTemp.appendTo("#selected_problem");
                    }
            }
        );
	
    $.post(
            "QueryProblemByKnowledgeId.action",
            {
                page:row,
                limit:15,
                knowledge_id:"none",
            },
            function(data) {
                var data = JSON.parse(data);
                //console.log(data);
                    for( var i = 0; i < data.length; i++ ) {
                        //动态创建一个tr行标签,并且转换成jQuery对象
                        var $trTemp = $("<tr ></tr>");
                        //往行里面追加 td单元格
                        var WorkFlag;
                        if(data[i].workFlag == 0) WorkFlag = "不公开";
                        else WorkFlag = "公开";
    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].problemId +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemName  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemDegree  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemPeople  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
    			        		'<span class="find glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:15px" data-toggle="modal" data-target="#myModal"></span>'
    			        		+"</td>");
                        // $("#J_TbData").append($trTemp);
                        $trTemp.appendTo("#KnowList");
                    }
            }
        );
	
	$("#sure").click(function(){
		
		$.ajaxSettings.async = false;
		$.post(
				"CountProblemByKnowledgeId.action",
				{
					knowledge_id:select_know,
				},
				function(data){
					var data = JSON.parse(data);
					//console.log(data);
					var sum = data.count;
					count = Math.ceil(sum/15);
					$("#TotalPage").html("");
					$("#NowPage").html("");
					var total = "共" + Math.ceil(sum/15) + "页";
					$("#TotalPage").append(total);
					$("#NowPage").append("，当前第" + row + "页");
				}
				);
		
		select_know = $("#know").val();
		$("#KnowList").html("");
	    $.post(
	            "QueryProblemByKnowledgeId.action",
	            {
	                page:row,
	                limit:15,
	                knowledge_id:select_know,
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	                        var WorkFlag;
	                        if(data[i].workFlag == 0) WorkFlag = "不公开";
	                        else WorkFlag = "公开";
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].problemId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemName  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemDegree  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemPeople  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<span class="find glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:15px" data-toggle="modal" data-target="#myModal"></span>'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#KnowList");
	                    }
	            }
	        );
	})
	
	$("#update_work").click(function(){
		
		var work_name = $("#update_name").val();
		var work_begin = $("#update_begin").val();
		var work_end = $("#update_end").val();
		var work_info = $("#update_info").val();
		var work_flag = $("input[type='radio']:checked").val();
		
  		var json =[];
		$('input[name="course"]:checked').each(function(){
			var obj = {};
			obj.courseId = $(this).val();
			json.push(obj);//向数组中添加元素  
			});
		var jsonText = JSON.stringify(json);
		
		var wflag = 0;
		if(work_flag == "考试") wflag = 1;
		
		if(work_name == null || work_name == "" || work_begin == null || work_begin == "" || work_end == null || work_end == "" || work_info == null || work_info == "" ){
			alert("所有项均为非空!");
		}
		else{
			$.post(
					"UpdateWork.action",
					{
						work_id:wid,
						work_name:work_name,
						work_starttime:work_begin,
						work_endtime:work_end,
						work_info:work_info,
						work_flag:wflag
					}, 
					function(data) {
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Success"){
							
							$.post(
									"AddWorkForCourse.action",
									{
										work_id:wid,
										course_info:jsonText,
									},
									function(data){
										alert("更新成功！");
										window.location.replace("ManagerWorkSet.html");
									    window.location.replace(url);
									}
									);
							
						}
						else{
							alert("更新失败!");
							window.location.replace("ManagerWorkSet.html");
						}
					}
			);
		}
		

		
	})
	
	  $("#KnowList").on('click','.find',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    var col2=currentRow.find("td:eq(1)").text(); //获得当前行第一个TD值
		    var col3=currentRow.find("td:eq(2)").text(); //获得当前行第一个TD值
		    var col4=currentRow.find("td:eq(3)").text(); //获得当前行第一个TD值
		    var col5=currentRow.find("td:eq(4)").text(); //获得当前行第一个TD值
		    
		    pid = col1;
		    pflag = col2;
		    pname = col3;
		    pdegree = col4;
		    ppeople = col5;
		    
		  });
	
	
	  $("#select_problem").on('click','.delete',function(){
		    //获得当前行
		  //console.log(465);
		   
		   var currentRow=$(this).closest("tr"); 
		   var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		   
		   for(var i = 0;i < wp.length; i++){
			   if(wp[i].ProblemId == col1){
				   wp.splice(i,1);
			   }
		   }
		   
		   $(this).closest("tr").remove(); 
		   
		  });
	  
	  $("#selected_problem").on('click','.choosed',function(){
		    //获得当前行
		   //console.log();
		   
		   var currentRow=$(this).closest("tr"); 
		   var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		   
		   for(var i = 0;i < select_wp.length; i++){
			   if(select_wp[i].ProblemId == col1){
				   select_wp.splice(i,1);
			   }
		   }
		   
		   $(this).closest("tr").remove(); 
		   
		  });
	
	  
	$("#add_problem").click(function(){
		
		var json = {};
		json.ProblemId = pid;
		wp.push(json);
		
		
        var $trTemp = $("<tr ></tr>");
        //往行里面追加 td单元格
        $trTemp.append("<td style=" + "text-align:center"  + ">"+ pid +"</td>");
        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + pflag +"</td>");
        $trTemp.append("<td style=" + "text-align:center"  + ">" + pflag +"</td>");
        $trTemp.append("<td style=" + "text-align:center"  + ">" + pname  +"</td>");
        $trTemp.append("<td style=" + "text-align:center"  + ">" + pdegree  +"</td>");
        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
        		'<span class="delete glyphicon glyphicon-trash"  style="cursor:pointer;margin-left:15px"></span>'
        		+"</td>");
        // $("#J_TbData").append($trTemp);
        $trTemp.appendTo("#select_problem");
	})
	
	$("#update_problem").click(function(){
		
		var json = {};
		json.ProblemId = pid;
		select_wp.push(json);
		
		
        var $trTemp = $("<tr ></tr>");
        //往行里面追加 td单元格
        $trTemp.append("<td style=" + "text-align:center"  + ">"+ pid +"</td>");
        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + pflag +"</td>");
        $trTemp.append("<td style=" + "text-align:center"  + ">" + pflag +"</td>");
        $trTemp.append("<td style=" + "text-align:center"  + ">" + pname  +"</td>");
        $trTemp.append("<td style=" + "text-align:center"  + ">" + pdegree  +"</td>");
        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
        		'<span class="choosed glyphicon glyphicon-trash"  style="cursor:pointer;margin-left:15px"></span>'
        		+"</td>");
        // $("#J_TbData").append($trTemp);
        $trTemp.appendTo("#selected_problem");
	})
	
	$("#UpdateWP").click(function(){
		var jsonText = JSON.stringify(select_wp);
		//console.log(jsonText);
		$.post(
				"UpdateWorkProblem.action",
				{
					problem_info:jsonText,
					work_id:wid
				}, 
				function(data) {
					data = data.replace(/^\s*/, "").replace(/\s*$/, "");
					if(data == "Success"){
						alert("更新成功！");
						window.location.replace("ManagerWorkSet.html");
					}
					else{
						alert("更新失败!");
						window.location.replace("ManagerWorkSet.html");
					}
				}
		);
	})
	
	$("#AddWP").click(function(){
		var jsonText = JSON.stringify(wp);
		$.post(
				"AddManyWorkProblem.action",
				{
					problem_info:jsonText,
					work_id:wid
				}, 
				function(data) {
					data = data.replace(/^\s*/, "").replace(/\s*$/, "");
					if(data == "Success"){
						alert("添加成功！");
						window.location.replace("ManagerWorkSet.html");
					}
					else{
						alert("添加失败!");
						window.location.replace("ManagerWorkSet.html");
					}
				}
		);
	})
});

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryAllCourse.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
		           for(var i=0 ;i<data.length;i++){ //几个人有几个checkbox
		        	   $("#allTime").append(
				                "<span>"+
				                    "<input  type='checkbox' class='time' name='course' value='"+data[i].courseId+"' title='"+data[i].courseName+"'>" 
				                 +"</span>");
		           }
			}
			);
	
	
	$.post(
			"QueryCourseByWorkId.action",
			{
				work_id:wid
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				for(var i = 0; i < data.length; i ++){
					$('input:checkbox[value='+data[i].courseId+']').attr('checked', true);
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
	            "QueryProblemByKnowledgeId.action",
	            {
	                page:row,
	                limit:15,
	                knowledge_id:select_know,
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	                        var WorkFlag;
	                        if(data[i].workFlag == 0) WorkFlag = "不公开";
	                        else WorkFlag = "公开";
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].problemId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemName  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemDegree  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemPeople  +"</td>");
	    			        $trTemp.append("<td class=layui-btn style=" + "text-align:center"  + ">" + 
	    			        		'<i class="layui-icon find">&#xe608;</i> 添加'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#KnowList");
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
	            "QueryProblemByKnowledgeId.action",
	            {
	                page:row,
	                limit:15,
	                knowledge_id:select_know,
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	                        var WorkFlag;
	                        if(data[i].workFlag == 0) WorkFlag = "不公开";
	                        else WorkFlag = "公开";
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].problemId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemName  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemDegree  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemPeople  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<span class="find glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:15px" data-toggle="modal" data-target="#myModal"></span>'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#KnowList");
	                    }
	            }
	        );
	}

}

function back(){
	window.location.replace("ManagerWorkSet.html");
}