package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemCat")
@CrossOrigin
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 请求路径: /itemCat/findItemCatList/{level}
     * 请求类型: get
     * 请求参数: level 1/2/3
     * 返回值：SysResult data:三级商品分类信息
     */
    @GetMapping("/findItemCatList/{level}")
    public SysResult findItemCatList(@PathVariable Integer level) {
        List<ItemCat> catList = itemCatService.findItemCatList(level);
        return SysResult.success(catList);
    }

    /**
     * 实现商品分类新增操作
     * 请求路径: /itemCat/saveItemCat
     * 请求类型: post
     * 请求参数: JSON串
     * 返回值：SysResult对象
     */
    @PostMapping("/saveItemCat")
    public SysResult saveItemCat(@RequestBody ItemCat itemCat) {
        itemCatService.saveItemCat(itemCat);
        return SysResult.success();
    }

    /**
     * 商品分类删除
     * 请求路径: /itemCat/deleteItemCat
     * 请求类型: delete
     * 参数： ID /Level
     * 返回值：SysResult
     */
    @DeleteMapping("/deleteItemCat")
    public SysResult deleteItemCat(ItemCat itemCat) {
        itemCatService.deleteItemCat(itemCat);
        return SysResult.success();
    }
}
