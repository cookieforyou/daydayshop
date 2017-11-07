<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<table id="grid"></table>

<script>

    $("#grid").datagrid({
        url: "items", //不能写/items，因为该URL是传到Action里具体方法的，其URL已有/
        striped: true, //隔行换色
        pagination: true, //添加分页工具栏
        rownumbers: true, //添加行号
        fit: true, //使数据表格自适应填充父容器
        pageSize: 10, //默认每页显示10条数据
        pageList: [10, 20, 30, 40, 50], //分页大小选项列表
        columns: [[
            {field: "ck", checkbox: true}, //添加复选按钮
            {field: "id", title: "商品编号", width: 100},
            {field: "title", title: "商品名称", width: 200},
            {field: "sellPoint", title: "商品买点", width: 200},
            {field: "catName", tittle: "商品分类", width: 100},
            {field: "price", title: "商品价格", width: 100, align: "right"}
        ]]
    });

</script>
