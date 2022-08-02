package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.entity.EquipmentBrand;
import com.leozhang.portalssm.entity.EquipmentStatus;
import com.leozhang.portalssm.service.EqBrandService;
import com.leozhang.portalssm.service.EqStatusService;
import com.leozhang.portalssm.service.EquipmentService;
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
@RequiresPermissions(value = "permission:query")
@RequestMapping("/eq")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EqBrandService eqBrandService;

    @Autowired
    private EqStatusService eqStatusService;

    @RequiresPermissions(value = "permission:query")
    @RequestMapping("/list")
    public String list(Model model){
        List<EquipmentBrand> equipmentBrandList = eqBrandService.selectAll();
        model.addAttribute("equipmentBrandList",equipmentBrandList);
        List<EquipmentStatus> equipmentStatusList = eqStatusService.selectAll();
        model.addAttribute("equipmentStatusList",equipmentStatusList);
        return "room/equipment/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result listPage(@RequestParam(value = "pno",defaultValue = "1")int pno,
                           @RequestParam(value = "psize",defaultValue = "10")int psize,
                           @RequestParam(value = "name",defaultValue = "")String name,
                           @RequestParam(value = "brandId",required = false)Long brandId,
                           @RequestParam(value = "statusId",required = false)Long statusId,
                           @RequestParam(value = "sortField",defaultValue = "")String sortField,
                           @RequestParam(value = "sortType",defaultValue = "")String sortType){

        return equipmentService.selectListForPage(pno,psize,name,brandId,statusId,sortField,sortType);
    }

    @RequiresPermissions(value = {"permission:insert"})
    @RequestMapping("/add/page")
    public String addPage(Model model){
        List<EquipmentBrand> equipmentBrandList = eqBrandService.selectAll();
        model.addAttribute("equipmentBrandList",equipmentBrandList);
        return "room/equipment/add";
    }


}