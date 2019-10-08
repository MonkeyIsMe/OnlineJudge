/**
 * Created by CallMeDad on 2019/9/2.
 */

var row = 1;  //页数
var count; //总记录数
var aid; //记录第几行的编号
var cnt = 0;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"SetUserInfo.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				if(data.user_role == '2'){
				    var url = "http://202.197.66.200:1188/OnlineJudge/Main.html";
				    window.location.replace(url);
				}
			}
			);
	
});

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountAnswer.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.AnswerCount;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});


$(function(){
    $.post(
        "QueryAnswerPageSize.action",
        {
            page:row,
            limit:25,
        },
        function(data) {
            var data = JSON.parse(data);
           // console.log("success");
            //console.log(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].answerId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].problemName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userAccount  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].answerTime  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;display: table-cell;"  + ">" +data[i].answerInfo  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a><span class="delete comment glyphicon glyphicon-th-large" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#myModal"></span></a>'
			        		+'<a><span class="delete clear glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#myModal"></span></a>'
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
	            "QueryAnswerPageSize.action",
	            {
	                page:row,
	                limit:25,
	            },
	            function(data) {
	                var data = JSON.parse(data);
	               // console.log("success");
	                //console.log(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].answerId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].problemName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userAccount  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].answerTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;display: table-cell;"  + ">" +data[i].answerInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a><span class="delete comment glyphicon glyphicon-th-large" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#myModal"></span></a>'
	    			        		+'<a><span class="delete clear glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#myModal"></span></a>'
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
	            "QueryAnswerPageSize.action",
	            {
	                page:row,
	                limit:25,
	            },
	            function(data) {
	                var data = JSON.parse(data);
	               // console.log("success");
	                console.log(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].answerId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].problemName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userAccount  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].answerTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;display: table-cell;"  + ">" +data[i].answerInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a><span class="delete comment glyphicon glyphicon-th-large" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#myModal"></span></a>'
	    			        		+'<a><span class="delete clear glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:30px" data-toggle="modal" data-target="#myModal"></span></a>'
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
	  $("#myTable").on('click','.comment',function(){
	    //获得当前行
	    var currentRow=$(this).closest("tr"); 
	    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
	    
	    aid = col1;
	    
	    var url = "ManagerCommentSet.html?AnswerId=" + aid;
	    window.location.replace(url);
	    
	  });
	  
	  $("#myTable").on('click','.clear',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    
		    aid = col1;
		    
		  });
	 
	  $("#clear").click(function(){
		  	$.post(
					"ClearComment.action",
					{
						answer_id:aid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("清空失败！");
						    var url = "ManagerAnswerComment.html";
						    window.location.replace(url);
						}
						else{
							alert("清空成功!");
						    var url = "ManagerAnswerComment.html";
						    window.location.replace(url);
						}
					}
					);
	  })
	  
	  
	  
});



function refresh(){
	window.location.replace("ManagerAnswerComment.html");
}