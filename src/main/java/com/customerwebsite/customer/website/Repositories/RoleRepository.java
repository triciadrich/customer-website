package com.customerwebsite.customer.website.Repositories;

import com.customerwebsite.customer.website.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role, Long> {
}
