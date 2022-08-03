package com.leozhang.portalssm.controller;

import com.github.pagehelper.PageHelper;
import com.leozhang.portalssm.entity.*;
import com.leozhang.portalssm.service.RoomAreaService;
import com.leozhang.portalssm.service.RoomService;
import com.leozhang.portalssm.service.RoomStatusService;
import com.leozhang.portalssm.service.UserService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomAreaService roomAreaService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomStatusService roomStatusService;

    @RequiresPermissions(value = {"permission:query"})
    @RequestMapping("/list")
    public String roomList(Model model){
        List<RoomArea> areaList = roomAreaService.selectAll();
        model.addAttribute("areaList",areaList);


        return "room/room/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result roomListPage(@RequestParam(value = "pno",defaultValue = "1")int pno,
                               @RequestParam(value = "psize",defaultValue = "10")int psize,
                               @RequestParam(value = "name",defaultValue = "")String name,
                               @RequestParam(value = "areaId")Long areaId,
                               @RequestParam(value = "phone",defaultValue = "")String phone,
                               @RequestParam(value = "sortField",defaultValue = "")String sortField,
                               @RequestParam(value = "sortType",defaultValue = "")String sortType){

        return roomService.getListForPage(pno, psize, name, areaId, phone, sortField, sortType);
    }

    @RequestMapping("/bind/user/page")
    public String bindUserPage(Long id,Model model){
        List<User> userList = userService.selectAll();
        model.addAttribute("userList",userList);
        Room room = roomService.selectById(id);
        model.addAttribute("formData",room);
        return "room/room/bind-user";
    }

    @RequestMapping("/edit")
    public String roomEdit(Room room){
        roomService.updateData(room);
        return "redirect:/room/list";
    }

    @RequestMapping("/edit/page")
    public String edit(Long id,Model model){
        Room room = roomService.selectById(id);
        model.addAttribute("formData",room);
        List<RoomStatus> statusList = roomStatusService.selectAll(null,null,null);
        model.addAttribute("statusList",statusList);
        List<RoomArea> roomAreaList = roomAreaService.selectAll();
        model.addAttribute("roomAreaList",roomAreaList);
        return "room/room/edit";
    }

}
