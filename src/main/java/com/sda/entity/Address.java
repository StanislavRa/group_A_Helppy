package com.sda.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "ADDRESS")

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 100)
    private Long id;


    @Column(name = "COUNTRY", nullable = false, length = 100)
    private String country;

    @Column(name = "CITY", nullable = false, length = 100)
    private String city;

    @OneToOne(mappedBy = "address", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Advertisement advertisement;

    @Column
    @CreationTimestamp
    private LocalDateTime CREATED_ON;

    @Column
    @UpdateTimestamp
    private LocalDateTime UPDATED_ON;


    public Address() {
    }

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public void setCity(String city) {
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