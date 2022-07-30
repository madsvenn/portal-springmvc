package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.AssetType;
import com.leozhang.portalssm.util.Result;

public interface AssetTypeService {

    Result assetTypeService(int pno,int psize,String assetTypeName,String sortField,String sortType);

    AssetType selectById(Long id);

    void insertData(AssetType assetType);

    void updatedata(AssetType assetType);

    void deleteData(Long id);
}
