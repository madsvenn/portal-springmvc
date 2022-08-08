package com.leozhang.portalssm.controller;
import com.alibaba.druid.sql.dialect.odps.ast.OdpsAddStatisticStatement;
import com.leozhang.portalssm.entity.Equipment;
import com.leozhang.portalssm.entity.Order;
import com.leozhang.portalssm.entity.OrderStatus;
import com.leozhang.portalssm.entity.User;
import com.leozhang.portalssm.service.EquipmentService;
import com.leozhang.portalssm.service.OrderService;
import com.leozhang.portalssm.service.OrderStatusService;
import com.leozhang.portalssm.service.UserService;
import com.leozhang.portalssm.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderStatusService orderStatusService;

    @Autowired
    private UserService userService;

    @Autowired
    private EquipmentService equipmentService;

    @RequiresPermissions(value = {"permission:query"})
    @RequestMapping("/problem/list/single")
    public String problemListSingle(Model model){
        List<OrderStatus> orderStatusList = orderStatusService.selectAll();
        model.addAttribute("orderStatusList",orderStatusList) ;
        return "order/problem/list-single";
    }

    @RequestMapping("/problem/list/page")
    @ResponseBody
    public Result problemListPage(@RequestParam(value = "pno",defaultValue = "1")int pno,
                                  @RequestParam(value = "psize",defaultValue = "10")int psize,
                                  @RequestParam(value = "orderStatusId",required = false)Long orderStatusId,
                                  @RequestParam(value = "publicUserId",required = false)Long publicUserId,
                                  @RequestParam(value = "targetUserId",required = false)Long targetUserId,
                                  @RequestParam(value = "sortField",defaultValue="")String sortField,
                                  @RequestParam(value = "sortType",defaultValue="")String sortType
                                  ,HttpSession session){
        User user = (User) session.getAttribute("userInfo");
        publicUserId = null;
        if (user.getRoleId() != 1) {
            publicUserId = user.getId();
        }
        return orderService.selectListForPage(pno,psize,orderStatusId,publicUserId,targetUserId,sortField,sortType);
    }

    @RequestMapping("/problem/add/page")
    public String addPage(Model model){
        List<User> userList = userService.selectAll();
        List<Equipment> equipmentList = equipmentService.selectAllByNull();
        model.addAttribute("userList",userList);
        model.addAttribute("equipmentList",equipmentList);
        return "order/problem/add";

    }

    @RequiresPermissions(value = {"permission:insert"})
    @RequestMapping("/problem/add")
    public String addData(Order order, HttpSession session){
        orderService.insert(order,session);
        return "redirect:/order/problem/list/single";
    }

    @RequestMapping("/todo/list")
    public String todoList(Model model,HttpSession session){
        List<OrderStatus> orderStatusList = orderStatusService.selectAll();
        User user = (User) session.getAttribute("userInfo");
        model.addAttribute("orderStatusList",orderStatusList);
        model.addAttribute("userInfo",user);
        return "order/todo/list";
    }

    @RequestMapping("/todo/edit/page")
    public String todoEditPage(Long id,Model model){
        Order order = orderService.selectById(id);
        List<OrderStatus> orderStatusList = orderStatusService.selectAll();
        List<Equipment> equipmentList = equipmentService.selectAllByNull();
        List<User> userList = userService.selectAll();
        model.addAttribute("formData",order);
        model.addAttribute("orderStatusList",orderStatusList);
        model.addAttribute("equipmentList",equipmentList);
        model.addAttribute("userList",userList);
        return "order/todo/edit";
    }

    @RequestMapping("/todo/edit")
    public String todoEdit(Order order){
        orderService.updateByKey(order);
        return "redirect:/order/todo/list";
    }

    @RequestMapping("/todo/add/page")
    public String todoAddPage(Model model){
        List<User> userList = userService.selectAll();
        List<Equipment> equipmentList = equipmentService.selectAllByNull();
        model.addAttribute("userList",userList);
        model.addAttribute("equipmentList",equipmentList);
        return "order/todo/add";
    }

    @RequiresPermissions(value = {"permission:insert"})
    @RequestMapping("/todo/add")
    public String addTodoData(Order order, HttpSession session){
        orderService.insert(order,session);
        return "redirect:/order/todo/list";
    }


    @RequestMapping("/problem/edit/page")
    public String orderEdit(Long id,Model model){
        Order order = orderService.selectById(id);
        List<Equipment> equipmentList = equipmentService.selectAllByNull();
        List<User> userList = userService.selectAll();
        List<OrderStatus> orderStatusList = orderStatusService.selectAll();
        model.addAttribute("formData",order);
        model.addAttribute("equipmentList",equipmentList);
        model.addAttribute("userList",userList);
        model.addAttribute("orderStatusList",orderStatusList);
        return "order/problem/edit";
    }

    @RequestMapping("/problem/edit")
    public String orderEdit(Order order){
        orderService.updateByKey(order);
        return "redirect:/order/problem/list/single";
    }


    @RequestMapping("/problem/delete")
    public String deleteOrder(Long id){
        orderService.deleteById(id);
        return "redirect:/order/problem/list/single";
    }

    @RequestMapping("/todo/preview/page")
    public String todoPreview(Long id,Model model){
        Order order = orderService.selectById(id);
        List<Equipment> equipmentList = equipmentService.selectAllByNull();
        List<User> userList = userService.selectAll();
        List<OrderStatus> orderStatusList = orderStatusService.selectAll();
        model.addAttribute("formData",order);
        model.addAttribute("equipmentList",equipmentList);
        model.addAttribute("userList",userList);
        model.addAttribute("orderStatusList",orderStatusList);
        return "order/todo/preview";
    }
}
