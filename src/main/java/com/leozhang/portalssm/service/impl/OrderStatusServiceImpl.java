package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.AssetStatus;
import com.leozhang.portalssm.entity.AssetStatusExample;
import com.leozhang.portalssm.entity.OrderStatus;
import com.leozhang.portalssm.entity.OrderStatusExample;
import com.leozhang.portalssm.mapper.OrderStatusMapper;
import com.leozhang.portalssm.service.OrderStatusService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Override
    public Result getListForPage(int pno, int psize, String statusName, String sortField, String sortType) {

        Page<OrderStatus> page = PageHelper.startPage(pno,psize);
        //创建查询的对象
        OrderStatusExample example = new OrderStatusExample();
        OrderStatusExample.Criteria criteria = example.createCriteria();
        if (statusName.trim().length()>0){
            criteria.andStatusNameLike("%"+statusName+"%");
        }
        //添加排序条件
        if (sortField.trim().length()>0){
            example.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<OrderStatus> list = orderStatusMapper.selectByExample(example);

        return Result.end(200,list,"查询成功",page.getTotal());
    }

    @Override
    public OrderStatus selectById(Long id) {
        return orderStatusMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertData(OrderStatus orderStatus) {
        orderStatusMapper.insert(orderStatus);
    }

    @Override
    public void updateData(OrderStatus orderStatus) {
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Override
    public void deleteData(Long id) {
        orderStatusMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<OrderStatus> selectAll() {
        return orderStatusMapper.selectByExample(null);
    }
}
