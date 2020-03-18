package com.sda.dao.implementation;

import com.sda.dao.DaoInterface;
import com.sda.entity.Category;
import com.sda.util.HibernateUtil;
import org.hibernate.Session;

public class DaoInterfaceImplementation implements DaoInterface {

    private  Session session;
    //Create session factory object
     sessionFactory = HibernateUtil.getSessionFactory();
    //getting session object from session factory
    Session session = sessionFactory.openSession();
    //getting transaction object from session object
  session.beginTransaction();
    @Override
    public void addCategory(Category category) throws Exception {

        return ;
    }

    @Override
    public Category searchCategory(Long categoryId) throws Exception {
        final String SEARCH_CATEGORY_BY_ID = "SELECT_FROM CATEGORY WHERE 'id'='"+categoryId+"'";
        return (Category)session.createQuery(SEARCH_CATEGORY_BY_ID).list().get(0);
    }

    @Override
    public void updateCategory(Category category) throws Exception {
        return;
    }

    @Override
    public void deleteCategory(Long categoryId) throws Exception {
        final String DELETE_CATEGORY_BY_ID = "DELETE FROM CATEGORY WHERE 'id'='"+categoryId+"'";
        return (Category)session.createQuery(DELETE_CATEGORY_BY_ID).list().get(0);

    }

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
    }
}
