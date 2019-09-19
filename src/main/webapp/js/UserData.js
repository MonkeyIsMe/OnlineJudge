var url = decodeURI(window.location.href);
 
URL = url.split("?")[1]; 
var a = URL.split("&");       
var wid = a[0].split("=")[1]; 
var uid = a[1].split("=")[1]; 
var row = 1;  //页数
var id;

ace.require("ace/ext/language_tools");
var editor = ace.edit("editor");
editor.setTheme("ace/theme/dreamweaver");
editor.session.setMode("ace/mode/java");
editor.setShowPrintMargin(true);
editor.session.getLength();
editor.session.setUseWrapMode(true);
editor.setOptions({
    enableBasicAutocompletion: true,
    enableSnippets: true,
    enableLiveAutocompletion: true,//只能补全
});

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
				//console.log(data);
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
            //console.log(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].workUserRecordId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].userAccount +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemId  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeLength  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeType  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].codeMemory +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeTime  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionResult  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionTime  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a id="pic"><span class="delete glyphicon glyphicon-folder-open" style="cursor:pointer;margin-left:50%" data-toggle="modal" data-target="#myModal"></span></a>'
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
	            "QueryWorkUserRecordByWorkUserIdPageSize.action",
	            {
	            	user_id:uid,
	            	work_id:wid,
	                page:row,
	                limit:25
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].workUserRecordId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].userAccount +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemId  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeLength  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeType  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].codeMemory +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionResult  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a id="pic"><span class="delete glyphicon glyphicon-folder-open" style="cursor:pointer;margin-left:50%" data-toggle="modal" data-target="#myModal"></span></a>'
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
	            "QueryWorkUserRecordByWorkUserIdPageSize.action",
	            {
	            	user_id:uid,
	            	work_id:wid,
	                page:row,
	                limit:25
	            },
	            function(data) {
	                var data = JSON.parse(data);
	                //console.log(data);
	                    for( var i = 0; i < data.length; i++ ) {
	                        //动态创建一个tr行标签,并且转换成jQuery对象
	                        var $trTemp = $("<tr ></tr>");
	                        //往行里面追加 td单元格
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].workUserRecordId +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].userAccount +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemId  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeLength  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeType  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center;"  + ">"  +data[i].codeMemory +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].codeTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionResult  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].submissionTime  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'<a id="pic"><span class="delete glyphicon glyphicon-folder-open" style="cursor:pointer;margin-left:50%" data-toggle="modal" data-target="#myModal"></span></a>'
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
	    
	    id = col1;
	    
	    
		$.ajaxSettings.async = false;
		$.post(
				"QuerySingleRecord.action",
				{
					record_id:id,
				},
				function(data){
					var data = JSON.parse(data);
					editor.setValue(data.sumissionCode);
				}
				);
	    
	  });
	  

});

function refresh(){
	window.location.href = url;
}

function backprev(){
	var back = "WorkUserData.html?WorkId="+wid;
	window.location.href = back;
}