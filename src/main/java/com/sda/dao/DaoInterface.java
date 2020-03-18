package com.sda.dao;

import com.sda.entity.Category;
import org.hibernate.Session;

public interface CategoryDao {

    public void addCategory(Category category) throws Exception;

    public Category searchCategory(Long categoryId) throws Exception;

    public void updateCategory(Category category) throws Exception;

    public void deleteCategory(Long categoryId) throws Exception;

    public void setSession(Session session) throws Exception;
}
