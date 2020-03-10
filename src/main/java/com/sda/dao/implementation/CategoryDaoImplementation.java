package com.sda.dao.implementation;

import com.sda.dao.CategoryDao;
import com.sda.entity.Category;
import org.hibernate.Session;

import java.io.Serializable;

public class CategoryDaoImplementation implements CategoryDao {

    private  Session session;

    @Override
    public boolean addCategory(Category category) throws Exception {
        Serializable save = session.save(category);
        return save != null;
    }

    @Override
    public Category searchCategory(int categoryId) throws Exception {
        return (Category) session.createQuery("FROM Category WHERE 'id'='"+categoryId+"'").list().get(0);
    }

    @Override
    public boolean updateCategory(Category category) throws Exception {
        return false;
    }

    @Override
    public boolean deleteCategory(int categoryId) throws Exception {
        return false;
    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
    }
}
