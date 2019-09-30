var user_str;

$('#excel-file').change(function(e) {
    var files = e.target.files;

    var fileReader = new FileReader();
    fileReader.onload = function(ev) {
        try {
            var data = ev.target.result,
                workbook = XLSX.read(data, {
                    type: 'binary'
                }), // 以二进制流方式读取得到整份excel表格对象
                persons = []; // 存储获取到的数据
        } catch (e) {
            console.log('文件类型不正确');
            return;
        }

        // 表格的表格范围，可用于判断表头是否数量是否正确
        var fromTo = '';
        // 遍历每张表读取
        for (var sheet in workbook.Sheets) {
            if (workbook.Sheets.hasOwnProperty(sheet)) {
                fromTo = workbook.Sheets[sheet]['!ref'];
                console.log(fromTo);
                persons = persons.concat(XLSX.utils.sheet_to_json(workbook.Sheets[sheet]));
                // break; // 如果只取第一张表，就取消注释这行
            }
        }
        var jsString=JSON.stringify(persons)
        console.log(persons);
        user_str = jsString;
        layui.use('table', function(){
            var table = layui.table;
            table.render({
                elem: '#test'
                ,data:persons
                ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,page: true //开启分页
                ,cols: [[
                    {field:'序号', title: '序号', sort: true}
                    ,{field:'姓名', title: '姓名'} //width 支持：数字、百分比和不填写。你还可以通过 minWidth 参数局部定义当前单元格的最小宽度，layui 2.2.1 新增
                    ,{field:'备注信息', title: '备注信息'}
                    ,{field:'班级', title: '班级'}
                    ,{field:'学号', title: '学号'}
                ]]
            });
        });

    };

    // 以二进制方式打开文件
    fileReader.readAsBinaryString(files[0]);
});


