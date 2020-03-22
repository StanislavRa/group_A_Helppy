package com.sda.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "ADVERTISEMENT")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 100)
    private Long id;

    @Column(name = "SUBJECT", unique = true, nullable = false)
    private String subject;

    @Column(name = "DESCRIPTION", unique = true, nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column
    @CreationTimestamp
    private LocalDateTime CREATED_ON;

    @Column
    @UpdateTimestamp
    private LocalDateTime UPDATED_ON;


    @Enumerated(EnumType.STRING)
    @Column(name = "STATE", nullable = false, updatable = false)
    private ServiceState serviceState;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false, updatable = false)
    private ServiceType serviceType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "FK_ADDRESS_ID")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "FK_CUSTOMER_ID")
    private Customer customer;

    public Advertisement() {
    }

    public Advertisement(String subject,
                         String description,
                         String price,
                         Date startDate,
                         Date endDate,
                         String serviceType,
                         Category category,
                         Customer customer) {
        this.subject = subject;
        this.description = description;
        this.price = new BigDecimal(price);
        this.startDate = startDate;
        this.endDate = endDate;
        this.serviceType = ServiceType.valueOf(serviceType);
        this.category = category;
        this.customer = customer;

        if (this.endDate.before(new Date())) {
            this.serviceState = ServiceState.ACTIVE;
        } else this.serviceState = ServiceState.INACTIVE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ServiceState getServiceState() {
        return serviceState;
    }

    public void setServiceState(ServiceState serviceState) {
        this.serviceState = serviceState;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCREATED_ON() {
        return CREATED_ON;
    }

    public LocalDateTime getUPDATED_ON() {
        return UPDATED_ON;
    }

    public void setCREATED_ON(LocalDateTime CREATED_ON) {
        this.CREATED_ON = CREATED_ON;
    }

    public void setUPDATED_ON(LocalDateTime UPDATED_ON) {
        this.UPDATED_ON = UPDATED_ON;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public enum  ServiceType {
        OFFER("Offer"),
        REQUEST("Request");
        private String type;

        ServiceType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }
    public enum  ServiceState {
        ACTIVE("Active"),
        INACTIVE("Inactive");
        String state;

        ServiceState(String state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return state;
        }
    }
}