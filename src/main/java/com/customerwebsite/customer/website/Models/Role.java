package com.customerwebsite.customer.website.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;

    public Role(Roles role) {
        this.role = role;
    }

    @JsonIgnore
    public String getAuthority() {
        return role.name();
    }

    public enum Roles {
        USER_ROLE,
        ADMIN_ROLE
    }

}
