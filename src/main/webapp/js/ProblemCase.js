/**
 * Created by CallMeDad on 2019/8/8.
 */

var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?ProblemId=");
var pid = argsIndex[1];



var cid; //记录第几行的编号


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
                    var CaseFlag;
                    if(data[i].caseFlag == 0) CaseFlag = "题面样例";
                    else CaseFlag = "测试样例";
			        $trTemp.append("<td style=" + "text-align:center;width:8%;"  + ">"+ data[i].caseId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;width:8%;"  + ">"  + CaseFlag +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;width:37%;word-break:break-all;"  + ">" +data[i].caseInput  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;width:37%;word-break:break-all;"  + ">" +data[i].caseOutput  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;width:10%;"  + ">" + 
			        		'<a><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:35px" data-toggle="modal" data-target="#update_Modal"></span></a>'
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
	    
	    cid = col1;
	    
	    
	  });
	  
	  
		$("#del_case").click(function(){
			//console.log(cid);
			$.post(
					"DeleteCase.action",
					{
						case_id:cid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
					    pid = col1;
					    var url = "ProblemCase.html?ProblemId=" + pid;
						if(data == "Fail"){
							alert("删除失败！");
							window.location.replace(url);
						}
						else{
							alert("删除成功!");
							window.location.replace(url);
						}
					}
					);
		})
		

});


function refresh(){
	window.location.replace("ManagerProblemSet.html");
}


