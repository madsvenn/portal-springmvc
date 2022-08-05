package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.OrderStatus;
import com.leozhang.portalssm.util.Result;

import java.util.List;

public interface OrderStatusService {

    Result getListForPage(int pno, int psize, String statusName, String sortField, String sortType);

    OrderStatus selectById(Long id);

    void insertData(OrderStatus orderStatus);

    void updateData(OrderStatus orderStatus);

    void deleteData(Long id);

    List<OrderStatus> selectAll();
}
