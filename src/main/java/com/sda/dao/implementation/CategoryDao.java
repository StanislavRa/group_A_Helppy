package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Category;
import com.sda.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements Dao<Category> {

    private static final Logger logger = LoggerFactory.getLogger(CategoryDao.class);

    @Override
    public Category get(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Category category = session.get(Category.class, id);
        transaction.commit();
        return category;
    }

    @Override
    public List<Category> getAll() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> rootEntry = cq.from(Category.class);
        CriteriaQuery<Category> all = cq.select(rootEntry);
        TypedQuery<Category> allQuery = session.createQuery(all);
        List<Category> categories = allQuery.getResultList();
        transaction.commit();
        return categories;
    }

    @Override
    public void save(Category category) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(category);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Category category) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(category);
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Category category) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(category);
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void deleteAll() {

        List<Category> categoryList = getAll();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (Category category : categoryList) {
                session.delete(category);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    public Category getByName(String name) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<Category> getCategoryByName = session.createNamedQuery("Category_GetByName",
                Category.class);
        getCategoryByName.setParameter("name", name);
        Category category = getCategoryByName.getSingleResult();
        transaction.commit();
        return category;
    }

    public List<String> getAllCategoryNames() {
        List<Category> getAllCategoryList = getAll();
        List<String> listOfSubcategories = new ArrayList<>();
        for (Category o : getAllCategoryList) {
            if (o.getSuperCategory() == null) {
                listOfSubcategories.add(o.getName());
            }
        }
        return listOfSubcategories;
    }
}
