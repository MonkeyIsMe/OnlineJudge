$(function(){
	$.post(
			"QuerySingleCourseUser.action",
			{
			}, 
			function(data) {
				var data = JSON.parse(data);
				//console.log(data);
				for(var i = 0 ; i < data.length ; i ++){
				    for( var i = 0; i < data.length; i++ ) {
				        //动态创建一个tr行标签,并且转换成jQuery对象
				        var $trTemp = $("<tr ></tr>");
				        //往行里面追加 td单元格
				        $trTemp.append("<td style=" + "text-align:center;padding-top:10px;padding-bottom:5px"  + ">"+ data[i].courseId +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;padding-top:10px;padding-bottom:5px"  + ">"  +data[i].courseName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;padding-top:10px;padding-bottom:5px"  + ">" +data[i].courseInfo  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;padding-top:10px;padding-bottom:5px"  + ">" + data[i].courseTeacher+"</td>");
				        $trTemp.append("<td style=" + "text-align:center;padding-top:10px;padding-bottom:5px"  + ">" + data[i].courseTime+"</td>");
				        // $("#J_TbData").append($trTemp);
				        $trTemp.appendTo("#CourseList");
				    }
				}
		}
	);
	
});