var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?UserId=");
var uid = argsIndex[1];

var row = 1;  //页数
var count; //总记录数
var aid; //记录第几行的编号
var cnt = 0;


$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountCodeByAccount.action",
			{
				user_id:uid,
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.CodeCount;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});


$(function(){
    $.post(
        "QueryCodeByAccount.action",
        {
            page:row,
            limit:25,
            user_id:uid,
        },
        function(data) {
            var data = JSON.parse(data);
            //console.log(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].codeId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].codeName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeInfo  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userAccount  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:80px"></span></a>'
			        		+'<a><span class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>'
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
	            "QueryAnswerByProblemId.action",
	            {
	                page:row,
	                limit:25,
	                problem_id:pid
	            },
	            function(data) {
	                var data = JSON.parse(data);
	               // console.log("success");
	                //console.log(data);
	                for( var i = 0; i < data.length; i++ ) {
	                    //动态创建一个tr行标签,并且转换成jQuery对象
	                    var $trTemp = $("<tr ></tr>");
	                    //往行里面追加 td单元格
				        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].codeId +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].codeName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeInfo  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userAccount  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
				        		'<a><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:80px"></span></a>'
				        		+'<a><span class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>'
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
	            "QueryAnswerByProblemId.action",
	            {
	                page:row,
	                limit:25,
	                problem_id:pid
	            },
	            function(data) {
	                var data = JSON.parse(data);
	               // console.log("success");
	                //console.log(data);
	                for( var i = 0; i < data.length; i++ ) {
	                    //动态创建一个tr行标签,并且转换成jQuery对象
	                    var $trTemp = $("<tr ></tr>");
	                    //往行里面追加 td单元格
				        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].codeId +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].codeName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeInfo  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userAccount  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
				        		'<a><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:80px"></span></a>'
				        		+'<a><span class="delete glyphicon glyphicon-trash" style="cursor:pointer;margin-left:20px" data-toggle="modal" data-target="#myModal"></span></a>'
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
	    
	    cid = col1;
	  });
	 
	  $("#delete_code").click(function(){
		  	$.post(
					"DeleteCode.action",
					{
						code_id:cid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
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
	window.location.replace(url);
}

function backprev(){
	window.location.replace("ManagerUserCodeSet.html");
}