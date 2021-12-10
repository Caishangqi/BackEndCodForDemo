package com.jt.mapper;

import com.jt.pojo.Rights;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RightsMapper {
    List<Rights> getRightsList();
}
