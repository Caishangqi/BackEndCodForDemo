package com.jt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

//pojo基类，完成2个任务，2个日期，实现序列化
@Data
@Accessors(chain = true)
public class BasePojo implements Serializable {
	private Date created;    //表示入库时需要赋值
	private Date updated;    //表示入库/更新时赋值.
}
