package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.Dept;
import com.leozhang.portalssm.util.Result;

public interface DeptService {
    Result selectDeptByNameResult(int pno, int psize,Long id,Long pid, String name, String sortField, String sortType);

    void insertDept(Dept dept);

    @Deprecated
    void deleteData(Long id);

    Dept selectById(Long id);

    void updateByDept(Dept dept);

    void deleteDeptById(Long id);

    Result getDeptListById(Long pid);
}
