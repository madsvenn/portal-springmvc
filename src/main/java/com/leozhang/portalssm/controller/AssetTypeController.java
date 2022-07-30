package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.AssetStatus;
import com.leozhang.portalssm.entity.AssetType;
import com.leozhang.portalssm.service.AssetTypeService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/assetType")
public class AssetTypeController {

    @Autowired
    private AssetTypeService typeService;

    @RequestMapping("/list")
    public String assetTypeList(){
        return "type/assetType/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result assetTypeListForPage(@RequestParam(value = "pno",defaultValue = "1")int pno,
                                       @RequestParam(value = "psize",defaultValue = "10")int psize,
                                       @RequestParam(value = "assetTypeName",defaultValue = "")String assetTypeName,
                                       @RequestParam(value = "sortField",defaultValue = "")String sortField,
                                       @RequestParam(value = "sortType",defaultValue = "")String sortType){


        return typeService.assetTypeService(pno, psize, assetTypeName, sortField, sortType);
    }

    @RequestMapping("/add/page")
    public String addPage(){
        return "type/assetType/add";
    }

    @RequestMapping("/add")
    public String addAssetType(AssetType assetType){

        typeService.insertData(assetType);
        return "redirect:/assetType/list";
    }

    @RequestMapping("/edit/page")
    public String editPage(Long id, Model model){
        AssetType assetType = typeService.selectById(id);
        model.addAttribute("formData",assetType);
        return "type/assetType/edit";
    }

    @RequestMapping("/edit")
    public String editAssetType(AssetType assetType){
        typeService.updatedata(assetType);
        return "redirect:/assetType/list";
    }

    @RequestMapping("/delete")
    public String deleteData(@RequestParam("id")Long id){
        typeService.deleteData(id);
        return "redirect:/assetType/list";
    }
}
