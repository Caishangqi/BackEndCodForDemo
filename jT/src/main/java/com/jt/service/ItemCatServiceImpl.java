package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    /**
     * (!) 判断Map集合中是否存在key
     * (!) 如果key不存在，准备一个新的list集合，讲自己作为第一个元素添加
     * (!) 如果key存在，获取list集合，把自己追加进去
     *
     * @return {@link Map<Integer,List<ItemCat>>}
     */
    public Map<Integer, List<ItemCat>> getMap() {
        Map<Integer, List<ItemCat>> map = new HashMap<>();
        //查询数据库所有记录
        List<ItemCat> list = itemCatMapper.selectList(null);
        for (ItemCat itemCat : list) {
            int key = itemCat.getParentId();
            if (map.containsKey(key)) { //true

                map.get(key).add(itemCat);

            } else { //false 没有数据
                List<ItemCat> childrenList = new ArrayList<>();
                childrenList.add(itemCat);
                map.put(key, childrenList);
            }
        }

        return map;

    }

    /**
     * 只查询一级菜单
     * <p>parent id=0</p>
     * 查询二级菜单
     * <p>parent id=一级ID</p>
     * 查询三级菜单
     * <p>parent id=二级ID</p>
     *
     * @param level
     * @return
     */
    @Override
    public List<ItemCat> findItemCatList(Integer level) {
//                    _         _           _                _
//                   | |       | |         | |              | |
//     ___    _   _  | |_    __| |   __ _  | |_    ___    __| |
//    / _ \  | | | | | __|  / _` |  / _` | | __|  / _ \  / _` |
//   | (_) | | |_| | | |_  | (_| | | (_| | | |_  |  __/ | (_| |
//    \___/   \__,_|  \__|  \__,_|  \__,_|  \__|  \___|  \__,_|
//
//
//        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parent_id", 0);
//        List<ItemCat> oneList = itemCatMapper.selectList(queryWrapper);
//        for (ItemCat oneItemCat : oneList) {
//            int oneItemCatId = oneItemCat.getId();//获取每一个一级对象的ID
//            //清空多余的条件
//            queryWrapper.clear();
//            //清空完毕，新的条件
//            queryWrapper.eq("parent_id", oneItemCatId);
//            List<ItemCat> twoList = itemCatMapper.selectList(queryWrapper); //二级菜单
//            //获取三级菜单信息
//            for (ItemCat twoItemCat : twoList) {
//                int twoId = twoItemCat.getId();
//                queryWrapper.clear();
//                queryWrapper.eq("parent_id", twoId);
//                List<ItemCat> threeList = itemCatMapper.selectList(queryWrapper);//三级集合
//                //三级数据封装二级数据
//                twoItemCat.setChildren(threeList);
//            }
//            //二级封装刀一级菜单中
//            oneItemCat.setChildren(twoList);
//        }
//
//        return oneList;


        //level是获取菜单级别的
        //(!) 获取数据信息
        Map<Integer, List<ItemCat>> map = getMap();
        if (level == 1) {//只获取一级菜单数据

            return map.get(0);

        }

        if (level == 2) { //获取1-2级数据

            return getTwoList(map);

        }

        //获取1-3级数据
        return getThreeList(map);

    }

    /**
     * 有些数据应该提前填充
     *
     * @param itemCat
     */
    @Override
    @Transactional //事务注解
    public void saveItemCat(ItemCat itemCat) {
        itemCat.setStatus(true);
//                .setCreated(new Date())
//                .setUpdated(itemCat.getCreated());

        itemCatMapper.insert(itemCat);
    }

    /**
     * 判断是否为3级 如果是，则直接删除
     * 判断是否为2级 先删除3级再删除2级
     * 如果是1级，先查询二级再删除三级/二级/一级
     *
     * @param itemCat
     */
    @Override
    @Transactional
    public void deleteItemCat(ItemCat itemCat) {
        if (itemCat.getLevel() == 3) {
            itemCatMapper.deleteById(itemCat.getId());
        }
        if (itemCat.getLevel() == 2) {
            int twoId = itemCat.getId();
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id", twoId);
            //删除的是三级数据
            itemCatMapper.delete(queryWrapper);
            //再删除自己
            itemCatMapper.deleteById(twoId);
        }

        //只剩下一级菜单
        // 应该先查询二级id
        int oneId = itemCat.getId();
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", oneId);
        List<Object> twoIdList = itemCatMapper.selectObjs(queryWrapper);

        if (twoIdList.size() == 0) {
            //如果没有二级数据，则直接删除一级信息
            itemCatMapper.deleteById(oneId);
        } else {
            //有二级，可以删除三级
            queryWrapper.clear();
            queryWrapper.in("parent_id", twoIdList)
                    .or()
                    .in("id", twoIdList)
                    .or()
                    .eq("id", oneId);
            itemCatMapper.delete(queryWrapper);
        }
    }

    private List<ItemCat> getThreeList(Map<Integer, List<ItemCat>> map) {
        //获取一级二级数据
        List<ItemCat> oneList = getTwoList(map);
        for (ItemCat oneItemCat : oneList) {
            List<ItemCat> twoList = oneItemCat.getChildren();

            if (twoList == null) {
                //说明该一级没有二级数据，跳过本次循环
                continue;
            }

            for (ItemCat twoItemCat : twoList) {
                //查询三级数据，使用二级ID
                int key = twoItemCat.getId();
                List<ItemCat> threeList = map.get(key);
                twoItemCat.setChildren(threeList);
            }
        }

        return oneList;
    }

    //获取一级和二级集合
    private List<ItemCat> getTwoList(Map<Integer, List<ItemCat>> map) {
        List<ItemCat> oneList = map.get(0); //一级集合
        for (ItemCat itemCat : oneList) {
            //根据一级ud查询二级集合
            int key = itemCat.getId(); //id map中的key
            List<ItemCat> twoList = map.get(key);
            //二级数据封装到一级当中
            itemCat.setChildren(twoList);
        }

        return oneList;
    }
}
