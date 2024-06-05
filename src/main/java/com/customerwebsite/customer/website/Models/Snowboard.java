package com.customerwebsite.customer.website.Models;

import com.customerwebsite.customer.website.Services.CustomerService;
import jakarta.persistence.*;
import lombok.*;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="snowboards")
@Getter
@Setter
@ToString
public class Snowboard {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String brand;
    private String name;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
