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
				$("#update_create").val(datas.workCreatTime);
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
		   
		   
		   var currentRow=$(this).closest("tr"); 
		   var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		   
		   for(var i = 0;i < wp.length; i++){
			   if(wp[i].id == col1){
				   wp.splice(i,1);
			   }
		   }
		   
		   $(this).closest("tr").remove(); 
		   
		  });
	
	$("#add_problem").click(function(){
		
		var json = {};
		json.name = "ProblemId";
		json.id = pid;
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
	
	
	$("#AddWP").click(function(){
		var jsonText = JSON.stringify(wp);
		$.post(
				"QueryAllKnowledge.action",
				{
					problem_info:jsonText,
					work_id:widm
				}, 
				function(data) {
					
				}
		);
	})
});



function PrevPage(){

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

