var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?sid=");
var sid = argsIndex[1];


$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"GetSubmissionResult.action",
			{
				submission_id:sid
			},
			function(data){
				var data = JSON.parse(data);
				console.log(data);
				if(data.result != "SE"){
					var case_result = data.case_result
					
					var tresult;
					
					if(data.result == 'AC'){
						tresult = "Accepted";
					}
					else if(data.result == 'WA'){
						tresult = "Wrong Answer";
					}
					else if(data.result == 'CE'){
						tresult = "Compile Error";
					}
					else if(data.result == 'RTE'){
						tresult = "Runtime Error";
					}
					else if(data.result == 'TLE'){
						tresult = "Time Limit Exceeded";
					}
					else if(data.result == 'SE'){
						tresult = "Server Error";
					}
					
					var result = "最后总结果为：" + data.result + ", " + "提交代码所耗时为：" + data.result_time + ", " + "提交代码所耗内存为：" + data.result_memory;
					$("#total_result").append(result);
					for(var i = 0; i < case_result.length; i ++){
						//console.log(case_result[i]);
						
						if( i != 0){
							if(case_result[i].result == "AC"){
								var html = '<div class="case_result ac" style="margin-left:10px">'+case_result[i].result+'</div>';
								$("#case_result").append(html);
							}
							else{
								var html = '<div class="case_result other" style="margin-left:10px">'+case_result[i].result+'</div>';
								$("#case_result").append(html);
							}
						}
						else{
							if(case_result[i].result == "AC"){
								var html = '<div class="case_result ac">'+case_result[i].result+'</div>';
								$("#case_result").append(html);
							}
							else{
								var html = '<div class="case_result other">'+case_result[i].result+'</div>';
								$("#case_result").append(html);
							}
						}
						
					}
				}
				else{
					var result = "最后总结果为：" + data.result +",系统出错，请重新提交";
					$("#total_result").append(result);
				}

			}
			);
	
});

$(function(){
	$.ajaxSettings.async = false;
	$.post(
			"QuerySingleSubmission.action",
			{
				submission_id:sid
			},
			function(data){
				var data = JSON.parse(data);
				var $trTemp = $("<tr ></tr>");
				//往行里面追加 td单元格
				$trTemp.append("<td style=" + "text-align:center"  + ">"+ data.submissionId +"</td>");
				$trTemp.append("<td style=" + "text-align:center"  + ">" +data.userAccount  +"</td>");
				$trTemp.append("<td style=" + "text-align:center;"  + ">"  +data.problemId +"</td>");
				$trTemp.append("<td style=" + "text-align:center"  + ">" +data.submissionResult  +"</td>");
				$trTemp.append("<td style=" + "text-align:center"  + ">" +data.codeMemory  +"</td>");
				$trTemp.append("<td style=" + "text-align:center"  + ">" +data.codeTime  +"</td>");
				$trTemp.append("<td style=" + "text-align:center"  + ">" +data.codeType  +"</td>");
				$trTemp.append("<td style=" + "text-align:center"  + ">" +data.codeLength  +"</td>");
				$trTemp.append("<td style=" + "text-align:center"  + ">" +data.submissionTime  +"</td>");
				// $("#J_TbData").append($trTemp);
				$trTemp.appendTo("#KnowList");
			}
			);
	
});

