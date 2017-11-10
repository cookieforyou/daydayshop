package daydayshop.service.impl;

import daydayshop.common.dto.TreeNode;
import daydayshop.dao.TbItemCatMapper;
import daydayshop.pojo.po.TbItemCat;
import daydayshop.pojo.po.TbItemCatExample;
import daydayshop.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper mapper;

    @Override
    public List<TreeNode> listItemCats(Long parentId) {
        //创建查询模板
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> itemCatList = mapper.selectByExample(example);
        //创建树形节点集合用于接收遍历list集合的TbItemCat
        List<TreeNode> treeNodeList = new ArrayList<>();
        //遍历查询到的TbItemCat集合
        for (int i = 0; i < itemCatList.size(); i++) {
            TbItemCat cat = itemCatList.get(i);
            TreeNode node = new TreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent() ? "closed" : "open");
            treeNodeList.add(node);
        }
        return treeNodeList;
    }
}
