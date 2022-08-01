package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.EquipmentBrand;
import com.leozhang.portalssm.entity.EquipmentStatus;
import com.leozhang.portalssm.service.EqStatusService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/eqStatus")
public class EqStatusController {

    @Autowired
    EqStatusService eqStatusService;

    @RequestMapping("/list")
    public String eqStatusList(){
        return "type/eqStatus/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result eqBrandListForPage(@RequestParam(value = "pno",defaultValue = "1")int pno,
                                     @RequestParam(value = "psize",defaultValue = "10")int psize,
                                     @RequestParam(value = "statusName",defaultValue = "")String statusName,
                                     @RequestParam(value = "sortField",defaultValue = "")String sortField,
                                     @RequestParam(value = "sortType",defaultValue = "")String sortType){

        return eqStatusService.getListForPage(pno,psize,statusName,sortField,sortType);
    }

    @RequestMapping("/add/page")
    public String addPage(){
        return "type/eqStatus/add";
    }

    @RequestMapping("/add")
    public String addeqStatus(EquipmentStatus equipmentStatus){
        eqStatusService.insertData(equipmentStatus);
        return "redirect:/eqStatus/list";
    }

    @RequestMapping("/edit")
    public String editStatus(EquipmentStatus equipmentStatus){
        eqStatusService.updateData(equipmentStatus);
        return "redirect:/eqStatus/list";
    }

    @RequestMapping("/edit/page")
    public String editPage(Long id, Model model){
        EquipmentStatus equipmentStatus = eqStatusService.selectById(id);
        model.addAttribute("formData",equipmentStatus);
        return "type/eqStatus/edit";
    }

    @RequestMapping("/delete")
    public String deleteData(@RequestParam("id")Long id){
        eqStatusService.deleteData(id);
        return "redirect:/eqStatus/list";
    }
}
