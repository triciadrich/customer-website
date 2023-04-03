package com.customerwebsite.customer.website.Repositories;

import com.customerwebsite.customer.website.Models.Snowboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnowboardRepository extends JpaRepository<Snowboard, Long> {

    Snowboard findByCustomer_Id(Long id);
}
