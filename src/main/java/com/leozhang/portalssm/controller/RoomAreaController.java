package com.leozhang.portalssm.controller;


import com.leozhang.portalssm.entity.RoomArea;
import com.leozhang.portalssm.service.RoomAreaService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiresPermissions(value = {"permission:query"})
@RequestMapping("/room_area")
@Controller
public class RoomAreaController {

    @Autowired
    private RoomAreaService roomAreaService;


    @RequestMapping("/list")
    public String List(){

        return "room/area/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result ListPage(@RequestParam(value = "pno",defaultValue = "1")int pno,
                              @RequestParam(value = "psize",defaultValue = "10")int psize,
                              @RequestParam(value = "areaName",defaultValue = "")String areaName,
                              @RequestParam(value = "sortField",defaultValue = "")String sortField,
                              @RequestParam(value = "sortType",defaultValue = "")String sortType){

        return roomAreaService.getListForPage(pno,psize,areaName,sortField,sortType);
    }


    @RequestMapping("/add/page")
    public String addPage(){
        return "room/area/add";
    }

    @RequestMapping("/add")
    public String Add(RoomArea roomArea){
        roomAreaService.insert(roomArea);
        return "redirect:/room_area/list";
    }

    @RequestMapping("/edit/page")
    public String EditForPage(Long id, Model model){
        RoomArea roomArea = roomAreaService.selectById(id);
        //将sex存在mode视图中
        model.addAttribute("formData",roomArea);
        return "room/area/edit";
    }

    @RequestMapping("/edit")
    public String Edit(RoomArea roomArea){
        roomAreaService.update(roomArea);
        return "redirect:/room_area/list";
    }

    @RequestMapping("/delete")
    public String Delete(@RequestParam("id")Long id){
        roomAreaService.deleteById(id);
        return "redirect:/room_area/list";
    }
}
