package com.jt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 刘昱江
 * 时间 2021/2/18
 */
@Data
@Accessors(chain = true)
public class Rights extends BasePojo {
    private Integer id;
    private String name;
    private Integer parentId;
    private String path;
    private Integer level;
    private List<Rights> children; //不是表格固有属性
}
