package com.sda.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ADVERTISEMENT")
 /* Annotation repetitions should not be wrapped.
    Before Java 8 if you needed to use multiple instances of the same annotation, they had to be wrapped in a container annotation.
    With Java 8, that's no longer necessary, allowing for cleaner, more readable code.*/
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Advertisement_GetAllActive",
                query = "select * from ADVERTISEMENT advertisement " +
                        "where advertisement.END_DATE > CURDATE() - 1",
                resultClass = Advertisement.class),

})

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

    @Column
    @CreationTimestamp
    private LocalDateTime CREATED_ON;   // Violating naming convention

    @Column
    @UpdateTimestamp
    private LocalDateTime UPDATED_ON;   // Violating naming convention

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false, length = 50)
    private ServiceType serviceType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "FK_CATEGORY_ID")
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_ID")
    private Address address;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CUSTOMER_ID")
    private Customer customer;
    /*Having constructor with more than 4 parameters is a sign of code smell
    A long parameter list can indicate that a new structure should be created to wrap the numerous parameters
    or that the function is doing too many things. (Violating Single Responsibility)*/
    public Advertisement(String subject,
                         String description,
                         String price,
                         Date startDate,
                         Date endDate,
                         ServiceType serviceType,
                         Category category,
                         Customer customer,
                         Address address) {
        this.subject = subject;
        this.description = description;
        this.price = new BigDecimal(price);
        this.startDate = startDate;
        this.endDate = endDate;
        this.serviceType = serviceType;
        this.category = category;
        this.customer = customer;
        this.address = address;

    }

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