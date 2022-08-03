package com.leozhang.portalssm.service.impl;

import com.leozhang.portalssm.entity.RoomStatus;
import com.leozhang.portalssm.entity.RoomStatusExample;
import com.leozhang.portalssm.mapper.RoomStatusMapper;
import com.leozhang.portalssm.service.RoomStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomStatusServiceImpl implements RoomStatusService {

    @Autowired
    private RoomStatusMapper roomStatusMapper;


    @Override
    public List<RoomStatus> selectAll(String roomName, String sortField, String sortType) {
        RoomStatusExample example = new RoomStatusExample();
        RoomStatusExample.Criteria criteria = example.createCriteria();
        if (roomName!=null && !roomName.trim().equals("")){
            criteria.andRoomNameLike("%"+roomName+"%");
        }
        if (sortField!=null && !sortField.trim().equals("")){
            example.setOrderByClause(sortField+" "+sortType);
        }

        return roomStatusMapper.selectByExample(example);
    }
}
