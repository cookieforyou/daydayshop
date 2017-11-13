<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="easyui-panel" title="商品详情" data-options="fit:true">
    <form class="itemForm" id="itemAddForm" name="itemAddForm" method="post">
        <table style="width:100%;">
            <tr>
                <td class="label">商品类目：</td>
                <td>
                    <input id="cid" name="cid" style="width:200px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品标题：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="title" name="title"
                           data-options="required:true" style="width:100%">
                </td>
            </tr>
            <tr>
                <td class="label">商品卖点：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="sellPoint" name="sellPoint"
                           data-options="validType:'length[0,150]',multiline:true" style="width:100%;height:60px;">
                </td>
            </tr>
            <tr>
                <td class="label">商品价格：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="priceView" name="priceView"
                           data-options="required:true,min:0,precision:2">
                    <input type="hidden" id="price" name="price">
                </td>
            </tr>
            <tr>
                <td class="label">商品库存：</td>
                <td>
                    <input class="easyui-numberbox" type="text" id="num" name="num"
                           data-options="required:true,min:0,precision:0">
                </td>
            </tr>
            <tr>
                <td class="label">条形码：</td>
                <td>
                    <input class="easyui-textbox" type="text" id="barcode" name="barcode"
                           data-options="validType:'length[0,30]'">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- 加载富文本编辑器的容器 -->
                    <script id="container" name="desc" type="text/plain">商品描述</script>
                </td>
            </tr>
            <tr class="paramsShow" style="display:none;">
                <td class="label">商品规格：</td>
                <td>

                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button onclick="submitForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-ok'">保存
                    </button>
                    <button onclick="clearForm()" class="easyui-linkbutton" type="button"
                            data-options="iconCls:'icon-undo'">重置
                    </button>
                </td>
            </tr>
        </table>
        <input name="paramData" id="paramData" style="display:none;">
    </form>
</div>

<script>
    //实例化富文本编辑器之前先删除上一次实例化的
    UE.delEditor("container");
    //实例化富文本编辑器
    var ue = UE.getEditor('container',{
        initialFrameWidth: '100%',
        initialFrameHeight: '400'
    });
    //加载商品类目的树形下拉框
    $("#cid").combotree({
        url: "itemCats?parentId=0",
        required: true,
        onBeforeExpand: function (node) {
            //获取当前被点击的tree
            var $currentTree = $("#cid").combotree("tree");
            //调用easyUI tree 组件的options方法
            var option = $currentTree.tree("options");
            //修改option的url属性
            option.url = "itemCats?parentId=" + node.id;
        },
        onBeforeSelect: function (node) {
            //判断选中节点是否为叶子结点,如果是,返回true
            var isLeaf = $("#cid").tree("isLeaf", node.target);
            //如果选中的不是叶子结点,给出警告框
            if (!isLeaf) {
                $.messager.alert("警告", "请选择最终类别!", "warning");
                return false;
            }
        }
    });
    function submitForm() {
        $("#itemAddForm").form("submit", {
            //提交表单动作的URL地址
            url: "saveItem",
            //在提交之前触发,返回false终止提交
            onSubmit: function () {
                //给隐藏域设置ID属性,并且设值
                $("#price").val($("#priceView").val() * 100);
                return $(this).form("validate");
            },
            //在表单成功提交后触发
            success: function (data) {
                //console.log(data); data为ItemAction的saveItem方法执行保存商品成功后返回的受影响的行数
                if (data > 0) {
                    $.messager.alert("消息", "保存成功", "info");
                    ddshop.saveItemSuccessTab();
                }
            }
        });
    }
    function clearForm() {
        $("#itemAddForm").form("reset");
        ue.setContent("商品描述");
    }

</script>