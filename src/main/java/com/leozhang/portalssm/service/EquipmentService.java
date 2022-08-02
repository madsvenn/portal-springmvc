package com.leozhang.portalssm.service;


import com.leozhang.portalssm.util.Result;

public interface EquipmentService {
    Result selectListForPage(int pno, int psize, String name, Long brandId, Long statusId, String sortField, String sortType);

}
