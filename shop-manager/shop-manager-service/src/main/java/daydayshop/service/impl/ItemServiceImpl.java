package daydayshop.service.impl;

import daydayshop.common.dto.IDUtils;
import daydayshop.common.dto.Order;
import daydayshop.common.dto.Page;
import daydayshop.common.dto.Result;
import daydayshop.dao.TbItemCustomMapper;
import daydayshop.dao.TbItemDescMapper;
import daydayshop.dao.TbItemMapper;
import daydayshop.dao.TbItemParamItemMapper;
import daydayshop.pojo.po.TbItem;
import daydayshop.pojo.po.TbItemDesc;
import daydayshop.pojo.po.TbItemExample;
import daydayshop.pojo.po.TbItemParamItem;
import daydayshop.pojo.vo.TbItemCustom;
import daydayshop.pojo.vo.TbItemQuery;
import daydayshop.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper mapper;

    @Autowired
    private TbItemCustomMapper custom;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public int updateBatch(List<Long> ids, String status) {
        int i = 0;
        try {

            //创建状态为3的商品对象
            TbItem record = new TbItem();

            if (status.equals("1")) {
                record.setStatus((byte) 1); //set status = ?
            }
            if (status.equals("2")) {
                record.setStatus((byte) 2); //set status = ?
            }
            if (status.equals("3")) {
                record.setStatus((byte) 3); //set status = ?
            }

            //创建更新模板
            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids); //where id in (?,?,?)

            //执行更新 update tb_item set status = ? where id in (?,?,?)
            i = mapper.updateByExampleSelective(record, example);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return i;
    }

    @Override
    @Transactional
    public int saveItem(TbItem tbItem, String desc, String paramData) {
        long itemId = IDUtils.getItemId();
        tbItem.setId(itemId);
        tbItem.setStatus((byte) 1);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        int count = mapper.insert(tbItem);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        count += tbItemDescMapper.insert(tbItemDesc);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(paramData);
        tbItemParamItem.setCreated(new Date());
        tbItemParamItem.setUpdated(new Date());
        count += tbItemParamItemMapper.insert(tbItemParamItem);

        return count;
    }

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
    public Result<TbItemCustom> listItemsByPage(Page page, Order order, TbItemQuery tbItemQuery) {
        Result<TbItemCustom> result = null;
        try {
            //创建Map存储形参,解决多参数传递问题
            Map<String, Object> map = new HashMap<>();
            map.put("page", page);
            map.put("order", order);
            map.put("tbItemQuery", tbItemQuery);
            //创建一个响应参数实体类
            result = new Result<TbItemCustom>();
            //对total进行设值(符合条件的总记录数)
            int total = custom.countItems(map);
            result.setTotal(total);
            //对rows进行设值(符合条件的记录集合)
            List<TbItemCustom> list = custom.listItemsByPage(map);
            result.setRows(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }
}
