package com.jt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/4/7
 */
@Data
@Accessors(chain = true)
public class Item extends BasePojo {
    private Integer id;         //商品Id号
    private String title;       //商品标题信息
    private String sellPoint;   //卖点信息
    private Integer price;      //商品价格
    private Integer num;        //商品数量
    private String images;       //商品图片
    private Integer itemCatId;  //商品分类ID号
    private Boolean status;     //状态信息    0 下架 1 上架

}
