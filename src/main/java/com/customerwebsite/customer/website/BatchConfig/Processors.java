package com.customerwebsite.customer.website.BatchConfig;

import com.customerwebsite.customer.website.Models.Customer;
import com.customerwebsite.customer.website.Models.Role;
import com.customerwebsite.customer.website.Models.Snowboard;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

public interface Processors {
    @Component
    class BoardProcessor implements ItemProcessor<Snowboard, Snowboard> {
        @Override
        public Snowboard process(Snowboard snowboard) {
            snowboard.setBrand(snowboard.getBrand());
            snowboard.setType(snowboard.getType());
            snowboard.setName(snowboard.getName());
            snowboard.setCustomer(snowboard.getCustomer());
            return snowboard;
        }
    }

    @Component
    class UserProcessor implements ItemProcessor<Customer, Customer> {
        @Override
        public Customer process(Customer customer) {
            customer.setAddress(customer.getAddress());
            customer.setAge(customer.getAge());
            customer.setSnowboard(customer.getSnowboard());
            customer.setFullName(customer.getFullName());
            customer.setEmailAddress(customer.getEmailAddress());
            return customer;
        }
    }

    @Component
    class RoleProcessor implements ItemProcessor<Role, Role>{

        @Override
        public Role process(Role role){
            role.setRole(role.getRole());
            role.setId(role.getId());
            return role;
        }
    }
}
