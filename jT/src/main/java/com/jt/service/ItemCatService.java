package com.jt.service;

import com.jt.pojo.ItemCat;

import java.util.List;

public interface ItemCatService {
    List<ItemCat> findItemCatList(Integer level);

    void saveItemCat(ItemCat itemCat);

    void deleteItemCat(ItemCat itemCat);
}
