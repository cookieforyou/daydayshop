package daydayshop.web;

import daydayshop.common.dto.Page;
import daydayshop.common.dto.Result;
import daydayshop.pojo.po.TbItem;
import daydayshop.pojo.vo.TbItemCustom;
import daydayshop.service.ItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
public class ItemAction {

    @Autowired
    private ItemService itemService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    public TbItem getItemById(@PathVariable("itemId") Long itemId) {
        TbItem item = itemService.getItemById(itemId);
        return item;
    }

    @RequestMapping(value = "/items")
    @ResponseBody
    public Result<TbItemCustom> listItemsByPage(Page page) {

        Result<TbItemCustom> list = null;
        try {
            list = itemService.listItemsByPage(page);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return list;
    }
}
