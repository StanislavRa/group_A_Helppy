package com.sda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 100)
    private Long id;

    @Column(name = "LOGIN", unique = true, nullable = false, length = 60)
    private String login;

    @Column(name = "PASSWORD", nullable = false, length = 60)
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}