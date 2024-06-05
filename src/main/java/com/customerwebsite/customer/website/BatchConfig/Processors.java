package com.customerwebsite.customer.website.BatchConfig;

import com.customerwebsite.customer.website.Models.CustomUserDetails;
import com.customerwebsite.customer.website.Models.Customer;
import com.customerwebsite.customer.website.Models.Role;
import com.customerwebsite.customer.website.Models.Snowboard;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


public interface Processors {
@Component
    class BoardProcessor implements ItemProcessor<Snowboard, Snowboard> {

     @Override
        public Snowboard process(Snowboard snowboard) {
            snowboard.setId(snowboard.getId());
            snowboard.setBrand(snowboard.getBrand());
            snowboard.setType(snowboard.getType());
            snowboard.setName(snowboard.getName());
            snowboard.setCustomer(snowboard.getCustomer());
            return snowboard;
        }
    }

@Component
    class CustomerProcessor implements ItemProcessor<Customer, Customer> {
        @Override
        public Customer process(Customer customer) {
            customer.setId(customer.getId());
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
            role.setId(role.getId());
            role.setRole(role.getRole());
            role.setId(role.getId());
            return role;
        }
    }

@Component
    class UserProcessor implements ItemProcessor<CustomUserDetails, CustomUserDetails>{

        @Override
        public CustomUserDetails process(CustomUserDetails customUserDetails){
            customUserDetails.setId(customUserDetails.getId());
            customUserDetails.setCustomer(customUserDetails.getCustomer());
            customUserDetails.setEnabled(customUserDetails.isEnabled());
            customUserDetails.setAuthorities(customUserDetails.getAuthorities());
            customUserDetails.setPassword(customUserDetails.getPassword());
            customUserDetails.setUsername(customUserDetails.getUsername());
            customUserDetails.setAccountNonExpired(customUserDetails.isAccountNonExpired());
            customUserDetails.setAccountNonLocked(customUserDetails.isAccountNonLocked());
            customUserDetails.setCredentialsNonExpired(customUserDetails.isCredentialsNonExpired());


            return customUserDetails;
        }
    }
}
