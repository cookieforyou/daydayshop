var ddshop = {

    //点击左侧导航树上的节点，添加选项卡
    registerMenuEvent: function () {
        var _this = this; //这里的this指变量对象ddshop
        var $tree = $("#menu .easyui-tree"); //约定大于配置：jquery对象前面加上$,如果是DOM对象不需要加$
        $tree.tree({
            onClick: function (node) {
                var href = node.attributes.href;//item-add
                var text = node.text;
                _this.addTab(text, href); //不能使用this.addTab(text, href);这里的this指$tree对象
            }
        });
    },
    //添加选项卡
    addTab: function (text, href) {
        if ($("#tab").tabs("exists", text)) {
            $("#tab").tabs("select", text); //如存在,选中并刷新
        } else {
            $("#tab").tabs("add", {
                title: text,
                href: href,
                closable: true
            });
        }
    },
    //新增商品保存成功关闭新增商品选项卡
    saveItemSuccessTab: function () {
        if ($("#tab").tabs("exists", "查询商品")) {
            $("#tab").tabs("close", "查询商品");
        }
        if ($("#tab").tabs("exists", "新增商品")) {
            $("#tab").tabs("close", "新增商品");
            this.addTab("查询商品", "item-list");
        }
    }

};



