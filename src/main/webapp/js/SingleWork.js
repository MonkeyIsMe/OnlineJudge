var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?WorkId=");
var wid = argsIndex[1];

$(function(){
	$.post(
			"QuerySingleWork.action",
			{
				work_id:wid,
			}, 
			function(data) {
				var datas = JSON.parse(data);
				console.log(datas);
				$("#update_name").val(datas.workName);
				$("#update_owner").val(datas.workOwner);
				$("#update_begin").val(datas.workBeginTime);
				$("#update_end").val(datas.workEndTime);
				$("#update_create").val(datas.workCreatTime);
				$("#update_info").append(datas.workInfo);
				if(datas.workFlag == 1){
					$("input[name=work][value=exam]").attr("checked",true);
				}
				else {
					$("input[name=work][value=homework]").attr("checked",true);
				}
			}
	);
	
});

console.log(wid)