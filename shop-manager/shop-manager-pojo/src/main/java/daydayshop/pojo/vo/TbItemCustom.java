package daydayshop.pojo.vo;

import daydayshop.pojo.po.TbItem;

//自定义商品显示类(VO)
public class TbItemCustom extends TbItem {

    private String catName;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
