package com.sda.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "FK_SUPER_CATEGORY")
    public Category superCategory;

    @OneToMany(mappedBy="superCategory", cascade = CascadeType.ALL)
    public List<Category> subCategories;

    @Column
    @CreationTimestamp
    private LocalDateTime CREATED_ON;

    @Column
    @UpdateTimestamp
    private LocalDateTime UPDATED_ON;

    public Category() {
    }

    public Category(Category superCategory, String name) {
        this.superCategory = superCategory;
        this.name = name;
        //this.subCategories = new ArrayList<>();
    }

    public Category(String name) {
        this.name = name;
        //this.subCategories = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getSuperCategory() {
        return superCategory;
    }

    public void setSuperCategory(Category superCategory) {
        this.superCategory = superCategory;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public LocalDateTime getCREATED_ON() {
        return CREATED_ON;
    }

    public void setCREATED_ON(LocalDateTime CREATED_ON) {
        this.CREATED_ON = CREATED_ON;
    }

    public LocalDateTime getUPDATED_ON() {
        return UPDATED_ON;
    }

    public void setUPDATED_ON(LocalDateTime UPDATED_ON) {
        this.UPDATED_ON = UPDATED_ON;
    }
}
