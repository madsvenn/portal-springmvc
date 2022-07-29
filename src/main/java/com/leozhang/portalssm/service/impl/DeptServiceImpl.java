package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.Dept;
import com.leozhang.portalssm.entity.DeptExample;
import com.leozhang.portalssm.mapper.DeptMapper;
import com.leozhang.portalssm.service.DeptService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    private static Logger logger= LogManager.getLogger(LogManager.DEFAULT_CONFIGURATION_FILE);

    @Override
    public Result selectDeptByNameResult(int pno, int psize, Long id, Long pid, String name, String sortField, String sortType) {

        Page<Dept> deptPage = PageHelper.startPage(pno,psize);

        DeptExample example = new DeptExample();
        DeptExample.Criteria criteria = example.createCriteria();

        if (id != null){
            criteria.andIdEqualTo(id);
        }else{
            criteria.andPidEqualTo(pid);
        }
        if (name != null && !name.trim().equals("")) {
            criteria.andNameLike("%"+name+"%");
        }
        if (sortField != null && !sortField.trim().equals(""))  {
            example.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<Dept> list = deptMapper.selectByExample(example);

        return Result.end(200,list,"查询成功",deptPage.getTotal());
    }

    @Override
    public void insertDept(Dept dept) {
        deptMapper.insert(dept);
    }
}
