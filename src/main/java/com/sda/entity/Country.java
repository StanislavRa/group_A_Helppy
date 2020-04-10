package com.sda.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "COUNTRY")


public class Country {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID", unique = true, nullable = false, length = 100)
        private Long id;

        @Column(name = "COUNTRY", length = 60,unique = true, nullable = false)
        private String countryName;

        @Column
        @CreationTimestamp
        private LocalDateTime CREATED_ON;

        @Column
        @UpdateTimestamp
        private LocalDateTime UPDATED_ON;


        public Country() {
        }

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public LocalDateTime getCREATED_ON() {
            return CREATED_ON;
        }

        public LocalDateTime getUPDATED_ON() {
            return UPDATED_ON;
        }

    @Override
    public String toString() {
        return "AddressCountry{" +
                "id=" + id +
                ", country='" + countryName + '\'' +
                ", CREATED_ON=" + CREATED_ON +
                ", UPDATED_ON=" + UPDATED_ON +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country that = (Country) o;
        return getCountryName().equals(that.getCountryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountryName());
    }
}
