package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.RoomStatus;
import com.leozhang.portalssm.service.RoomStatusService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/room_status")
public class RoomStatusController {

    @Autowired
    private RoomStatusService roomStatusService;

    @RequestMapping("/list")
    public String list(){
        return "type/roomStatus/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result listForPage(@RequestParam(value = "pno",defaultValue = "1")int pno,
                              @RequestParam(value = "psize",defaultValue = "10")int psize,
                              @RequestParam(value = "roomName",defaultValue = "")String roomName,
                              @RequestParam(value = "sortField",defaultValue = "")String sortField,
                              @RequestParam(value = "sortType",defaultValue = "")String sortType){

        List<RoomStatus> list = roomStatusService.selectAll(roomName, sortField, sortType);
        return Result.end(200,list,"good",3);
    }
}
