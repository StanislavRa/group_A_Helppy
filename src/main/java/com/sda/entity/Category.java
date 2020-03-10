package com.sda.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "CREATED_DATE", unique = false, nullable = false, length = 100)
    private Date createdDate;

    @Column(name = "IS_CREATED", unique = false, nullable = false)
    private boolean isCreated;

    public Category() {}

    public Category(String name, Date createdDate, boolean isCreated) {
        this.name = name;
        this.createdDate = createdDate;
        this.isCreated = isCreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }
}
