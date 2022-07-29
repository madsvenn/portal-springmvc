package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.service.DeptService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/dept")
@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequiresPermissions("permission:query")
    @RequestMapping("/list")
    public String deptList(Long pid, Model model){
        model.addAttribute("pid",pid);
        return "staff/dept/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result deptListForPage(@RequestParam(value = "pno",defaultValue = "1")int pno,
                                  @RequestParam(value = "psize",defaultValue = "10")int psize,
                                  @RequestParam(value = "name",defaultValue = "")String name,
                                  @RequestParam(value = "sortField",defaultValue = "")String sortField,
                                  @RequestParam(value = "sortType",defaultValue = "")String sortType){

        return deptService.selectDeptByNameResult(pno,psize,name,sortField,sortType);
    }

}
