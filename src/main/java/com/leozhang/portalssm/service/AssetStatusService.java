package com.leozhang.portalssm.service;

import com.leozhang.portalssm.util.Result;

public interface AssetStatusService {

    Result getListForPage(int pno,int psize,String assetStatusName,String sortField,String sortType);
}
