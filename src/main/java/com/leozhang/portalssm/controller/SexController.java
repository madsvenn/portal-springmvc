package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.service.SexService;
import com.leozhang.portalssm.service.impl.SexServiceImpl;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiresPermissions("permission:query")
@Controller
@RequestMapping("/sex")
public class SexController {

    @Autowired
    private SexService sexService;

    @RequestMapping("/list")
    public String sexList(){

        return "type/sex/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result sexListPage(@RequestParam(value = "pno",defaultValue = "1")int pno,
                              @RequestParam(value = "psize",defaultValue = "10")int psize,
                              @RequestParam(value = "sexName",defaultValue = "")String sexName,
                              @RequestParam(value = "sortField",defaultValue = "")String sortField,
                              @RequestParam(value = "sortType",defaultValue = "")String sortType){

        return sexService.getListForPage(pno,psize,sexName,sortField,sortType);
    }

}
