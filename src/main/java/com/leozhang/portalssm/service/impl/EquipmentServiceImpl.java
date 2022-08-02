package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.entity.EquipmentExample;
import com.leozhang.portalssm.mapper.EquipmentMapper;
import com.leozhang.portalssm.service.EquipmentService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public Result selectListForPage(int pno, int psize, String name, Long brandId, Long statusId, String sortField, String sortType) {

        Page<Equipment> p = PageHelper.startPage(pno,psize);
        EquipmentExample example = new EquipmentExample();
        EquipmentExample.Criteria criteria = example.createCriteria();
        if (name!=null && !name.trim().equals("")){
            criteria.andNameLike("%"+name+"%");
        }
        if (brandId!=null){
            criteria.andBrandIdEqualTo(brandId);
        }
        if (statusId!=null){
            criteria.andStatusIdEqualTo(statusId);
        }
        if (sortField!=null && !sortField.trim().equals("")){
            example.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<Equipment> list = equipmentMapper.selectAllByExample(example);
        return Result.end(200,list,"good",p.getTotal());
    }

    @Override
    public Equipment selectById(Long id) {
        return equipmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insert(Equipment equipment) {
        equipment.setInsertTime(new Date());
        System.out.println(equipment.getInsertTime());
        equipmentMapper.insert(equipment);
    }

    @Override
    public void delete(Long id) {
        equipmentMapper.deleteByPrimaryKey(id);
    }
}
