package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/4/7
 */
@Data
@Accessors(chain = true)
@TableName("item_desc")
public class ItemDesc extends BasePojo {
    @TableId                 //只标识主键即可
    private Integer id;      //item.id一致
    private String itemDesc; //html代码片段
}
