package com.customerwebsite.customer.website.Controllers;

import com.customerwebsite.customer.website.Models.Customer;
import com.customerwebsite.customer.website.Models.Snowboard;
import com.customerwebsite.customer.website.Services.CustomerService;
import com.customerwebsite.customer.website.Services.CustomerServiceImpl;
import com.customerwebsite.customer.website.Services.SnowboardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    SnowboardService snowboardService;


    @GetMapping("/")
    public String viewHomePage() {

        return "index";
    }

    @GetMapping("/customer-list")
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    public String viewCustomerList(@ModelAttribute("snowboard") Snowboard snowboard, Model model){
        // Here you call the service to retrieve all the customers
        final List<Customer> customerList = customerService.getAllCustomers();
        final List<Snowboard> snowboardList = snowboardService.getAvailableSnowboards();
        // Once the customers are retrieved, you can store them in model and return it to the view
        model.addAttribute("customerList", customerList);
        model.addAttribute("snowboardList", snowboardList);
        model.addAttribute("snowboard", snowboard);
        return"customer-list";
    }


    @GetMapping("/new")
    public String showNewCustomerPage(Model model) {
        // Here a new (empty) Customer is created and then sent to the view
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "new-customer";
    }

    @PostMapping(value = "/save")
    // As the Model is received back from the view, @ModelAttribute
    // creates a Customer based on the object you collected from
    // the HTML page above
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    // The path variable "id" is used to pull a customer from the database
    public ModelAndView showEditCustomerPage(@PathVariable(name = "id") Long id) {
        // Since the previous methods use Model, this one uses ModelAndView
        // to get some experience using both. Model is more common these days,
        // but ModelAndView accomplishes the same thing and can be useful in
        // certain circumstances. The view name is passed to the constructor.
        ModelAndView mav = new ModelAndView("edit-customer");
        Customer customer = customerService.getCustomer(id);
        mav.addObject("customer", customer);
        return mav;
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable(name = "id") Long id, @ModelAttribute("customer") Customer customer, Model model) {
        if (!id.equals(customer.getId())) {
            model.addAttribute("message",
                    "Cannot update, customer id " + customer.getId()
                            + " doesn't match id to be updated: " + id + ".");
            return "error-page";
        }
        customerService.saveCustomer(customer);
        return "redirect:/";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable(name = "id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/login";

    }

    @RequestMapping("/customerView")
    public String customerView(Model model, Customer customer){
        model.addAttribute("customer", customer);

        return "customer-view";
    }
}
