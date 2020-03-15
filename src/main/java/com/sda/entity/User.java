package com.sda.entity;

import javax.persistence.*;
import java.util.*;

/**
 * @author StanislavR
 */
@Entity
@Table(name = "USER")
@Inheritance(
        strategy = InheritanceType.JOINED)

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "LOGIN", length = 60, nullable = false)
    private String login;
    @Column(name = "PASSWORD", length = 60, nullable = false)
    private String password;


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
