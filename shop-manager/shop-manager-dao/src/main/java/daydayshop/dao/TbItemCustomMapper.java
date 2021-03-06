package daydayshop.dao;

import daydayshop.pojo.vo.TbItemCustom;

import java.util.List;
import java.util.Map;

//自定义商品实体数据访问层接口
public interface TbItemCustomMapper {

    //查询商品表中所有记录的数量
    int countItems(Map<String, Object> map);

    //查询指定页码显示集合记录(多参数传递时，应使用注解@Param)
    List<TbItemCustom> listItemsByPage(Map<String, Object> map);
}
