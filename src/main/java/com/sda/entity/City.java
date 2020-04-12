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
                name = "City_GetAllByCountry",
                query = "select * from CITY city "+
                        "left join COUNTRY as country " +
                        "on country.id = city.FK_COUNTRY " +
                        "where country.country=:country",
                resultClass = City.class)
})


public class City {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID", unique = true, nullable = false, length = 100)
        private Long id;

        @Column(name = "CITY", length = 60,unique = true, nullable = false)
        private String cityName;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "FK_COUNTRY")
        private Country country;

        @Column
        @CreationTimestamp
        private LocalDateTime CREATED_ON;

        @Column
        @UpdateTimestamp
        private LocalDateTime UPDATED_ON;


        public City() {
        }

        public City(String cityName) {
            this.cityName = cityName;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }


        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }


        public LocalDateTime getCREATED_ON() {
            return CREATED_ON;
        }

        public LocalDateTime getUPDATED_ON() {
            return UPDATED_ON;
        }

        public Country getCountry() {
                return country;
        }

        public void setCountry(Country country) {
                this.country = country;
        }

        @Override
        public String toString() {
                return "City{" +
                        "id=" + id +
                        ", city='" + cityName + '\'' +
                        ", country=" + country +
                        ", CREATED_ON=" + CREATED_ON +
                        ", UPDATED_ON=" + UPDATED_ON +
                        '}';
        }

        @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City that = (City) o;
        return getCityName().equals(that.getCityName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCityName());
    }
}

