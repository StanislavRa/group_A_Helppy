package com.sda.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author StanislavR
 */
@Entity
@Table(name = "ADVERTISEMENT")
public class Advertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "SUBJECT", unique = true, nullable = false)
    private String subject;

    @Column(name = "PRICE", unique = true, nullable = false)
    private BigDecimal price;

    public Advertisement() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
