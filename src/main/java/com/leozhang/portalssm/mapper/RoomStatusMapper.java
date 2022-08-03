package com.leozhang.portalssm.mapper;

import com.leozhang.portalssm.entity.RoomStatus;
import com.leozhang.portalssm.entity.RoomStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomStatusMapper {
    long countByExample(RoomStatusExample example);

    int deleteByExample(RoomStatusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoomStatus record);

    int insertSelective(RoomStatus record);

    List<RoomStatus> selectByExample(RoomStatusExample example);

    RoomStatus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoomStatus record, @Param("example") RoomStatusExample example);

    int updateByExample(@Param("record") RoomStatus record, @Param("example") RoomStatusExample example);

    int updateByPrimaryKeySelective(RoomStatus record);

    int updateByPrimaryKey(RoomStatus record);
}