package com.jt.service;

import com.jt.mapper.RightsMapper;
import com.jt.pojo.Rights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightsServiceImpl implements RightsService {

    @Autowired
    private RightsMapper rightsMapper;

    /**
     * 获取1-2级的菜单信息
     * 一级菜单：parent_id = 0
     * 二级菜单：parent_id = 1
     * 将二级菜单的数据封装到一级菜单的 children 中
     *
     * @return
     */
    @Override
    public List<Rights> getRightsList() {
        //只获取1级和2级数据
        return rightsMapper.getRightsList();
    }
}
