/**
 * Created by CallMeDad on 2019/8/4.
 */
var row = 1;

$(function(){
    $.post(
        "QueryKnowledgeByPageSize.action",
        {
            page:row,
            limit:3
        },
        function(data) {
            var data = JSON.parse(data);
            console.log(data);
            for(var i = 0 ; i < data.length ; i ++){
                for( var i = 0; i < data.length; i++ ) {
                    //动态创建一个tr行标签,并且转换成jQuery对象
                    var $trTemp = $("<tr ></tr>");
                    //往行里面追加 td单元格
                    // $("#J_TbData").append($trTemp);
                    $trTemp.appendTo("#KnowList");
                }
            }
        }
    );

});