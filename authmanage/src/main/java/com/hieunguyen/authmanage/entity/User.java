package com.hieunguyen.authmanage.entity;

import com.hieunguyen.authmanage.utils.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // DOCTOR, ADMIN

    private String fullName;
    private String phone;
    private String email;
}

