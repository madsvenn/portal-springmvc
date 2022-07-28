package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.service.AssetTypeService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

}
