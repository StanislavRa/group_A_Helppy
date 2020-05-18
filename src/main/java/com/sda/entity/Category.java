package com.sda.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY")
@NamedNativeQueries({

        @NamedNativeQuery(
                name = "Category_GetByName",
                query = "select * from CATEGORY category " +
                        "where category.name=:name",
                resultClass = Category.class)
})

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 100)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false, length = 100)
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

    
    public Category(Category superCategory, String name) {
        this.superCategory = superCategory;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    
}
