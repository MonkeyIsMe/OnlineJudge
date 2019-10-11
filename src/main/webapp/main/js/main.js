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

$(function(){
	$.post(
			"QueryWorkByUserAccount.action",
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
				        $trTemp.append("<td style=" + "text-align:center;padding-top:10px;padding-bottom:5px"  + ">"+ data[i].workId +"</td>");
				        $trTemp.append("<td onclick=SeeProblem('"+data[i].workId+"') style=" + "text-align:center;padding-top:10px;padding-bottom:5px"  + ">"  +data[i].workName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;padding-top:10px;padding-bottom:5px"  + ">" +data[i].workOwner  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;padding-top:10px;padding-bottom:5px"  + ">" + data[i].workCreatTime+"</td>");
				        // $("#J_TbData").append($trTemp);
				        $trTemp.appendTo("#MyWorkList");
				    }
				}
		}
	);
	
})

function SeeProblem(wid){
	
	$.post(
			"QuerySingleWork.action",
			{
				work_id:wid
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				var flag = "考试";
				if(data.workFlag == '0'){
					flag = "作业";
				}
				var time = getNowFormatDate();
				if(data.workBeginTime > time){
					alert("该" + flag + data.workName +"还未开始，请等待开始！");
				}
				else if(data.workEndTime < time){
					alert("该" + flag + data.workName +"已经结束！");
				}
				else{
					var url = "WorkPage.html?WorkId=" + wid;
					window.location.href = url;
				}
			}
			);


}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
    return currentdate;
}