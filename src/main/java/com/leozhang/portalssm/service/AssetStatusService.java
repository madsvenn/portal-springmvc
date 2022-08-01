package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.AssetStatus;
import com.leozhang.portalssm.entity.Sex;
import com.leozhang.portalssm.util.Result;

public interface AssetStatusService {

    Result getListForPage(int pno,int psize,String assetStatusName,String sortField,String sortType);

    AssetStatus selectById(Long id);

    void insertData(AssetStatus assetStatus);

    void updatedata(AssetStatus assetStatus);

    void deleteData(Long id);

}
