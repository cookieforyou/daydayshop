package daydayshop.dao;

import daydayshop.common.dto.Page;
import daydayshop.pojo.vo.TbItemCustom;

import java.util.List;

//自定义商品实体数据访问层接口
public interface TbItemCustomMapper {

    //查询商品表中所有记录的数量
    int countItems();

    //查询指定页码显示集合记录
    List<TbItemCustom> listItemsByPage(Page page);
}
