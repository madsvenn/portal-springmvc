package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Sex;
import com.leozhang.portalssm.util.Result;

public interface SexService {

    Result getListForPage(int pno, int psize, String sexName, String sortField, String sortType);
    void insertSex(Sex sex);

    Sex selectById(Long id);

    void updateSex(Sex sex);

    void deleteSexById(Long id);
}
