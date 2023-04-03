package com.customerwebsite.customer.website.Repositories;

import com.customerwebsite.customer.website.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
