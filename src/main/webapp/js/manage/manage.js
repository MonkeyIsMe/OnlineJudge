var user_role;

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"SetUserInfo.action",
			{
			},
			function(data){
				var data = JSON.parse(data);
				console.log(data);
				user_role = data.user_role;
				$("#UserName").append(data.user_name);
				if(data.user_role == '2'){
				    var url = "http://202.197.66.200:1188/OnlineJudge/Main.html";
				    window.location.replace(url);
				}
			}
			);
	
});

function ToManage(){
	if(user_role == '1'){
	    var url = "http://202.197.66.200:1188/OnlineJudge/manage.html";
	    window.location.replace(url);
	}
	else{
		alert("没有这个权限！");
	}
}