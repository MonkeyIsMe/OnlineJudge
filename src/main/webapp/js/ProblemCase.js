/**
 * Created by CallMeDad on 2019/8/8.
 */

var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?ProblemId=");
var pid = argsIndex[1];


var row = 1;  //页数
var count; //总记录数
var pid; //记录第几行的编号
var stock = [];
var arr = [];
var count = 0;




$(function(){
    $.post(
        "QueryAllCase.action",
        {
        	problem_id:pid,
        },
        function(data) {
            var data = JSON.parse(data);
            console.log(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
/*                    var WorkFlag;
                    if(data[i].workFlag == 0) WorkFlag = "不公开";
                    else WorkFlag = "公开";
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].ProblemId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].ProblemName  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].degree  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].know  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].people  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:50px" data-toggle="modal" data-target="#update_Modal"></span></a>'
			        		+'<a><span  class="delete cases glyphicon glyphicon-tasks" style="cursor:pointer;margin-left:20px" ></span></a>'
			        		+'<a><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>'
			        		+"</td>");
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#KnowList");*/
                }
        }
    );

});



$(document).ready(function(){
	  //用于读取所选表行单元格数据（值）的代码
	  $("#myTable").on('click','.delete',function(){
	    //获得当前行
	    var currentRow=$(this).closest("tr"); 
	    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
	    
	    pid = col1;
	    
	    
	  });
	  
	  
		$("#del_case").click(function(){
			//console.log(cid);
			$.post(
					"DeleteProblem.action",
					{
						problem_id:pid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("删除失败！");
							window.location.replace("ManagerProblemSet.html");
						}
						else{
							alert("删除成功!");
							window.location.replace("ManagerProblemSet.html");
						}
					}
					);
		})
		

});


function refresh(){
	window.location.replace("ManagerProblemSet.html");
}


