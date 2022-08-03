package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.Room;
import com.leozhang.portalssm.entity.RoomExample;
import com.leozhang.portalssm.mapper.RoomMapper;
import com.leozhang.portalssm.service.RoomService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Result getListForPage(int pno, int psize, String name, Long areaId,String phone, String sortField, String sortType) {

        Page<Room> p = PageHelper.startPage(pno,psize);
        RoomExample example = new RoomExample();
        RoomExample.Criteria criteria = example.createCriteria();
        if (name!=null && !name.trim().equals("")){
            criteria.andNameLike("%"+name+"%");
        }
        if (areaId!=null && !areaId.equals("")){
            criteria.andAreaIdEqualTo(areaId);
        }
        if (phone!=null && !phone.trim().equals("")){
            criteria.andPhoneLike("%"+phone+"%");
        }
        if (sortField!=null && !sortField.trim().equals("")){
            example.setOrderByClause(sortField+" "+sortType);
        }
        List<Room> list = roomMapper.selectAllByExample(example);
        return Result.end(200,list,"good",p.getTotal());
    }

    @Override
    public Room selectById(Long id) {
        return roomMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateData(Room room) {
        roomMapper.updateByPrimaryKeySelective(room);
    }
}
