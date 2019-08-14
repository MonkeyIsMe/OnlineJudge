var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?WorkId=");
var wid = argsIndex[1];

var row = 1;
var select_know = "none";

$(document).ready(function(){

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
    			        $trTemp.append("<td class=layui-btn style=" + "text-align:center"  + ">" + 
    			        		'<i class="layui-icon">&#xe608;</i> 添加'
    			        		+"</td>");
                        // $("#J_TbData").append($trTemp);
                        $trTemp.appendTo("#KnowList");
                    }
            }
        );
	
	$("#sure").click(function(){
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
	    			        $trTemp.append("<td class=layui-btn style=" + "text-align:center"  + ">" + 
	    			        		'<i class="layui-icon">&#xe608;</i> 添加'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#KnowList");
	                    }
	            }
	        );
	})
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
	    			        		'<i class="layui-icon">&#xe608;</i> 添加'
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
	    			        $trTemp.append("<td class=layui-btn style=" + "text-align:center"  + ">" + 
	    			        		'<i class="layui-icon">&#xe608;</i> 添加'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#KnowList");
	                    }
	            }
	        );
	}

}