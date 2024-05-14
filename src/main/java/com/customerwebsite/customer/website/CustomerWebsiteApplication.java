package com.customerwebsite.customer.website;

import com.customerwebsite.customer.website.Models.CustomUserDetails;
import com.customerwebsite.customer.website.Models.Customer;
import com.customerwebsite.customer.website.Models.Role;
import com.customerwebsite.customer.website.Repositories.RoleRepository;
import com.customerwebsite.customer.website.Repositories.UserRepository;
import com.customerwebsite.customer.website.Services.CustomerService;
import com.customerwebsite.customer.website.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static com.customerwebsite.customer.website.Models.Role.Roles.ADMIN_ROLE;
import static com.customerwebsite.customer.website.Models.Role.Roles.valueOf;

@SpringBootApplication
public class CustomerWebsiteApplication  {

	public static void main(String[] args) {
		SpringApplication.run(CustomerWebsiteApplication.class, args);
	}
	@Autowired
	PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner loadInitialData(UserRepository userRepository, UserService userService,RoleRepository roleRepository) {
		return (args) -> {

//			CustomUserDetails user2 = CustomUserDetails.builder()
//					.username("IamADmin2")
//					.password(passwordEncoder.encode("1234"))
//					.authorities(Collections.singletonList(new Role(ADMIN_ROLE)))
//					.customer(Customer.builder()
//							.emailAddress("admin@email.com")
//							.address("123 somewhere")
//							.fullName("Admin")
//							.age(30)
//							.build())
//					.build();
//			userRepository.save(user2);

		};
	}

}
