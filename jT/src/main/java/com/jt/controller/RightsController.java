package com.jt.controller;


import com.jt.pojo.Rights;
import com.jt.service.RightsService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rights")
@CrossOrigin //前后端跨域操作
public class RightsController {

    @Autowired
    private RightsService rightsService;

    /**
     * URL地址 /rights/getRightsList
     * 参数: 无参数
     * 类型: Get类型
     * 返回值：SysResult对象(list)集合
     */
    @GetMapping("/getRightsList")
    public SysResult getRightsList() {

        List<Rights> list = rightsService.getRightsList();
        return SysResult.success(list);
    }

}
