package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;

    /**
     * SQL: select * from item limit 起始位置,显示条数
     * 参数：
     * page 用来风筝分页参数 第几页.每页多少条
     * queryWrapper 查询条件构造器
     *
     * @param pageResult
     * @return
     */
    @Override
    public PageResult getItemList(PageResult pageResult) {
        //构建模糊查询
        boolean flag = StringUtils.hasLength(pageResult.getQuery());
        QueryWrapper<Item> queryWrapper = new QueryWrapper();
        queryWrapper.like(flag, "title", pageResult.getQuery());

        //定义分页对象 2
        IPage<Item> page = new Page<>(pageResult.getPageNum(), pageResult.getPageSize());
        //page 的参数由原来的页数/条数 经过业务调用添加了 总记录数 和分页的结果
        page = itemMapper.selectPage(page, queryWrapper); //2+2
        long totalPage = page.getTotal(); //获取总数
        List<Item> rows = page.getRecords(); //获取分页的结果
        return pageResult.setTotal(totalPage).setRows(rows);
    }

    /**
     * 两部分入库操作
     * (!) Item 的入库操作
     * (!) ItemDesc入库操作
     * MP基于对象的方式操作数据,如果实现数据的入库操作,
     * 则数据都会与对象绑定,动态回显.
     *
     * @param itemVO
     */
    @Transactional
    @Override
    public void saveItem(ItemVO itemVO) {
        //1 实现Item对象入库
        Item item = itemVO.getItem().setStatus(true);
        //刚开始id为null，入库操作时，id在数据库中会自动赋值
        //赋值时ID依然为null
        itemMapper.insert(item);

        //1 实现ItemDesc对象入库
        ItemDesc itemDesc = itemVO.getItemDesc();
        itemDesc.setId(item.getId());
        itemDescMapper.insert(itemDesc);

    }

    @Override
    @Transactional
    public void deleteItemById(Integer id) {
        itemMapper.deleteById(id);
        itemDescMapper.deleteById(id);
    }
}
