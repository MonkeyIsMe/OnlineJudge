var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?pid=");
var pid = argsIndex[1];


$(function(){
    $.post(
        "QueryOriginByProblemId.action",
        {
        	problem_id:pid
        },
        function(data) {
            var data = JSON.parse(data);
            console.log(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].originId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].orginType  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemId  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemName  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a><span class="delete glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:40%" data-toggle="modal" data-target="#update_Modal"></span></a>'
			        		+'<a><span class="delete glyphicon glyphicon-minus" style="cursor:pointer;margin-left:10%" data-toggle="modal" data-target="#update_Modal"></span></a>'
			        		+"</td>");
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#KnowList");
                }
        }
    );

});


function refresh(){
	window.location.replace("OriginCode.html");
}