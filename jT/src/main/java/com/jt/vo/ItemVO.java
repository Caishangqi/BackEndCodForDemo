package com.jt.vo;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Accessors(chain = true)
public class ItemVO implements Serializable {
    private Item item;
    private ItemDesc itemDesc;
}
