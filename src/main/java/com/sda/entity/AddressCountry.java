package com.sda.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "COUNTRY")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "AddressCountry_GetAll",
                query = "select * from COUNTRY country ",
                resultClass = AddressCountry.class)
})

public class AddressCountry {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID", unique = true, nullable = false, length = 100)
        private Long id;

        @Column(name = "COUNTRY", length = 60,unique = true, nullable = false)
        private String country;

        @Column
        @CreationTimestamp
        private LocalDateTime CREATED_ON;

        @Column
        @UpdateTimestamp
        private LocalDateTime UPDATED_ON;


        public AddressCountry() {
        }

    public AddressCountry(String country) {
        this.country = country;
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
                ", country='" + country + '\'' +
                ", CREATED_ON=" + CREATED_ON +
                ", UPDATED_ON=" + UPDATED_ON +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressCountry that = (AddressCountry) o;
        return getCountry().equals(that.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry());
    }
}
