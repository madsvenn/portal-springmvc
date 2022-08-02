package com.leozhang.portalssm.service;


import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.util.Result;

public interface EquipmentService {
    Result selectListForPage(int pno, int psize, String name, Long brandId, Long statusId, String sortField, String sortType);

    Equipment selectById(Long id);

    void insert(Equipment equipment);

    void delete(Long id);

    void update(Equipment equipment);
}
