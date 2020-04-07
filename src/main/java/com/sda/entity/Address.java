package com.sda.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "ADDRESS")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Address_GetAll",
                query = "select * from ADDRESS address ",
                resultClass = Address.class)
})

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 100)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_COUNTRY")
    private AddressCountry country;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CITY")
    private AddressCity city;

    @Column
    @CreationTimestamp
    private LocalDateTime CREATED_ON;

    @Column
    @UpdateTimestamp
    private LocalDateTime UPDATED_ON;


    public Address() {
    }

    public Address(AddressCountry country, AddressCity city) {
        this.country = country;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AddressCountry getCountry() {
        return country;
    }

    public void setCountry(AddressCountry country) {
        this.country = country;
    }

    public AddressCity getCity() {
        return city;
    }

    public void setCity(AddressCity city) {
        this.city = city;
    }

    public LocalDateTime getCREATED_ON() {
        return CREATED_ON;
    }

    public LocalDateTime getUPDATED_ON() {
        return UPDATED_ON;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country=" + country +
                ", city=" + city +
                ", CREATED_ON=" + CREATED_ON +
                ", UPDATED_ON=" + UPDATED_ON +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return getCountry().equals(address.getCountry()) &&
                getCity().equals(address.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getCity());
    }
}