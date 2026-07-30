package com.homepocket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The username must be informed")
    @Column(nullable = false, length = 25)
    private String username;

    @NotBlank(message = "The name must be informed")
    @Column(nullable = false, length = 110)
    private String name;

    @NotBlank(message = "The password must be informed")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "The email must be informed")
    @Email
    @Column(nullable = false)
    private String email;
}
