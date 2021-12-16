package com.jt.controller;


import com.jt.service.ItemService;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * <h2>商品列表展现</h2>
     * <p>请求路径: /item/getItemList?query=&pageNum=1&pageSize=10</p>
     * <p>请求类型: get</p>
     *
     * @param pageResult {@link PageResult}
     * @return {@link SysResult}
     */
    @GetMapping("/getItemList")
    //Get可以直接拼对象
    public SysResult getItemList(PageResult pageResult) { //三个参数

        pageResult = itemService.getItemList(pageResult); //3+2
        return SysResult.success(pageResult);

    }

    /**
     * <h2>商品新增业务</h2>
     * 请求路径: /item/saveItem
     * 请求类型: post
     * 参数信息：itemVO对象 JSON串
     */
    @PostMapping("/saveItem")
    public SysResult saveItem(@RequestBody ItemVO itemVO) {
        itemService.saveItem(itemVO);
        return SysResult.success();

    }

    /**
     * 业务: 实现商品删除操作
     * URL: /item/deleteItemById?id=xxx
     * 参数: id
     * 返回值: SysResult对象
     */
    @DeleteMapping("/deleteItemById")
    public SysResult deleteItemById(Integer id) {

        itemService.deleteItemById(id);
        return SysResult.success();

    }
}
