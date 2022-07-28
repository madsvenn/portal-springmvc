package com.leozhang.portalssm.service;

import com.leozhang.portalssm.util.Result;

public interface AssetTypeService {

    Result assetTypeService(int pno,int psize,String assetTypeName,String sortField,String sortType);
}
