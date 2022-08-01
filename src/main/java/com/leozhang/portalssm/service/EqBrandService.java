package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.EquipmentBrand;
import com.leozhang.portalssm.util.Result;
import org.springframework.stereotype.Service;


public interface EqBrandService {
    Result getListForPage(int pno, int psize, String bName, String sortField, String sortType);

    EquipmentBrand selectById(Long id);

    void insertData(EquipmentBrand equipmentBrand);

    void updateData(EquipmentBrand equipmentBrand);

    void deleteData(Long id);
}
