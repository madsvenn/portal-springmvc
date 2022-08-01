package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.EquipmentBrand;
import com.leozhang.portalssm.service.EqBrandService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/eqBrand")
public class EqBrandController {

    @Autowired
    EqBrandService eqBrandService;

    @RequestMapping("/list")
    public String eqBrandList(){
        return "type/eqBrand/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result eqBrandListForPage(@RequestParam(value = "pno",defaultValue = "1")int pno,
                                     @RequestParam(value = "psize",defaultValue = "10")int psize,
                                     @RequestParam(value = "brandName",defaultValue = "")String brandName,
                                     @RequestParam(value = "sortField",defaultValue = "")String sortField,
                                     @RequestParam(value = "sortType",defaultValue = "")String sortType){

        return eqBrandService.getListForPage(pno,psize,brandName,sortField,sortType);
    }

    @RequestMapping("/add/page")
    public String addPage(){
        return "type/eqBrand/add";
    }

    @RequestMapping("/add")
    public String addeqBrand(EquipmentBrand equipmentBrand){
        eqBrandService.insertData(equipmentBrand);
        return "redirect:/eqBrand/list";
    }

    @RequestMapping("/edit")
    public String editBrand(EquipmentBrand equipmentBrand){
        eqBrandService.updateData(equipmentBrand);
        return "redirect:/eqBrand/list";
    }

    @RequestMapping("/edit/page")
    public String editPage(Long id, Model model){
        EquipmentBrand equipmentBrand = eqBrandService.selectById(id);
        model.addAttribute("formData",equipmentBrand);
        return "type/eqBrand/edit";
    }

    @RequestMapping("/delete")
    public String deleteData(@RequestParam("id")Long id){
        eqBrandService.deleteData(id);
        return "redirect:/eqBrand/list";
    }
}
