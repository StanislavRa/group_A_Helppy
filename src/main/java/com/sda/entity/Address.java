package com.sda.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "ADDRESS")

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 100)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_COUNTRY")
    private Country countryName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CITY")
    private City cityName;

    @Column
    @CreationTimestamp
    private LocalDateTime CREATED_ON;

    @Column
    @UpdateTimestamp
    private LocalDateTime UPDATED_ON;


    public Address() {
    }

    public Address(Country countryName, City cityName) {
        this.countryName = countryName;
        this.cityName = cityName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountryName() {
        return countryName;
    }

    public void setCountryName(Country countryName) {
        this.countryName = countryName;
    }

    public City getCityName() {
        return cityName;
    }

    public void setCityName(City cityName) {
        this.cityName = cityName;
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
                ", country=" + countryName +
                ", city=" + cityName +
                ", CREATED_ON=" + CREATED_ON +
                ", UPDATED_ON=" + UPDATED_ON +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return getCountryName().equals(address.getCountryName()) &&
                getCityName().equals(address.getCityName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountryName(), getCityName());
    }
}