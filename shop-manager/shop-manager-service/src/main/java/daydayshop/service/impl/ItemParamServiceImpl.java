package daydayshop.service.impl;

import daydayshop.common.dto.Page;
import daydayshop.common.dto.Result;
import daydayshop.dao.TbItemParamCustomMapper;
import daydayshop.dao.TbItemParamMapper;
import daydayshop.pojo.po.TbItemParam;
import daydayshop.pojo.po.TbItemParamExample;
import daydayshop.pojo.vo.TbItemParamCustom;
import daydayshop.service.ItemParamService;
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
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamCustomMapper itemParamCustomDao;

    @Autowired
    private TbItemParamMapper itemParamDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional
    public int saveItemParam(Long cid, String paramData) {
        int saveData = 0;
        TbItemParam itemParam = null;
        try{
            itemParam = new TbItemParam();
            itemParam.setItemCatId(cid);
            itemParam.setParamData(paramData);
            itemParam.setCreated(new Date());
            itemParam.setUpdated(new Date());
            saveData = itemParamDao.insert(itemParam);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return saveData;
    }

    @Override
    public TbItemParam getItemParamByCid(Long cid) {
        TbItemParam tbItemParam = null;
        try{
            //创建查询模板
            TbItemParamExample example = new TbItemParamExample();
            TbItemParamExample.Criteria criteria = example.createCriteria();
            criteria.andItemCatIdEqualTo(cid);
            //执行查询
            List<TbItemParam> list = itemParamDao.selectByExampleWithBLOBs(example);
            if (list != null && list.size() > 0) {
                tbItemParam = list.get(0);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return tbItemParam;
    }

    @Override
    public Result<TbItemParamCustom> listItemParamsByPage(Page page) {
        Result<TbItemParamCustom> result = null;
        try {
            //DAO层接口多多个参数解决方案之一
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("page", page);
            //取出tb_item_param这张表记录条数
            int count = itemParamCustomDao.countItemParams();
            //取出对应页码的记录集合
            List<TbItemParamCustom> list = itemParamCustomDao.listItemParamsByPage(map);
            result = new Result<TbItemParamCustom>();
            result.setTotal(count);
            result.setRows(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }
}
