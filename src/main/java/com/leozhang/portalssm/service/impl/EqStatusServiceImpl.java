package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.EquipmentBrand;
import com.leozhang.portalssm.entity.EquipmentBrandExample;
import com.leozhang.portalssm.entity.EquipmentStatus;
import com.leozhang.portalssm.entity.EquipmentStatusExample;
import com.leozhang.portalssm.mapper.EquipmentStatusMapper;
import com.leozhang.portalssm.service.EqStatusService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EqStatusServiceImpl implements EqStatusService {

    @Autowired
    private EquipmentStatusMapper equipmentStatusMapper;

    @Override
    public Result getListForPage(int pno, int psize, String sName, String sortField, String sortType) {
        //开启分页查询模式
        Page<EquipmentStatus> page = PageHelper.startPage(pno,psize);
        //创建查询的对象
        EquipmentStatusExample example = new EquipmentStatusExample();
        EquipmentStatusExample.Criteria criteria = example.createCriteria();
        if (sName.trim().length()>0){
            criteria.andStatusNameLike("%"+sName+"%");
        }
        //添加排序条件
        if (sortField.trim().length()>0){
            example.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<EquipmentStatus> list = equipmentStatusMapper.selectByExample(example);
        return Result.end(200,list,"查询成功",page.getTotal());
    }

    @Override
    public EquipmentStatus selectById(Long id) {
        return equipmentStatusMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertData(EquipmentStatus equipmentStatus) {
        equipmentStatusMapper.insert(equipmentStatus);
    }

    @Override
    public void updateData(EquipmentStatus equipmentStatus) {
        equipmentStatusMapper.updateByPrimaryKeySelective(equipmentStatus);
    }

    @Override
    public void deleteData(Long id) {
        equipmentStatusMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<EquipmentStatus> selectAll() {
        return equipmentStatusMapper.selectByExample(null);
    }
}
