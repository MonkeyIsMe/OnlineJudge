/**
 * Created by CallMeDad on 2019/8/4.
 */
var row = 1;

$(function(){
    $.post(
        "QueryKnowledgeByPageSize.action",
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
			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].knowledgeId +"</td>");
			        $trTemp.append("<td  style=" + "text-align:center;"  + ">"  +data[i].knowledgeName +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].knowledgeInfo  +"</td>");
			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
			        		'&nbsp;&nbsp;&nbsp;&nbsp;<a id="pic"><span onclick=UpdateDevice('+ data[i].knowledgeId +') class="glyphicon glyphicon-pencil" ></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
			        		+'s&nbsp;&nbsp;<a id="pic"><span onclick=UpdateDevice('+ data[i].knowledgeId +') class="glyphicon glyphicon-trash" ></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
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
		continue;
	}
	else{
		row--;
		$("#KnowList").html("");
	    $.post(
	            "QueryKnowledgeByPageSize.action",
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
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].knowledgeId +"</td>");
	    			        $trTemp.append("<td  style=" + "text-align:center;"  + ">"  +data[i].knowledgeName +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].knowledgeInfo  +"</td>");
	    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
	    			        		'&nbsp;&nbsp;&nbsp;&nbsp;<a id="pic"><span onclick=UpdateDevice('+ data[i].knowledgeId +') class="glyphicon glyphicon-pencil" ></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
	    			        		+'s&nbsp;&nbsp;<a id="pic"><span onclick=UpdateDevice('+ data[i].knowledgeId +') class="glyphicon glyphicon-trash" ></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
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
	row ++;
	$("#KnowList").html("");
    $.post(
            "QueryKnowledgeByPageSize.action",
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
    			        $trTemp.append("<td style=" + "text-align:center"  + ">"+ data[i].knowledgeId +"</td>");
    			        $trTemp.append("<td  style=" + "text-align:center;"  + ">"  +data[i].knowledgeName +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" +data[i].knowledgeInfo  +"</td>");
    			        $trTemp.append("<td style=" + "text-align:center"  + ">" + 
    			        		'&nbsp;&nbsp;&nbsp;&nbsp;<a id="pic"><span onclick=UpdateDevice('+ data[i].knowledgeId +') class="glyphicon glyphicon-pencil" ></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
    			        		+'s&nbsp;&nbsp;<a id="pic"><span onclick=UpdateDevice('+ data[i].knowledgeId +') class="glyphicon glyphicon-trash" ></span></a>&nbsp;&nbsp;&nbsp;&nbsp;'
    			        		+"</td>");
                        // $("#J_TbData").append($trTemp);
                        $trTemp.appendTo("#KnowList");
                    }
                }
            }
        );
}