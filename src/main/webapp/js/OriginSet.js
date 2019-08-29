var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?pid=");
var pid = argsIndex[1];

var panme;

var oid;

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

//添加的编译器
var add_editor = ace.edit("add_editor");
add_editor.setTheme("ace/theme/dreamweaver");
add_editor.session.setMode("ace/mode/java");
add_editor.setShowPrintMargin(true);
add_editor.session.getLength();
add_editor.session.setUseWrapMode(true);
add_editor.setOptions({
    enableBasicAutocompletion: true,
    enableSnippets: true,
    enableLiveAutocompletion: true,//只能补全
});

$(function(){
	$.ajaxSettings.async = false;
	
	
	$.post(
			"QuerySingleProblem.action",
			{
				problem_id:pid,
			},
			function(data){
				var data = JSON.parse(data);
				//console.log(data);
				pname = data.problemName;
			}
			);
	
    $.post(
        "QueryOriginByProblemId.action",
        {
        	problem_id:pid
        },
        function(data) {
            var data = JSON.parse(data);
            //console.log(data);
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].originId +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].orginType  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemId  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].problemName  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'<a><span class="updatecode glyphicon glyphicon-pencil" style="cursor:pointer;margin-left:2%" data-toggle="modal" data-target="#myModal"></span></a>'
			        		+'<a><span class="deletecode glyphicon glyphicon-minus" style="cursor:pointer;margin-left:10%" data-toggle="modal" data-target="#del_Modal"></span></a>'
			        		+"</td>");
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#KnowList");
                }
        }
    );

});

$(document).ready(function(){
	  //用于读取所选表行单元格数据（值）的代码
	  $("#myTable").on('click','.deletecode',function(){
	    //获得当前行
	    var currentRow=$(this).closest("tr"); 
	    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
	    
	    oid = col1;
	    	    
	  });
	  
	  
	  $("#myTable").on('click','.updatecode',function(){
		    //获得当前行
		    var currentRow=$(this).closest("tr"); 
		    var col1=currentRow.find("td:eq(0)").text(); //获得当前行第一个TD值
		    var col2=currentRow.find("td:eq(1)").text();
		    
		    oid = col1;
		    
		    if(col2 == "Java"){
		    	editor.session.setMode("ace/mode/java");
		    }
		    else if(col2 =="C" || col2 == "C++"){
		    	editor.session.setMode("ace/mode/c_cpp");
		    }
		    else{
		    	editor.session.setMode("ace/mode/python");
		    }
		    
			$.post(
					"QuerySingleOrigin.action",
					{
						origin_id:oid,
					},
					function(data){
						var data = JSON.parse(data);
						//console.log(data);
						editor.setValue(data.problemCode);
					}
					);
		    	    
		  });
	  
		$("#del").click(function(){
			//console.log(cid);
			$.post(
					"DeleteOrigin.action",
					{
						origin_id:oid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("删除失败！");
						    var url = "OriginCode.html?pid=" + pid;
						    window.location.replace(url);
						}
						else{
							alert("删除成功!");
						    var url = "OriginCode.html?pid=" + pid;
						    window.location.replace(url);
						}
					}
					);
		})
		
		$("#update").click(function(){
			//console.log(cid);
			
			var codeContent = editor.getValue();
			var options = $("#select_lang option:selected").val();
			
			//console.log(codeContent);
			//console.log(options);
			$.post(
					"UpdateOrigin.action",
					{
						origin_id:oid,
						origin_code:codeContent,
						origin_type:options,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("更新失败！");
						    var url = "OriginCode.html?pid=" + pid;
						    window.location.replace(url);
						}
						else{
							alert("更新成功!");
						    var url = "OriginCode.html?pid=" + pid;
						    window.location.replace(url);
						}
					}
					);

		})

		
		$("#add").click(function(){
			//console.log(cid);
			
			var codeContent = add_editor.getValue();
			var options = $("#add_lang option:selected").val();
			
			//console.log(codeContent);
			//console.log(options);
			//alert(pname);

			$.post(
					"AddOrigin.action",
					{
						problem_name:pname,
						problem_id:pid,
						origin_code:codeContent,
						origin_type:options,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("添加失败！");
						    var url = "OriginCode.html?pid=" + pid;
						    window.location.replace(url);
						}
						else{
							alert("添加成功!");
						    var url = "OriginCode.html?pid=" + pid;
						    window.location.replace(url);
						}
					}
					);
		})
		
		
});

function BackPrev(){
	window.location.replace("ManagerCode.html");
}

function refresh(){
    var url = "OriginCode.html?pid=" + pid;
    window.location.replace(url);
}