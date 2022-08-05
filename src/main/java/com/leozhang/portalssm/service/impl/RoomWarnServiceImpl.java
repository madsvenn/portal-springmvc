package com.leozhang.portalssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.Room;
import com.leozhang.portalssm.entity.RoomWarn;
import com.leozhang.portalssm.entity.RoomWarnExample;
import com.leozhang.portalssm.mapper.RoomMapper;
import com.leozhang.portalssm.mapper.RoomWarnMapper;
import com.leozhang.portalssm.service.RoomWarnService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomWarnServiceImpl implements RoomWarnService {

    @Autowired
    private RoomWarnMapper roomWarnMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Result selectListForPage(int pno, int psize, String sortField, String sortType) {
        Page<RoomWarn> p = PageHelper.startPage(pno,psize);
        RoomWarnExample example = new RoomWarnExample();
        RoomWarnExample.Criteria criteria = example.createCriteria();
        if (sortField!=null && !sortField.equals("")){
            example.setOrderByClause(sortField+" "+sortType);
        }
        List<RoomWarn> list = roomWarnMapper.selectByExample(example);
        List<Room> roomList = roomMapper.selectByExample(null);
        List<Long> roomId = new ArrayList<>();
        for (RoomWarn roomWarn : list) {
            roomId.add(roomWarn.getRoomId());
        }
        /**
         * 这个代码块主要是查询机房是否有告警单，如果没有就new一个
         */
        roomList.forEach(x->{
            if (!roomId.contains(x.getId())){
                RoomWarn warn = new RoomWarn();
                warn.setRoomId(x.getId());
                warn.setId(list.get(list.size()-1).getId()+1);
                list.add(warn);
            }
        });
        return Result.end(200,list,"good",p.getTotal());
    }

    @Override
    public RoomWarn getRoomWarnByRoomId(Long roomId) {
        RoomWarnExample example = new RoomWarnExample();
        RoomWarnExample.Criteria criteria = example.createCriteria();
        criteria.andRoomIdEqualTo(roomId);
        List<RoomWarn> list = roomWarnMapper.selectByExample(example);
        RoomWarn roomWarn = null;
        if (list.size()>0){
            roomWarn = list.get(0);
        }else {
            roomWarn = new RoomWarn();
            roomWarn.setRoomId(roomId);
            roomWarnMapper.insert(roomWarn);
            RoomWarnExample roomWarnExample1 = new RoomWarnExample();
            RoomWarnExample.Criteria criteria1 = example.createCriteria();
            criteria.andRoomIdEqualTo(roomId);
            List<RoomWarn> list1 = roomWarnMapper.selectByExample(example);
            roomWarn = list1.get(0);
        }
        return roomWarn;
    }

    @Override
    public void update(RoomWarn roomWarn) {
        roomWarnMapper.updateByPrimaryKeySelective(roomWarn);
    }
}
