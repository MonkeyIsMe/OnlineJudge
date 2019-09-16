let first; //存放一个选择框
var array = [];

var choosed;
var i = 0;

$(document).ready(function(){
    $("#head").load('MainHead.html');
    $("#editor").css("display","none");
    
    $("#saveserver").click(function(){
    	var val = $('input[name="code"]:checked').val(); 
    	//alert(val);
    	for(var i = 0; i <array.length; i ++){
    		//console.log(array);
    		if(array[i].key == val){
    			//console.log(array[i]);
    			$.post(
    					"AddCode.action",
    					{
    						code_name:val,
    						code_info:array[i].value
    					},
    					function(data){
    						data = data.replace(/^\s*/, "").replace(/\s*$/, "");
    						if(data == "Fail"){
    							alert("保存失败！");
    							//window.location.replace("ManagerUserSet.html");
    						}
    						else{
    							alert("保存成功!");
    							//window.location.replace("ManagerUserSet.html");
    						}
    					}
    					);
    		}
    	}
    })
})




//编译器
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
        	
        	$("#editor").css("display","block");
        	
            if(i == 0){
                var html ="<div class='file'><input id='"+filename+"' type='radio' value='" + filename + "' checked=\"checked\" name='code' onclick=inputcode('" + filename + "')>"+filename+"."+lang+"</div>";
                first = filename;
                var arr ={
                    key:filename,
                    value:""
                }
                editor.setValue("");
                array.push(arr)
                i++;
            }
            else{
                var html ="<div class='file'><input id='"+filename+"' type='radio' value='" + filename + "'checked=\"checked\"  name='code' onclick=inputcode('" + filename + "')>"+filename+"."+lang+"</div>";
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

//筛选和存储文件
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
