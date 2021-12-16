package com.jt.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PageResult implements Serializable {

    private String query; //用户查询的数据
    private Integer pageNum; //查询页数
    private Integer pageSize; //查询条数
    private Long total; //查询总记录数
    private Object rows; //分页查询的结果

}
