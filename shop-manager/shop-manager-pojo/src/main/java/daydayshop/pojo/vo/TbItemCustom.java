package daydayshop.pojo.vo;

import daydayshop.pojo.po.TbItem;

//自定义商品显示类(VO)
public class TbItemCustom extends TbItem {

    private String catName;
    private String priceFormat;

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getPriceFormat() {
        return priceFormat;
    }

    public void setPriceFormat(String priceFormat) {
        this.priceFormat = priceFormat;
    }
}
