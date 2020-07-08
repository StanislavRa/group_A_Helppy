package com.sda.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.annotations.FetchMode.JOIN;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
@NamedNativeQuery(
        name = "Customer_GetByLogin",
        query = "select * from CUSTOMER customer " +
                "left join USER as user " +
                "on user.id = customer.id " +
                "where binary (user.LOGIN)=:login",
        resultClass = Customer.class)
@NamedNativeQuery(
        name = "Customer_GetByLoginAndPassword",
        query = "select * from CUSTOMER customer " +
                "left join USER as user " +
                "on user.id = customer.id " +
                "where binary (user.LOGIN)=:login and binary (user.PASSWORD)=:password",
        resultClass = Customer.class)
@FetchProfile(
        name="customer.userAdvertisements",
        fetchOverrides={
                @FetchProfile.FetchOverride(
                        entity=Customer.class,
                        association="userAdvertisements",
                        mode=JOIN
                )
        }
)
public class Customer extends User {
    @Column(name = "FULL_NAME", nullable = false, length = 60)
    private String fullName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Advertisement> userAdvertisements = new ArrayList<>();

    public Customer(String login, String password, String fullName) {
        super(login, password);
        this.fullName = fullName;
    }
}
