package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.Dept;
import com.leozhang.portalssm.entity.DeptExample;
import com.leozhang.portalssm.mapper.DeptMapper;
import com.leozhang.portalssm.service.DeptService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Result selectDeptByNameResult(int pno, int psize, String name, String sortField, String sortType) {

        Page<Dept> deptPage = PageHelper.startPage(pno,psize);
        DeptExample example = new DeptExample();
        DeptExample.Criteria criteria = example.createCriteria();
        if (name != null && !name.trim().equals("")) {
            criteria.andNameLike("%"+name+"%");
        }


        return null;
    }
}
