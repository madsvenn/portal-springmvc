package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.service.AssetStatusService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/assetStatus")
public class AssetStatusController {
    @Autowired
    private AssetStatusService assetStatusService;

    @RequestMapping("/list")
    public String assetStatusList(){
        return "type/assetStatus/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result assetStatusListForPage(@RequestParam(value = "pno",defaultValue = "1")int pno,
                                         @RequestParam(value = "psize",defaultValue = "10")int psize,
                                         @RequestParam(value = "assetStatusName",defaultValue = "")String assetStatusName,
                                         @RequestParam(value = "sortField",defaultValue = "")String sortField,
                                         @RequestParam(value = "sortType",defaultValue = "")String sortType){

        return assetStatusService.getListForPage(pno,psize,assetStatusName,sortField,sortType);
    }
}
