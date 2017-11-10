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
    }

};



