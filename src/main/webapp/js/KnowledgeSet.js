/**
 * Created by CallMeDad on 2019/8/4.
 */
var row = 1;  //页数
var count; //总记录数
var kid; //记录第几行的编号
var kname; //记录第几行的名称
var kinfo; //记录第几行的备注信息

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountKnowledge.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.KnowledgeCount;
				count = Math.ceil(sum/15);
				var total = "共" + Math.ceil(sum/15) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});

$(function(){
    $.post(
        "QueryKnowledgeByPageSize.action",
        {
            page:row,
            limit:15
        },
        function(data) {
        	//console.log(data);
            var data = JSON.parse(data);
            //console.log(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].knowledgeId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].knowledgeName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].knowledgeInfo  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'    <a id="pic"><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#update_Modal"></span></a>    '
			        		+'  <a id="pic"><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>    '
			        		+"</td>");
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#KnowList");
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
	            "QueryKnowledgeByPageSize.action",
	            {
	                page:row,
	                limit:15
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].knowledgeId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].knowledgeName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].knowledgeInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'    <a id="pic"><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#update_Modal"></span></a>    '
	    			        		+'  <a id="pic"><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>    '
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
	            "QueryKnowledgeByPageSize.action",
	            {
	                page:row,
	                limit:15
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].knowledgeId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].knowledgeName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].knowledgeInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'    <a id="pic"><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#update_Modal"></span></a>    '
	    			        		+'  <a id="pic"><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>    '
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#KnowList");
	                    }
	            }
	        );
	}

}

$(document).ready(function(){
	  //用于读取所选表行单元格数据（值）的代码
	  $("#myTable").on('click','.delete',function(){
	    //获得当前行
	    var currentRow=$(this).closest("tr"); 
	    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
	    var col2=currentRow.find("td:eq(1)").text(); //获得当前行第一个TD值
	    var col3=currentRow.find("td:eq(2)").text(); //获得当前行第一个TD值
	    
	    kid = col1;
	    kname = col2;
	    kinfo = col3;
	    $("#update_name").val(kname);
	    $("#update_info").val(kinfo);

	  });
	  
		$("#del_know").click(function(){
			//console.log(kid);
			$.post(
					"DeleteKnowledge.action",
					{
						knowledge_id:kid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("删除失败！");
							window.location.replace("ManagerKnowledgeSet.html");
						}
						else{
							alert("删除成功!");
							window.location.replace("ManagerKnowledgeSet.html");
						}
					}
					);
		})
		
		
		$("#update_know").click(function(){
			
			var name = $("#update_name").val();
			var info = $("#update_info").val();
			
			$.post(
					"UpdateKnowledge.action",
					{
						knowledge_id:kid,
						knowledge_info:info,
						knowledge_name:name,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("修改失败！");
							window.location.replace("ManagerKnowledgeSet.html");
						}
						else{
							alert("修改成功!");
							window.location.replace("ManagerKnowledgeSet.html");
						}
					}
					);
		})
		
		$("#add_know").click(function(){
			
			var name = $("#add_name").val();
			var info = $("#add_info").val();
			
			$.post(
					"AddKnowledge.action",
					{
						knowledge_info:info,
						knowledge_name:name,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("添加失败！");
							window.location.replace("ManagerKnowledgeSet.html");
						}
						else{
							alert("添加成功!");
							window.location.replace("ManagerKnowledgeSet.html");
						}
					}
					);
		})
});

function refresh(){
	window.location.replace("ManagerKnowledgeSet.html");
}