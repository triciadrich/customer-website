package com.customerwebsite.customer.website.Controllers;

import com.customerwebsite.customer.website.Models.CustomUserDetails;
import com.customerwebsite.customer.website.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;


    @GetMapping("/register")
    public String registerForm(Model model){

        model.addAttribute("user",new CustomUserDetails());
        return"register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user")CustomUserDetails userDetails){
        userService.createNewUser(userDetails);

        return "customer-view";
    }
}
