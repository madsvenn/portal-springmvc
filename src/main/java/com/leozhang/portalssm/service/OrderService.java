package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Order;
import com.leozhang.portalssm.util.Result;

import javax.servlet.http.HttpSession;

public interface OrderService {

    Result selectListForPage(int pno, int psize, Long orderStatusId, Long publicUserId, Long targetUserId, String sortField, String sortType);

    void insert(Order order, HttpSession session);

    Order selectById(Long id);

    void updateByKey(Order order);

    void deleteById(Long id);
}
