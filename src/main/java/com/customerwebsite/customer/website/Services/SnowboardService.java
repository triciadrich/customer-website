package com.customerwebsite.customer.website.Services;

import com.customerwebsite.customer.website.Models.Customer;
import com.customerwebsite.customer.website.Models.Snowboard;
import com.customerwebsite.customer.website.Repositories.CustomerRepository;
import com.customerwebsite.customer.website.Repositories.SnowboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SnowboardService {

    @Autowired
    SnowboardRepository snowboardRepository;

     @Autowired
    CustomerRepository customerRepository;
    public List<Snowboard> getAllSnowboards(){

        return snowboardRepository.findAll();
    }

    public List<Snowboard> getAvailableSnowboards(){
        return getAllSnowboards().stream().filter(s-> s.getCustomer() == null)
                .collect(Collectors.toList());
    }

    public Snowboard getSnowboardById(Long id){
        return snowboardRepository.findById(id).orElseThrow(()->new RuntimeException());
    }

    public Snowboard createNewSnowboard(Snowboard snowboard){
        return snowboardRepository.save(snowboard);
    }

    public Snowboard updateSnowboard(Snowboard snowboard){
        return snowboardRepository.save(snowboard);
    }

    public Snowboard deleteSnowboardById(Long id){
        Snowboard snowboard = (Snowboard) getSnowboardById(id);
        snowboardRepository.deleteById(id);
        return snowboard;
    }

    public void addCustomerToSnowboard(Customer customer, Snowboard snowboard){
        customer.setSnowboard(snowboard);
        snowboard.setCustomer(customer);
        this.snowboardRepository.save(snowboard);
        customerRepository.save(customer);

    }

    public Snowboard findByCustomerId(Long id){
        return snowboardRepository.findByCustomer_Id(id);
    }
}
