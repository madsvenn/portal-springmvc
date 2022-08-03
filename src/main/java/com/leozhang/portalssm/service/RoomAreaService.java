package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.RoomArea;
import com.leozhang.portalssm.util.Result;

public interface RoomAreaService {

    Result getListForPage(int pno, int psize, String areaName, String sortField, String sortType);
    void insert(RoomArea roomArea);

    RoomArea selectById(Long id);

    void update(RoomArea roomArea);

    void deleteById(Long id);
}
