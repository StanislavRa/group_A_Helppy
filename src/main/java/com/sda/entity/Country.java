package com.sda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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
    public Country(String countryName) {
        this.countryName = countryName;
    }

}
