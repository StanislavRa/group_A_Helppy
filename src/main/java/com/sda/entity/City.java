package com.sda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CITY")
        @NamedNativeQuery(
                name = "City_GetAllByCountry",
                query = "select * from CITY city "+
                        "left join COUNTRY as country " +
                        "on country.id = city.FK_COUNTRY " +
                        "where country.country=:country",
                resultClass = City.class)
public class City {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID", unique = true, nullable = false, length = 100)
        private Long id;

        @Column(name = "CITY", length = 60,unique = true, nullable = false)
        private String cityName;

        @ManyToOne(cascade = CascadeType.MERGE)
        @JoinColumn(name = "FK_COUNTRY")
        private Country country;

        @Column(name = "CREATED_ON")
        @CreationTimestamp
        private LocalDateTime createdOn;

        @Column(name = "UPDATED_ON")
        @UpdateTimestamp
        private LocalDateTime updatedOn;

        public City(String cityName) {
            this.cityName = cityName;
        }

        public City(String cityName, Country country) {
                this.cityName = cityName;
                this.country = country;
        }
}

