package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Category;
import com.sda.util.HibernateUtil;
import com.sda.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author StanislavR
 */
public class CategoryDao extends SessionUtil implements Dao<Category> {

    @Override
    public Category get(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.get(Category.class, id);

    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public void save(Category category) {

        try  {
            // open session with a transaction
            openTransactionSession();
            Session session = getSession();
            session.save(category);

            // close session with a transaction
            closeTransactionSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void update(Category category) {

    }

    @Override
    public void delete(Category category) {

    }
}
