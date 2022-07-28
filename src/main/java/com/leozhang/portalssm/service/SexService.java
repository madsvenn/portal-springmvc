package com.leozhang.portalssm.service;

import com.leozhang.portalssm.util.Result;

public interface SexService {

    Result getListForPage(int pno, int psize, String sexName, String sortField, String sortType);
}
