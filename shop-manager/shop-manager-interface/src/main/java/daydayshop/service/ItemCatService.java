package daydayshop.service;

import daydayshop.common.dto.TreeNode;

import java.util.List;

public interface ItemCatService {

    public List<TreeNode> listItemCats(Long parentId);
}
