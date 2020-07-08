package com.sda.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY")
@NamedNativeQuery(
                name = "Category_GetByName",
                query = "select * from CATEGORY category " +
                        "where category.name=:name",
                resultClass = Category.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false, length = 100)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false, length = 100)
    private String name;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "FK_SUPER_CATEGORY")
    public Category superCategory;

    @ToString.Exclude
    @OneToMany(mappedBy="superCategory", fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    public List<Category> subCategories;

    @Column(name = "CREATED_ON")
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name = "UPDATED_ON")
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    public Category(Category superCategory, String name) {
        this.superCategory = superCategory;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }
}
