<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div id="toolbar">
    <div style="padding: 5px; background-color: #B4EEB4;">
        <label>商品标题：</label>
        <input class="easyui-textbox" type="text" id="title">
        <label>商品状态：</label>
        <select id="status" class="easyui-combobox" >
            <option value="0">全部</option>
            <option value="1">正常</option>
            <option value="2">下架</option>
        </select>
        <!--注意：要加上type="button",默认行为是submit-->
        <button onclick="searchForm()" type="button" class="easyui-linkbutton">搜索</button>
    </div>
    <div>
        <button onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</button>
        <button onclick="edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</button>
        <button onclick="remove()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</button>
        <button onclick="down()" class="easyui-linkbutton" data-options="iconCls:'icon-down',plain:true">下架</button>
        <button onclick="up()" class="easyui-linkbutton" data-options="iconCls:'icon-up',plain:true">上架</button>
    </div>
</div>

<table id="grid"></table>

<script>

    function add() {
        alert("add");
    }
    function edit() {
        alert("edit");
    }
    function remove() {
        //获取所有被选中的行，当没有记录被选中的时候将返回一个空数组
        var selections = $("#grid").datagrid("getSelections");
        if(selections.length == 0){
            //没有记录被选中
            $.messager.alert("提示", "请至少选中一条记录!", "warning");
            return;
        }
        //至少选中了一行
        //confirm,确认框,参数分别为标题,确认框提示内容,确认函数
        //确认函数function (r) {},如果点击确定,那么r=true
        $.messager.confirm("确认", "确认删除所选记录?", function (r) {
            if (r){
                //用于存放选取记录的id
                var ids = [];
                for (var i = 0; i < selections.length; i++){
                    ids.push(selections[i].id); //遍历选中记录,将记录的id存放到js数组中
                }
                //将用于存放选取记录id的js数组ids异步提交到后台
                $.post(
                    //$.post({}) 参数为：url,data,callback,dataType，顺序固定，不能更改

                    //url:请求后台哪个action进行处理,String类型
                    //data:从前台提交给后台的数据,Object类型
                    //callback:后台处理成功后的回调函数,相当于success,function类型
                    //dataType:后台返回的数据类型,如json,String类型
                    "items/batch",
                    {"ids[]": ids, "status": 3}, //键"ids[]"中的[]不能少,这样后台Action参数才能用List接收
                    function (data){
                        $("#grid").datagrid("reload"); //完成后台批量删除之后，需要刷新页面
                        $.messager.alert("提示", "删除成功!" + data + "行受影响!");
                    },
                    "json"
                );
            }
        });
    }
    function down() {
        var selections = $("#grid").datagrid("getSelections");
        if(selections.length == 0){
            $.messager.alert("提示", "请至少选中一条记录!", "warning");
            return;
        }
        $.messager.confirm("确认", "确认下架所选记录?", function (r) {
            if (r){
                var ids = [];
                for (var i = 0; i < selections.length; i++){
                    ids.push(selections[i].id);
                }
                $.post(
                    "items/batch",
                    {"ids[]": ids, "status": 2},
                    function (data){
                        $("#grid").datagrid("reload");
                        $.messager.alert("提示", "下架成功!" + data + "行受影响!");
                    },
                    "json"
                );
            }
        });
    }
    function up() {
        var selections = $("#grid").datagrid("getSelections");
        if(selections.length == 0){
            $.messager.alert("提示", "请至少选中一条记录!", "warning");
            return;
        }
        $.messager.confirm("确认", "确认上架所选记录?", function (r) {
            if (r){
                var ids = [];
                for (var i = 0; i < selections.length; i++){
                    ids.push(selections[i].id);
                }
                $.post(
                    "items/batch",
                    {"ids[]": ids, "status": 1},
                    function (data){
                        $("#grid").datagrid("reload");
                        $.messager.alert("提示", "上架成功!" + data + "行受影响!");
                    },
                    "json"
                );
            }
        });
    }
    function searchForm() {
        $("#grid").datagrid("load", {
            title: $("#title").val(),
            status: $("#status").combobox("getValue")
        });
    }
    $("#grid").datagrid({
        url: "items", //不能写/items，因为该URL是传到Action里具体方法的，其URL已有/
        striped: true, //隔行换色
        pagination: true, //添加分页工具栏
        rownumbers: true, //添加行号
        fit: true, //使数据表格自适应填充父容器
        pageSize: 10, //默认每页显示10条数据
        pageList: [10, 20, 30, 40, 50], //分页大小选项列表
        toolbar: "#toolbar", //将工具栏添加到数据表格
        columns: [[
            {field: "ck", checkbox: true}, //添加复选按钮
            {field: "id", title: "商品编号", width: 100, sortable: true},
            {field: "title", title: "商品名称", width: 200, sortable: true},
            {field: "sellPoint", title: "商品买点", width: 200},
            {field: "catName", title: "商品分类", width: 100},
            {field: "priceFormat", title: "商品价格", width: 100, align: "right"},
            {field: "status", title: "商品状态", width: 100, formatter: function (value, row, index) {
                //value:该条记录的status值
                //row:该条记录对象
                //index:该条记录的索引
                switch (value) {
                    case 1:
                        return "正常";
                        break;
                    case 2:
                        return "下架";
                        break;
                    case 3:
                        return "删除";
                        break;
                    default:
                        return "未知";
                        break;
                }
            }},
            {field: "created", title: "创建时间", width: 100, formatter: function (value, row, index) {
                return moment(value).format("LL");
            }},
            {field: "updated", title: "修改时间", width: 100, formatter: function (value, row, index) {
                return moment(value).format("LL");
            }}
        ]]
    });

</script>
