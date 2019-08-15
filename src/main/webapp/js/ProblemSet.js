/**
 * Created by CallMeDad on 2019/8/8.
 */
var row = 1;  //页数
var count; //总记录数
var pid; //记录第几行的编号
var stock = [];
var arr = [];
var cnt = 0;


$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountProblem.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.ProblemCount;
				count = Math.ceil(sum/15);
				var total = "共" + Math.ceil(sum/15) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"QueryAllKnowledge.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
		           for(var i=0 ;i<data.length;i++){ //几个人有几个checkbox
		        	   $("#allTime").append(
				                "<span>"+
				                    "<input  type='checkbox' class='time' name='know' value='"+data[i].knowledgeId+"' title='"+data[i].knowledgeName+"'>" 
				                 +"</span>");
		           }
			}
			);
	
});

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


function PrevPage(){
	if(row == 1){
		alert("没有前一页了");
	}
	else{
		row--;
		$("#KnowList").html("");
	    $.post(
	            "QueryProblemListManager.action",
	            {
	                page:row,
	                limit:15
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                console.log(data);
	                for(var i = 0 ; i < data.length ; i ++){
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
	}

}

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
		    var url = "ProblemCase.html?ProblemId=" + pid;
		    window.location.replace(url);
		    
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
            
	  		var json =[];
			$('input[name="know"]:checked').each(function(){
				var obj = {};
				obj.knowledgeId = $(this).val();
				json.push(obj);//向数组中添加元素  
				});
			var jsonText = JSON.stringify(json);
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
                        case_info:case_info,
                        knowledge_info:jsonText,
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


$(function () {
    $('#pop-up').click(function () {
        $('#runText').show();
        $('.shadow').show();
        $('body').css('overflow','hidden');
        $('#layer-close').click(function () {
            $('.shadow').hide();
            $('#runText').hide();
            $('body').css('overflow','auto');
        })
        $('#shadow').click(function () {
            $('#runText').hide();
            $('#shadow').hide();
            $('body').css('overflow','auto');
        })
    })
    $("#exampleFormControlSelect1").click(function () {
        var lan = $("#exampleFormControlSelect1").val();
        console.log(lan);
        if (lan == "---select---") {
            return;
        }
    })
})


$(function () {
        $('#addone').click(function () {
            getAce();
        })

        $("#sure").click(function () {
            var testArr = [];
            for(var i=0;i<arr.length;i++)
            {
                //console.log("length = " + arr.length)
                testArr.push(arr[i].getValue());
                cnt = cnt + 1;
                if(cnt == 2)
                {
                	console.log(i+" " + testArr[i-1] + " " + testArr[i] + " " + count);
                    var obj = {stdin:testArr[i-1],stdout:testArr[i]}
                    //console.log(obj);
                    stock.push(obj);
                    cnt=0;
                }
            }
            arr.length = 0;
            for (var i = 0; i < stock.length; i++) {
                for (var j =i+1; j <stock.length; ) {
                    if (stock[i].stdin == stock[j].stdin && stock[i].stdout == stock[j].stdout) {//判断条件可以按照个人需求改
                        stock.splice(j, 1);
                    }
                    else j++;
                }
            }
        })
    })


function getAce() {
        var editorx = null;
        // var arr = [];
        $("#addText").append("<li><div></div> <div></div> <button type=\"button\" class=\"btn btn-primary delete\">Delete</button></li>");
        $('#addText>li>div').each(function (index) {
            index = index + 1
            $(this).attr({
                class: 'editor ace_editor ace_tm',
                id: 'editor' + index,
            });
            var str = 'editor' + index;
            editorx = ace.edit(str);
            editorx.setTheme("ace/theme/chrome");
            editorx.setShowPrintMargin(true);
            editorx.session.getLength();
            editorx.session.setUseWrapMode(true);
            arr.push(editorx);

        })
        $('.delete').click(function () {
            $(this).parent().remove();
        })
    }