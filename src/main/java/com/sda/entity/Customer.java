package com.sda.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author StanislavR
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer extends User{
    @Column(name = "FULL_NAME", length = 60, nullable = false)
    private String fullName;

    @OneToMany
    @JoinColumn(name = "customer_advertisement", nullable = true)
    private List<Advertisement> userAdvertisements = new ArrayList<>();

    public Customer() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    

    public List<Advertisement> getUserAdvertisements() {
        return userAdvertisements;
    }

    public void setUserAdvertisements(List<Advertisement> userAdvertisements) {
        this.userAdvertisements = userAdvertisements;
    }
}
