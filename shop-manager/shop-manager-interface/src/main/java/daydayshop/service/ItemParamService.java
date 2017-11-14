package daydayshop.service;

import daydayshop.common.dto.Page;
import daydayshop.common.dto.Result;
import daydayshop.pojo.po.TbItemParam;
import daydayshop.pojo.vo.TbItemParamCustom;

public interface ItemParamService {
    //对参数规格说明表进行分页操作
    Result<TbItemParamCustom> listItemParamsByPage(Page page);
    //保存商品参数规格
    int saveItemParam(Long cid, String paramData);

    TbItemParam getItemParamByCid(Long cid);
}
