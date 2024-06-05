package com.customerwebsite.customer.website.BatchConfig;

import com.customerwebsite.customer.website.Models.CustomUserDetails;
import com.customerwebsite.customer.website.Models.Customer;
import com.customerwebsite.customer.website.Models.Role;
import com.customerwebsite.customer.website.Models.Snowboard;
import com.customerwebsite.customer.website.Repositories.CustomerRepository;
import com.customerwebsite.customer.website.Repositories.RoleRepository;
import com.customerwebsite.customer.website.Repositories.SnowboardRepository;
import com.customerwebsite.customer.website.Repositories.UserRepository;
import io.micrometer.common.lang.NonNull;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

public interface Writers {
    @Component
     class BoardWriter implements ItemWriter<Snowboard> {
        @Autowired
        private SnowboardRepository snowboardRepository;

        @Value("${sleepTime}")
        private Integer SLEEP_TIME;

        @Override
        public void write(@NonNull Chunk<? extends Snowboard> snowboards) throws InterruptedException{
            snowboardRepository.saveAll(snowboards);
            Thread.sleep(SLEEP_TIME);
            System.out.println("Saved snowboards: " + snowboards);
        }


    }

    @Component
    class UserWriter implements ItemWriter<CustomUserDetails> {
        @Autowired
        private UserRepository userRepository;

        @Value("${sleepTime}")
        private Integer SLEEP_TIME;

        @Override
        public void write(@NonNull Chunk<? extends CustomUserDetails> users) throws InterruptedException{
            userRepository.saveAll(users);
            Thread.sleep(SLEEP_TIME);
            System.out.println("Saved snowboards: " + users);
        }


    }

    @Component
    class RoleWriter implements ItemWriter<Role> {
        @Autowired
        private RoleRepository roleRepository;

        @Value("${sleepTime}")
        private Integer SLEEP_TIME;

        @Override
        public void write(@NonNull Chunk<? extends Role> roles) throws InterruptedException{
            roleRepository.saveAll(roles);
            Thread.sleep(SLEEP_TIME);
            System.out.println("Saved snowboards: " + roles);
        }


    }

    @Component
    class CustomerWriter implements ItemWriter<Customer> {
        @Autowired
        private CustomerRepository customerRepository;

        @Value("${sleepTime}")
        private Integer SLEEP_TIME;

        @Override
        public void write(@NonNull Chunk<? extends Customer> customers) throws InterruptedException{
            customerRepository.saveAll(customers);
            Thread.sleep(SLEEP_TIME);
            System.out.println("Saved snowboards: " + customers);
        }


    }
}
