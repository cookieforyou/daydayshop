package daydayshop.service;

import daydayshop.common.dto.Order;
import daydayshop.common.dto.Page;
import daydayshop.common.dto.Result;
import daydayshop.pojo.po.TbItem;
import daydayshop.pojo.vo.TbItemCustom;
import daydayshop.pojo.vo.TbItemQuery;

import java.util.List;

public interface ItemService {

    public TbItem getItemById(Long itemId);

    List<TbItem> getItemList();

    Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery tbItemQuery); //分页

    int updateBatch(List<Long> ids, String status); //批量修改状态

    int saveItem(TbItem tbItem, String desc, String paramData);
}
