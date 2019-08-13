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
        "QueryProblemListManager.action",
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
                    var WorkFlag;
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
                    $trTemp.appendTo("#KnowList");
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
	  
	  
	  $("#myTable").on('click','.cases',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    
		    pid = col1;
		    alert(col1);
		    
		  });
	  
		$("#del_problem").click(function(){
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
		
		$("#AddProblem").click(function () {
			//alert(123);
			var case_info = JSON.stringify(stock);
            var problem_name = $("#add_problem_name").val();
            var problem_hint = $("#add_problem_hint").val();
            var problem_memory = $("#add_problem_memory").val();
            var problem_time = $("#add_problem_time").val();
            var problem_input = $("#add_problem_input").val();
            var problem_output = $("#add_problem_output").val();
            var case_input = $("#add_case_input").val();
            var case_output = $("#add_case_output").val();
            var problem_info = $("#add_problem_info").val();
            var problem_flag = $('input:radio:checked').val();
            //alert(problem_flag);
            $.post(
                "AddProblem.action",
                {
                    problem_name:problem_name,
                    problem_hint:problem_hint,
                    problem_memory:problem_memory,
                    problem_time:problem_time,
                    problem_input:problem_input,
                    problem_output:problem_output,
                    case_input:case_input,
                    case_output:case_output,
                    problem_info:problem_info,
                    problem_flag:problem_flag,
                    case_info:case_info
                },
                function(data){
                	var data = JSON.parse(data);
                    console.log(data.ProblemId)
                }
            );
        })

});


function refresh(){
	window.location.replace("ManagerProblemSet.html");
}


