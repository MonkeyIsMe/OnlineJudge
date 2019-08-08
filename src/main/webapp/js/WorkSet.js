/**
 * Created by CallMeDad on 2019/8/8.
 */
var row = 1;  //页数
var count; //总记录数
var wid; //记录第几行的编号
var wname; //记录第几行的名称
var winfo; //记录第几行的备注信息
var wonwer; //记录第几行的创建者
var ctime; //记录第几行的开课时间

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountWork.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var sum = data.WorkCount;
				count = Math.ceil(sum/15);
				var total = "共" + Math.ceil(sum/15) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");
			}
			);
	
});

$(function(){
    $.post(
        "QueryAllWork.action",
        {
            page:row,
            limit:15
        },
        function(data) {
            var data = JSON.parse(data);
            console.log(data);
            for(var i = 0 ; i < data.length ; i ++){
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
                    var WorkFlag;
                    if(data[i].workFlag == 0) WorkFlag = "作业";
                    else WorkFlag = "考试";
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].workId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  + WorkFlag +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workName  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workCreatTime  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workInfo  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workOwner  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'&nbsp;&nbsp;&nbsp;&nbsp;<a id="pic"><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;" data-toggle="modal" data-target="#update_Modal"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
			        		+'&nbsp;&nbsp;<a id="pic"><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;" data-toggle="modal" data-target="#myModal"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
			        		+"</td>");
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#KnowList");
                }
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
	            "QueryAllWork.action",
	            {
	                page:row,
	                limit:15
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                console.log(data);
	                for(var i = 0 ; i < data.length ; i ++){
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].courseId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].courseName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseTeacher  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'&nbsp;&nbsp;&nbsp;&nbsp;<a id="pic"><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;" data-toggle="modal" data-target="#update_Modal"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
	    			        		+'&nbsp;&nbsp;<a id="pic"><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;" data-toggle="modal" data-target="#myModal"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#KnowList");
	                    }
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
	            "QueryAllWork.action",
	            {
	                page:row,
	                limit:15
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                console.log(data);
	                for(var i = 0 ; i < data.length ; i ++){
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].courseId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].courseName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].courseTeacher  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'&nbsp;&nbsp;&nbsp;&nbsp;<a id="pic"><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;" data-toggle="modal" data-target="#update_Modal"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
	    			        		+'&nbsp;&nbsp;<a id="pic"><span  class="delete glyphicon glyphicon-trash" style="cursor:pointer;" data-toggle="modal" data-target="#myModal"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
	    			        		+"</td>");
	                        // $("#J_TbData").append($trTemp);
	                        $trTemp.appendTo("#KnowList");
	                    }
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
	    var col4=currentRow.find("td:eq(3)").text(); //获得当前行第一个TD值
	    var col5=currentRow.find("td:eq(4)").text(); //获得当前行第一个TD值
	    
	    cid = col1;
	    cname = col2;
	    ctime = col3;
	    cinfo = col4;
	    cteacher = col5;
	    
	    $("#update_name").val(cname);
	    $("#update_info").val(cinfo);
	    $("#update_teacher").val(cteacher);
	    $("#update_time").val(ctime);	    
	    //console.log(cname + " " + cinfo);
	    //$("#update_name").append(cname);
	    //$("#update_info").append(cinfo);
	  });
	  
		$("#del_know").click(function(){
			//console.log(cid);
			$.post(
					"DeleteCourse.action",
					{
						course_id:cid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("删除失败！");
							window.location.replace("ManagerCourseSet.html");
						}
						else{
							alert("删除成功!");
							window.location.replace("ManagerCourseSet.html");
						}
					}
					);
		})
		
		

});
