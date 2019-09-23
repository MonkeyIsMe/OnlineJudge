/**
 * Created by CallMeDad on 2019/9/22.
 */

var row = 1;  //页数
var count; //总记录数
var nid; //记录第几行的编号
var cnt = 0;


$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountNotice.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.NoticeCount;
				count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});


$(function(){
    $.post(
        "QueryNoticeByPageSize.action",
        {
            page:row,
            limit:25,
        },
        function(data) {
            var data = JSON.parse(data);
            console.log(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].noticeId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].noticeName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].noticePeople  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].noticeTime  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].noticeInfo  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:50%" data-toggle="modal" data-target="#myModal"></span></a>'
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
	            "QueryNoticeByPageSize.action",
	            {
	                page:row,
	                limit:25,
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].noticeId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].noticeName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].noticePeople  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].noticeTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].noticeInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:50%" data-toggle="modal" data-target="#myModal"></span></a>'
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
	            "QueryNoticeByPageSize.action",
	            {
	                page:row,
	                limit:25,
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].noticeId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + data[i].noticeName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].noticePeople  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].noticeTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].noticeInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:50%" data-toggle="modal" data-target="#myModal"></span></a>'
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
	    
	    nid = col1;
	  });
	 
	  $("#del_notice").click(function(){
		  	$.post(
					"DeleteNotice.action",
					{
						notice_id:nid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("删除失败！");
						    var url = "ManagerNoticeSet.html";
						    window.location.replace(url);
						}
						else{
							alert("删除成功!");
						    var url = "ManagerNoticeSet.html";
						    window.location.replace(url);
						}
					}
					);
	  })
	  
	  	  $("#add_notice").click(function(){
	  		  
	  		  var info = $("#add_info").val();
	  		  var name = $("#answer_name").val();
	  		  
	  		  if(info == null || info == "" || name == null || name == ""){
	  			  alert("所有输入均为非空！");
	  		  }
	  		  else{
			  		$.post(
							"AddNotice.action",
							{
								notice_info:info,
								notice_name:name,
							},
							function(data){
								data = data.replace(/^\s*/, "").replace(/\s*$/, "");
								if(data == "Fail"){
									alert("添加失败！");
								    var url = "ManagerNoticeSet.html";
								    window.location.replace(url);
								}
								else{
									alert("添加成功!");
								    var url = "ManagerNoticeSet.html";
								    window.location.replace(url);
								}
							}
							);
	  		  }
	  		  

	  })
	  
	  
});



function refresh(){
	window.location.replace("ManagerNoticeSet.html");
}