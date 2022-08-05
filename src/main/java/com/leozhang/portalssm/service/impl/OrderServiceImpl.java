package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.Order;
import com.leozhang.portalssm.entity.OrderExample;
import com.leozhang.portalssm.entity.User;
import com.leozhang.portalssm.mapper.OrderMapper;
import com.leozhang.portalssm.service.OrderService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public Result selectListForPage(int pno, int psize, Long orderStatusId, Long publicUserId,
                                    Long targetUserId, String sortField, String sortType) {
        Page<Order> p = PageHelper.startPage(pno,psize);
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        if (orderStatusId != null){
            criteria.andOrderStatusIdEqualTo(orderStatusId);
        }
        if (publicUserId != null){
            criteria.andPublicUserIdEqualTo(publicUserId);
        }
        if (targetUserId != null){
            criteria.andTargetUserIdEqualTo(targetUserId);
        }
        if (sortField!=null && !sortField.trim().equals("")){
            example.setOrderByClause(sortField+" "+sortType);
        }
        List<Order> list = orderMapper.selectAllByExample(example);
        return Result.end(200,list,"good",p.getTotal());

    }

    @Override
    public void insert(Order order, HttpSession session) {

        order.setOrderStatusId(1L);
        User user = (User) session.getAttribute("userInfo");
        order.setPublicUserId(user.getId());
        order.setPublicUsername(user.getUsername());
        order.setInsertTime(new Date());
        orderMapper.insert(order);
    }

    @Override
    public Order selectById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByKey(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public void deleteById(Long id) {
        orderMapper.deleteByPrimaryKey(id);
    }

}
