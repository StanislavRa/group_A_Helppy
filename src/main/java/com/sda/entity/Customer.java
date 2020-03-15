package com.sda.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author StanislavR
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer extends User{
    @Column(name = "FULL_NAME", length = 60, nullable = false)
    private String fullName;

    public Customer() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
