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

    @Column(name = "CREATED_ON")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "UPDATED_ON")
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }
}
