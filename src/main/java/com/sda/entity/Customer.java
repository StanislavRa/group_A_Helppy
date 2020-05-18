package com.sda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
// Try to be consistent with documentation. You should apply JavaDoc to all classes.
/**
 * @author StanislavR
 */
@Data
@NoArgsConstructor
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
                        "where binary (user.LOGIN)=:login",
                resultClass = Customer.class),
        @NamedNativeQuery(
                name = "Customer_GetByLoginAndPassword",
                query = "select * from CUSTOMER customer " +
                        "left join USER as user " +
                        "on user.id = customer.id " +
                        "where binary (user.LOGIN)=:login and binary(user.PASSWORD)=:password",
                resultClass = Customer.class)
})

public class Customer extends User {
    // You could put fullName as a User field not Customer. Thus, there was no need for line 45-46 and 52-55
    @Column(name = "FULL_NAME", nullable = false, length = 60)
    private String fullName;

    // Why EAGER and not Lazy loading?
    @OneToMany(mappedBy = "customer",  fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Advertisement> userAdvertisements = new ArrayList<>();

    public Customer(String login, String password, String fullName) {
        super(login, password);
        this.fullName = fullName;
    }
}
