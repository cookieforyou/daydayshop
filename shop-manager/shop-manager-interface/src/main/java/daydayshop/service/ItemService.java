package daydayshop.service;

import daydayshop.common.dto.Page;
import daydayshop.common.dto.Result;
import daydayshop.pojo.po.TbItem;
import daydayshop.pojo.vo.TbItemCustom;

import java.util.List;

public interface ItemService {

    public TbItem getItemById(Long itemId);

    List<TbItem> getItemList();

    Result<TbItemCustom> listItemsByPage(Page page); //分页

    int updateBatch(List<Long> ids, String status); //批量修改状态
}
