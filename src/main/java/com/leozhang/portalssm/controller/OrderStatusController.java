package com.leozhang.portalssm.controller;

import com.leozhang.portalssm.entity.AssetStatus;
import com.leozhang.portalssm.entity.OrderStatus;
import com.leozhang.portalssm.service.OrderStatusService;
import com.leozhang.portalssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orderStatus")
public class OrderStatusController {

    @Autowired
    private OrderStatusService orderStatusService;

    @RequestMapping("/list")
    public String statusList(){
        return "type/orderStatus/list";
    }

    @RequestMapping("/list/page")
    @ResponseBody
    public Result statusListForPage(@RequestParam(value = "pno",defaultValue = "1")int pno,
                                         @RequestParam(value = "psize",defaultValue = "10")int psize,
                                         @RequestParam(value = "statusName",defaultValue = "")String statusName,
                                         @RequestParam(value = "sortField",defaultValue = "")String sortField,
                                         @RequestParam(value = "sortType",defaultValue = "")String sortType){

        return orderStatusService.getListForPage(pno,psize,statusName,sortField,sortType);
    }

    @RequestMapping("/add/page")
    public String addPage(){
        return "type/orderStatus/add";
    }

    @RequestMapping("/add")
    public String addStatus(OrderStatus orderStatus){

        orderStatusService.insertData(orderStatus);
        return "redirect:/orderStatus/list";
    }

    @RequestMapping("/edit")
    public String editStatus(OrderStatus orderStatus){
        orderStatusService.updateData(orderStatus);
        return "redirect:/orderStatus/list";
    }

    @RequestMapping("/edit/page")
    public String editPage(Long id, Model model){
        OrderStatus orderStatus = orderStatusService.selectById(id);
        model.addAttribute("formData",orderStatus);
        return "type/orderStatus/edit";
    }

    @RequestMapping("/delete")
    public String deleteData(@RequestParam("id")Long id){
        orderStatusService.deleteData(id);
        return "redirect:/orderStatus/list";
    }
}
