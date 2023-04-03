package com.customerwebsite.customer.website.Controllers;

import com.customerwebsite.customer.website.Models.Customer;
import com.customerwebsite.customer.website.Models.Snowboard;
import com.customerwebsite.customer.website.Services.CustomerService;
import com.customerwebsite.customer.website.Services.SnowboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SnowboardController {
    @Autowired
    SnowboardService snowboardService;

    @Autowired
    CustomerService customerService;
    @GetMapping("/snowboards")
    public String viewSnowboards(@ModelAttribute Snowboard snowboard, Model model){
        List<Snowboard> snowboardList = snowboardService.getAllSnowboards();
        model.addAttribute("snowboardList", snowboardList);
        return "snowboards";
    }

    @PostMapping("/addSnowboard")
    public String addSnowboard(@ModelAttribute("snowboard") Snowboard snowboard,Model Model){
        snowboardService.createNewSnowboard(snowboard);
        return "redirect:/snowboards";
    }

    @RequestMapping ("/deleteSnowboard/{id}")
    public String deleteSnowboard(@PathVariable("id") Long id, Model model){
        this.snowboardService.deleteSnowboardById(id);
        return"redirect:/snowboards";
    }

    @PostMapping("/addCustToSnow/{customerId}")
    public String addCustToSnow(@PathVariable("customerId")Long id, @RequestParam("snowboardId")Long snowId){
        Customer customer = this.customerService.getCustomer(id);
        Snowboard snowboard = snowboardService.getSnowboardById(snowId);
        this.snowboardService.addCustomerToSnowboard(customer,snowboard);
        return "redirect:/";
    }





}
