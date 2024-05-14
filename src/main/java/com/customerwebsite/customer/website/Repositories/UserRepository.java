package com.customerwebsite.customer.website.Repositories;

import com.customerwebsite.customer.website.Models.CustomUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUserDetails, Long> {
    CustomUserDetails findByUsername(String username);
}
