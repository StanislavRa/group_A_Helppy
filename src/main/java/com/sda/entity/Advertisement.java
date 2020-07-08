package com.sda.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ADVERTISEMENT")
@NamedNativeQuery(
        name = "Advertisement_GetAllActive",
        query = "select * from ADVERTISEMENT advertisement " +
                "where advertisement.END_DATE > CURDATE() - 1",
        resultClass = Advertisement.class)

public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 100)
    private Long id;

    @Column(name = "SUBJECT", nullable = false, length = 100)
    private String subject;

    @Column(name = "DESCRIPTION", nullable = false, length = 1000)
    private String description;

    @Column(name = "PRICE", nullable = false, length = 100)
    private BigDecimal price;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "CREATED_ON")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "UPDATED_ON")
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false, length = 50)
    private ServiceType serviceType;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_CATEGORY_ID")
    private Category category;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_ID")
    private Address address;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_CUSTOMER_ID")
    private Customer customer;

    public enum ServiceType {
        OFFER("Offer"),
        REQUEST("Request");
        private final String type;

        ServiceType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }
}
