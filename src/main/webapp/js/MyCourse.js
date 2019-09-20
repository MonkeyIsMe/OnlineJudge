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
				        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].workId +"</td>");
				        $trTemp.append("<td onclick=SeeProblem('"+data[i].ProblemId+"') style=" + "text-align:center;color:white"  + ">"  +data[i].workName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].workOwner  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + data[i].workCreatTime+"</td>");
				        // $("#J_TbData").append($trTemp);
				        $trTemp.appendTo("#CourseList");
				    }
				}
		}
	);
	
});