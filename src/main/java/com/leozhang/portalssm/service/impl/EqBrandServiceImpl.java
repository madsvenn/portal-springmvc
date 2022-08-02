package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.EquipmentBrand;
import com.leozhang.portalssm.entity.EquipmentBrandExample;
import com.leozhang.portalssm.mapper.EquipmentBrandMapper;
import com.leozhang.portalssm.service.EqBrandService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EqBrandServiceImpl implements EqBrandService {

    @Autowired
    private EquipmentBrandMapper equipmentBrandMapper;

    @Override
    public Result getListForPage(int pno, int psize, String bName, String sortField, String sortType) {

        //开启分页查询模式
        Page<EquipmentBrand> page = PageHelper.startPage(pno,psize);
        //创建查询的对象
        EquipmentBrandExample example = new EquipmentBrandExample();
        EquipmentBrandExample.Criteria criteria = example.createCriteria();
        if (bName.trim().length()>0){
            criteria.andBrandNameLike("%"+bName+"%");
        }
        //添加排序条件
        if (sortField.trim().length()>0){
            example.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<EquipmentBrand> list = equipmentBrandMapper.selectByExample(example);
        return Result.end(200,list,"查询成功",page.getTotal());
    }

    @Override
    public EquipmentBrand selectById(Long id) {
        return equipmentBrandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void insertData(EquipmentBrand equipmentBrand) {
        equipmentBrandMapper.insert(equipmentBrand);
    }

    @Override
    public void updateData(EquipmentBrand equipmentBrand) {
        equipmentBrandMapper.updateByPrimaryKeySelective(equipmentBrand);
    }

    @Override
    public void deleteData(Long id) {
        equipmentBrandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<EquipmentBrand> selectAll() {
        return equipmentBrandMapper.selectByExample(null);
    }
}
