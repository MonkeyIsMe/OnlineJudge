var url = decodeURI(window.location.href);
 
var argsIndex = url .split("?ProblemId=");
var pid = argsIndex[1];

let first; //存放一个选择框
var array = [];

var choosed;
var cnt = 0;

$(document).ready(function(){
    $("#head").load('MainHead.html');
    
	
	$.post(
			"QueryInCase.action",
			{
				problem_id:pid,
			}, 
			function(data) {
				var datas = JSON.parse(data);
				//console.log(datas);
				$("#pincase").append("输入：<br>" + datas[0].caseInput);
				$("#poutcase").append("输出：<br>" + datas[0].caseOutput);
			}
	);
    
    
    $("#saveserver").click(function(){
    	var val = $('input[name="code"]:checked').val(); 
    	var name = val.split(".");
    	for(var i = 0; i <array.length; i ++){
    		var code_in;
    		if(array[i].key == name[0]){
    			if(array[i].value == null || array[i].value == ""){
    				code_in = editor.getValue();
    			}
    			$.post(
    					"AddCode.action",
    					{
    						code_name:val,
    						code_info:code_in
    					},
    					function(data){
    						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
    						if(data == "Fail"){
    							alert("保存失败！");
    						}
    						else{
    							alert("保存成功!");
    						}
    					}
    					);
    		}
    	}
    })
    
    $("#test").click(function(){
    	var info = editor.getValue();
    	var input = $("#input").val();
    	input = input +"";
    	var datas = [];
    	datas.push(input);
    	   $.ajax({
    	        url:'http://192.168.1.144:8089/consumer/judge.do',
    	        type:'POST',
    	        contentType: 'application/json; charset=UTF-8',
    	        dataType:'json',
    	        data:JSON.stringify({
    	        	input:datas,
    	        	timeLimit:1000,
    	        	memoryLimit:65535,
    	        	judgeId:4,
    	        	src:info,
    	        }),
    	        function (response) {
    	            console.log(response);
    	        }
    	    })
    	
    })
    
    $("#compile").click(function(){
    	//alert(123);
    	var info = editor.getValue();
    	   $.ajax({
    	        url:'http://192.168.1.144:8089/consumer/judge.do',
    	        type:'POST',
    	        contentType: 'application/json; charset=UTF-8',
    	        dataType:'json',
    	        data:JSON.stringify({
    	        	input:[""],
    	        	timeLimit:1000,
    	        	memoryLimit:65535,
    	        	judgeId:4,
    	        	src:info,
    	        }),
    	        success: function (data) {
    	        	var str = JSON.stringify(data);
    	        	var datas = JSON.parse(str);
    	        	if(datas.globalMsg == null || datas.globalMsg ==""){
    	        		$("#area").append("编译成功");
    	        	}
    	        	else{
    	        		$("#area").val(datas.globalMsg);
    	        	}
    	        }
    	    })
    	
    })
    
    $("#submit").click(function(){
    	var info = editor.getValue();
    	var val = $('input[type="radio"]:checked').val(); 
    	var lang = val.split(".");
    	$.post(
    			"AddSubmission.action",
    			{
    				submission_type:lang[1],
    				problem_id:pid,
    				submission_code:info,
    			},
    			function(data){
    				var data = JSON.parse(data);
    				var sid = data.SubmissionId;
    			    var url = "SeeResult.html?sid=" + sid;
    			    window.location.replace(url);
    			}
    			);
    	
    })
    
})




//编译器
ace.require("ace/ext/language_tools");
var editor = ace.edit("peditor");
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


//对主题的切换
$("#ambiance").click(function () {
    editor.setTheme("ace/theme/ambiance");
    $("#theme").text("ambiance");
    

    
})


$("#chaos").click(function () {
    editor.setTheme("ace/theme/chaos");
    $("#theme").text("chaos");
})

$("#chrome").click(function () {
    editor.setTheme("ace/theme/chrome");
    $("#theme").text("chrome");
})

$("#dracula").click(function () {
    editor.setTheme("ace/theme/dracula");
    $("#theme").text("dracula");
})

$("#dreamweaver").click(function () {
    editor.setTheme("ace/theme/dreamweaver");
    $("#theme").text("dreamweaver");
})

$("#eclipse").click(function () {
    editor.setTheme("ace/theme/eclipse");
    $("#theme").text("eclipse");
})

$("#xcode").click(function () {
    editor.setTheme("ace/theme/xcode");
    $("#theme").text("xcode");
})


$("#terminal").click(function () {
    editor.setTheme("ace/theme/terminal");
    $("#theme").text("terminal");
})

//字体大小设置
function fontsize(size) {
    $("#editor").css("font-size",size);
}

function ChooseLang(ch) {
    choosed = ch;
    //console.log(ch);
}

$("#CreateCode").click(function(){
    //alert(choosed)
    var filename = $("#CodeName").val();
    var language = choosed;
    var lang;
    
    var flag = IsCreate(filename);
    
    if(flag == true){
        if(language == 'C++' || language == 'C'){
            lang = 'cpp';
            editor.session.setMode("ace/mode/c_cpp");
        }
        if(language == 'Java'){
            lang = 'class';
            editor.session.setMode("ace/mode/java");
        }
        if(language == 'Python'){
            lang = 'py';
            editor.session.setMode("ace/mode/python");
        }
        else if(language =="" || language == null){
        	alert("请先选择语言!");
        }
        else if(filename == null || filename ==""){
        	alert("文件不能为空!");
        }
        else{
        	
        	$("#peditor").css("display","block");
        	
            if(cnt == 0){
                var html ="<div class='file'><input id='"+filename+"' type='radio' value='" + filename+"."+lang+  "'checked name=code onclick=inputcode('" + filename + "')>"+filename+"."+lang+"</div>";
                first = filename;
                var arr ={
                    key:filename,
                    value:""
                }
                editor.setValue("");
                array.push(arr)
                cnt++;
            }
            else{
            	var html ="<div class='file'><input id='"+filename+"' type='radio' value='" + filename+"."+lang+  "'checked name=code onclick=inputcode('" + filename + "')>"+filename+"."+lang+"</div>";
                var arr ={
                    key:filename,
                    value:""
                }
                editor.setValue("");
                array.push(arr)
            }
        }

        $("#content").append(html);
        $("#CodeName").val("");
    }
    else{
    	alert("文件已存在！");
    	$("#CodeName").val("");
    }

})

$("#clear").click(function () {
    editor.setValue("");
})


function inputcode(filename) {

    var firstcode = editor.getValue();
    for(var j in array){
        if(array[j].key == first){
            array[j].value = firstcode;
        }
    }

    first = filename;
    var secondcode = "";
    for(var k in array){
        if(array[k].key == first){
            secondcode = array[k].value;
        }
    }

    if(secondcode == null) secondcode ="";
    editor.setValue(secondcode);

}

//判断文件是否存在
function IsCreate(filename){
	var flag = true;
    for(var j in array){
        if(array[j].key == filename){
            flag = false;
        }
    }
    return flag;
}

