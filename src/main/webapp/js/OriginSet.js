var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?pid=");
var pid = argsIndex[1];

var oid;

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
		    
			$.post(
					"QueryOriginByProblemId.action",
					{
						origin_id:col1,
					},
					function(data){
						var data = JSON.parse(data);
						console.log(data);
					}
					);
		    	    
		  });
	  
		$("#del").click(function(){
			//console.log(cid);
			$.post(
					"DeleteOrigin.action",
					{
						origin_id:cid,
					},
					function(data){
						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
						if(data == "Fail"){
							alert("删除失败！");
							window.location.replace("OriginCode.html");
						}
						else{
							alert("删除成功!");
							window.location.replace("OriginCode.html");
						}
					}
					);
		})
		

		
		
});

function BackPrev(){
	window.location.replace("ManagerCode.html");
}

function refresh(){
	window.location.replace("OriginCode.html");
}