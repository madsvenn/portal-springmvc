package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.RoomArea;
import com.leozhang.portalssm.entity.RoomAreaExample;
import com.leozhang.portalssm.mapper.RoomAreaMapper;
import com.leozhang.portalssm.service.RoomAreaService;
import com.leozhang.portalssm.util.ChangeChar;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomAreaServiceImpl implements RoomAreaService {

    @Autowired
    private RoomAreaMapper roomAreaMapper;

    @Override
    public Result getListForPage(int pno, int psize, String areaName, String sortField, String sortType) {
        //开启分页查询模式
        Page<RoomArea> page = PageHelper.startPage(pno,psize);
        //创建查询的对象
        RoomAreaExample example = new RoomAreaExample();
        RoomAreaExample.Criteria criteria = example.createCriteria();
        if (areaName.trim().length()>0){
            criteria.andAreaNameLike("%"+areaName+"%");
        }
        //添加排序条件
        if (sortField.trim().length()>0){
            example.setOrderByClause(ChangeChar.camelToUnderline(sortField,2)+" "+sortType);
        }
        List<RoomArea> list = roomAreaMapper.selectByExample(example);

        return Result.end(200,list,"查询成功",page.getTotal());
    }

    @Override
    public void insert(RoomArea roomArea) {
        roomAreaMapper.insert(roomArea);
    }

    @Override
    public RoomArea selectById(Long id) {
        return roomAreaMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(RoomArea roomArea) {
        roomAreaMapper.updateByPrimaryKeySelective(roomArea);
    }

    @Override
    public void deleteById(Long id) {
        roomAreaMapper.deleteByPrimaryKey(id);
    }
}
