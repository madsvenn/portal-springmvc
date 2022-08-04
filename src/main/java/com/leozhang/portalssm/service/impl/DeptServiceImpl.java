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

import java.util.ArrayList;
import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Deprecated
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

    @Override
    public void deleteData(Long id) {
        deptMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Dept selectById(Long id) {
        return deptMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByDept(Dept dept) {
        deptMapper.updateByPrimaryKeySelective(dept);
    }

    @Override
    public void deleteDeptById(Long id) {
        List<Dept> listAllDept = deptMapper.selectByExample(null);
        List<Long> isDid = new ArrayList<>();
        getDeleteList(listAllDept,isDid,id);
        System.out.println(isDid);
        isDid.forEach(x->{
            deptMapper.deleteByPrimaryKey(x);
        });
    }

    @Override
    public Result getDeptListById(Long pid) {
        DeptExample example = new DeptExample();
        DeptExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);
        List<Dept> list = deptMapper.selectByExample(example);
        return Result.end(200,list,"success",list.size());
    }

    public void getDeleteList(List<Dept> deptList,List<Long> isDid,Long id){
        if (!isDid.contains(id)) isDid.add(id);
        deptList.forEach(x->{
            if (x.getId()==id){
                if (x.getIsLeaf()==1){
                    if (!isDid.contains(x.getId())) isDid.add(x.getId());
                }else {
                    deptList.forEach(y-> {
                        if (y.getPid() == id) {
                            if (!isDid.contains(y.getPid()))isDid.add(id);
                            getDeleteList(deptList, isDid, y.getId());
                        }
                    });

                }
            }
        });
    }

}
