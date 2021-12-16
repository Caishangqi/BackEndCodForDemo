package com.jt.service;

import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;

public interface ItemService {
    PageResult getItemList(PageResult pageResult);

    void saveItem(ItemVO itemVO);

    void deleteItemById(Integer id);
}
