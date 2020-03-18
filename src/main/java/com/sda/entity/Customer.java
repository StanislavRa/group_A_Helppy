package com.sda.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "CUSTOMER")
public class Customer extends User{
    @Column(name = "FULL_NAME", nullable = false, length = 60)
    private String fullName;

    @OneToMany
    @JoinColumn(name = "ADVERTISEMENTS_LIST")
    private List<Advertisement> userAdvertisements = new ArrayList<>();

    public Customer() {
    }

    public Customer(String login, String password, String fullName, List<Advertisement> userAdvertisements) {
        super(login, password);
        this.fullName = fullName;
        this.userAdvertisements = userAdvertisements;
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

    @Override
    public String toString() {
        return "Customer{" +
                "fullName='" + fullName + '\'' +
                ", userAdvertisements=" + userAdvertisements +
                '}';
    }
}
