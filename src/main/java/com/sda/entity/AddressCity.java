package com.sda.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "CITY")

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "AddressCity_GetAll",
                query = "select * from CITY city ",
                resultClass = AddressCity.class)
})


public class AddressCity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID", unique = true, nullable = false, length = 100)
        private Long id;

        @Column(name = "CITY", length = 60,unique = true, nullable = false)
        private String city;

        @Column
        @CreationTimestamp
        private LocalDateTime CREATED_ON;

        @Column
        @UpdateTimestamp
        private LocalDateTime UPDATED_ON;


        public AddressCity() {
        }

        public AddressCity(String city) {
            this.city = city;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }


        public String getCity() {
            return city;
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
        return "AddressCity{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", CREATED_ON=" + CREATED_ON +
                ", UPDATED_ON=" + UPDATED_ON +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressCity that = (AddressCity) o;
        return getCity().equals(that.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity());
    }
}

