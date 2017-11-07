package daydayshop.service.impl;

import daydayshop.common.dto.Page;
import daydayshop.common.dto.Result;
import daydayshop.dao.TbItemCustomMapper;
import daydayshop.dao.TbItemMapper;
import daydayshop.pojo.po.TbItem;
import daydayshop.pojo.vo.TbItemCustom;
import daydayshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper mapper;

    @Autowired
    private TbItemCustomMapper custom;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public TbItem getItemById(Long itemId) {

        TbItem tbItem = mapper.selectByPrimaryKey(itemId);

        return tbItem;
    }

    @Override
    public List<TbItem> getItemList() {

        List<TbItem> list = null;
        try {
            list = mapper.selectByExample(null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Result<TbItemCustom> listItemsByPage(Page page) {

        Result<TbItemCustom> result = null;
        try {
            //创建一个响应参数实体类
            result = new Result<TbItemCustom>();
            //对total进行设值(符合条件的总记录数)
            int total = custom.countItems();
            result.setTotal(total);
            //对rows进行设值(符合条件的记录集合)
            List<TbItemCustom> list = custom.listItemsByPage(page);
            result.setRows(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }
}
