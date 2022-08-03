package com.leozhang.portalssm.service;

import com.leozhang.portalssm.entity.RoomStatus;

import java.util.List;

public interface RoomStatusService {

    List<RoomStatus> selectAll(String roomName,String sortField,String sortType);
}
