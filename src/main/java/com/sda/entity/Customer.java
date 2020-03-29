package com.sda.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author StanislavR
 */
@Entity
@Table(name = "CUSTOMER")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Customer_GetByFullName",
                query = "select * from CUSTOMER customer " +
                        "left join USER as user " +
                        "on user.id = customer.id " +
                        "where customer.FULL_NAME=:fullName",
                resultClass = Customer.class),
        @NamedNativeQuery(
                name = "Customer_GetByLogin",
                query = "select * from CUSTOMER customer " +
                        "left join USER as user " +
                        "on user.id = customer.id " +
                        "where user.LOGIN=:login",
                resultClass = Customer.class),
        @NamedNativeQuery(
                name = "Customer_GetByLoginAndPassword",
                query = "select * from CUSTOMER customer " +
                        "left join USER as user " +
                        "on user.id = customer.id " +
                        "where user.LOGIN=:login and user.PASSWORD=:password",
                resultClass = Customer.class)
})

public class Customer extends User {

    @Column(name = "FULL_NAME", nullable = false, length = 60)
    private String fullName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Advertisement> userAdvertisements = new ArrayList<>();

    public Customer() {
    }

    public Customer(String login, String password, String fullName) {
        super(login, password);
        this.fullName = fullName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getFullName(), customer.getFullName()) &&
                Objects.equals(getLogin(), customer.getLogin())&&
                Objects.equals(getPassword(), customer.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName(), getLogin(), getPassword());
    }
}
