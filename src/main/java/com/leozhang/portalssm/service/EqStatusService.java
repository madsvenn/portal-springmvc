package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.EquipmentBrand;
import com.leozhang.portalssm.entity.EquipmentStatus;
import com.leozhang.portalssm.util.Result;
import org.springframework.stereotype.Service;


public interface EqStatusService {

    Result getListForPage(int pno, int psize, String sName, String sortField, String sortType);

    EquipmentStatus selectById(Long id);

    void insertData(EquipmentStatus equipmentStatus);

    void updateData(EquipmentStatus equipmentStatus);

    void deleteData(Long id);
}
