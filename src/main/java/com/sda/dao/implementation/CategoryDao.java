package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Category;
import com.sda.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
            openTransactionAndSession();
            Session session = getSession();
            session.save(category);

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

            openTransactionAndSession();
            Session session = getSession();
            session.merge(category);

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

            Session session = getSession();
            session.remove(category);

            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteAll() {

        List<Category> categoryList = getAll();

        Session session = getSession();
        try {

            for (Category category : categoryList) {

                session.delete(category);
            }
        closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public Category getByName(String name) {

        openTransactionAndSession();
        Session session = getSession();

        Query<Category> getCategoryByName = session.createNamedQuery("Category_GetByName",
                Category.class);
        getCategoryByName.setParameter("name", name);

        return getCategoryByName.getSingleResult();
    }


    public List<String> getAllCategoriesList() {

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