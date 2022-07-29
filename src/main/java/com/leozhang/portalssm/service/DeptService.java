package com.leozhang.portalssm.service;

import com.leozhang.portalssm.util.Result;

public interface DeptService {
    Result selectDeptByNameResult(int pno, int psize, String name, String sortField, String sortType);
}
