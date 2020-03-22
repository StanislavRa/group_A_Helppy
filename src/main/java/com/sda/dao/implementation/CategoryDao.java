package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Category;
import com.sda.util.SessionUtil;
import org.hibernate.Session;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CategoryDao extends SessionUtil implements Dao<Category> {

    public CategoryDao(String hibernateConfigurationFilePath) {
        super(hibernateConfigurationFilePath);
    }

    @Override
    public Category get(Long id) {

        openTransactionAndSession();
        return getSession().get(Category.class, id);
    }

    @Override
    public List<Category> getAll() {

        openTransactionAndSession();
        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> rootEntry = cq.from(Category.class);
        CriteriaQuery<Category> all = cq.select(rootEntry);

        TypedQuery<Category> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(Category category) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.save(category);

            // close session with a transaction
            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category category) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.merge(category);

            // close session with a transaction
            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category category) {

        try {
            // open session with a transaction
            //openTransactionAndSession();
            Session session = getSession();
            session.remove(category);

            // close session with a transaction
            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Category> getAllSuperCategories() {
        openTransactionAndSession();
        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> rootEntry = cq.from(Category.class);
        CriteriaQuery<Category> all = cq.select(rootEntry);

        TypedQuery<Category> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }
}
