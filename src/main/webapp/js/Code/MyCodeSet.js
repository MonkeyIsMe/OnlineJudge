var row = 1;
var cid,del;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"CountCodeByUserAccount.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
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
			"QueryCodeByUserAccount.action",
			{
				page:row,
				limit:30
			}, 
			function(data) {
				var data = JSON.parse(data);
				//console.log(data);
				for(var i = 0 ; i < data.length ; i ++){
				    for( var i = 0; i < data.length; i++ ) {
				        //动态创建一个tr行标签,并且转换成jQuery对象
				        var $trTemp = $("<tr ></tr>");
				        //往行里面追加 td单元格
				        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">"+ data[i].codeId +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">"  +data[i].codeName +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" +data[i].codeType  +"</td>");
				        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" + data[i].codeTime+"</td>");
				        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
				        		'<span class="code glyphicon glyphicon-search" style="cursor:pointer;" data-toggle="modal" data-target="#myModal"></span>'
				        	+   '<span class="del glyphicon glyphicon-trash" style="cursor:pointer;margin-left:10%" data-toggle="modal" data-target="#clearModal"></span>'
				        		+"</td>");
				        $trTemp.appendTo("#WorkList");
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
				"QueryCodeByUserAccount.action",
				{
					page:row,
					limit:30
				}, 
				function(data) {
					var data = JSON.parse(data);
					//console.log(data);
					for(var i = 0 ; i < data.length ; i ++){
					    for( var i = 0; i < data.length; i++ ) {
					        //动态创建一个tr行标签,并且转换成jQuery对象
					        var $trTemp = $("<tr ></tr>");
					        //往行里面追加 td单元格
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">"+ data[i].codeId +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">"  +data[i].codeName +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" +data[i].codeType  +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" + data[i].codeTime+"</td>");
					        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
					        		'<span class="code glyphicon glyphicon-search" style="cursor:pointer;" data-toggle="modal" data-target="#myModal"></span>'
					        	+   '<span class="del glyphicon glyphicon-trash" style="cursor:pointer;margin-left:10%" data-toggle="modal" data-target="#clearModal"></span>'
					        		+"</td>");
					        $trTemp.appendTo("#WorkList");
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
				"QueryCodeByUserAccount.action",
				{
					page:row,
					limit:30
				}, 
				function(data) {
					var data = JSON.parse(data);
					//console.log(data);
					for(var i = 0 ; i < data.length ; i ++){
					    for( var i = 0; i < data.length; i++ ) {
					        //动态创建一个tr行标签,并且转换成jQuery对象
					        var $trTemp = $("<tr ></tr>");
					        //往行里面追加 td单元格
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">"+ data[i].codeId +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">"  +data[i].codeName +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" +data[i].codeType  +"</td>");
					        $trTemp.append("<td style=" + "text-align:center;font-size:16px"  + ">" + data[i].codeTime+"</td>");
					        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
					        		'<span class="code glyphicon glyphicon-search" style="cursor:pointer;" data-toggle="modal" data-target="#myModal"></span>'
					        	+   '<span class="del glyphicon glyphicon-trash" style="cursor:pointer;margin-left:10%" data-toggle="modal" data-target="#clearModal"></span>'
					        		+"</td>");
					        $trTemp.appendTo("#WorkList");
					    }
					}
			}
		);
	}

}

//更新的编译器
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


$(document).ready(function(){
	  //用于读取所选表行单元格数据（值）的代码
	  $("#myTable").on('click','.code',function(){
	    //获得当前行
	    var currentRow=$(this).closest("tr"); 
	    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
	    
	    cid = col1;
		$.post(
				"QuerySingleCode.action",
				{
					code_id:cid,
				},
				function(data){
					var data = JSON.parse(data);
					editor.setValue(data.CodeInfo);
					if(data.CodeType == "class"){
						$("#add_lang").find("option[value='Java']").attr("selected",true);
						editor.session.setMode("ace/mode/java");
					}
					else if(data.CodeType == "cpp"){
						$("#add_lang").find("option[value='C++']").attr("selected",true);
						editor.session.setMode("ace/mode/c_cpp");
					}
					else{
						$("#add_lang").find("option[value='Python']").attr("selected",true);
						 editor.session.setMode("ace/mode/python");
					}
				}
				);
	    
	  });

	  $("#myTable").on('click','.del',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    
		    del = col1;

		    
		  });
	  
	  $("#clear_code").click(function(){
			$.post(
					"DeleteCode.action",
					{
						code_id:del,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("删除失败！");
						    var url = "MyCodeSet.html";
						    window.location.replace(url);
						}
						else{
							alert("删除成功!");
							var url = "MyCodeSet.html";
						    window.location.replace(url);
						}
					}
					);
	  })
		
});
