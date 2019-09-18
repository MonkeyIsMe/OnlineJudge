var url = decodeURI(window.location.href);
 
url = url.split("?")[1]; 
var a=url.split("&");       
var wid = a[0].split("=")[1]; 
var uid = a[1].split("=")[1]; 
var row = 1;  //页数

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountRecordByWorkUser.action",
			{
				work_id:wid,
				user_id:uid
			},
			function(data){
				var data = JSON.parse(data);
				console.log(data);
				var sum = data.WorkUserRecordCount;
				count = Math.ceil(sum/25);
				sub_count = Math.ceil(sum/25);
				var total = "共" + Math.ceil(sum/25) + "页";
				$("#TotalPage").append(total);
				$("#NowPage").append("，当前第" + row + "页");

			}
			);
	
});

$(function(){
	//学生数据列表
    $.post(
        "QueryWorkUserRecordByWorkUserIdPageSize.action",
        {
        	user_id:uid,
        	work_id:wid,
            page:row,
            limit:25
        },
        function(data) {
            var data = JSON.parse(data);
            console.log(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].userId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].userName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userAccount  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].userInfo  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].studentClassroom  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a id="pic"><span class="delete glyphicon glyphicon-folder-open" style="cursor:pointer;margin-left:50%" data-toggle="modal" data-target="#update_Modal"></span></a>'
			        		+"</td>");
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#UserList");
                }
        }
    );
    

});