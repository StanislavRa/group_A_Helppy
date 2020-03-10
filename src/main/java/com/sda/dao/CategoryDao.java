package com.sda.dao;

import com.sda.entity.Category;
import org.hibernate.Session;

public interface CategoryDao {

    public boolean addCategory(Category category) throws Exception;

    public Category searchCategory(int categoryId) throws Exception;

    public boolean updateCategory(Category category) throws Exception;

    public boolean deleteCategory(int categoryId) throws Exception;

    public void setSession(Session session) throws Exception;
}
