package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Country;
import com.sda.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CountryDao implements Dao<Country> {

    private static final Logger logger = LoggerFactory.getLogger(CountryDao.class);

    @Override
    public Country get(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Country country = session.get(Country.class, id);
        transaction.commit();
        return country;
    }

    @Override
    public List<Country> getAll() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> rootEntry = cq.from(Country.class);
        CriteriaQuery<Country> all = cq.select(rootEntry);
        TypedQuery<Country> allQuery = session.createQuery(all);
        List<Country> countries = allQuery.getResultList();
        transaction.commit();
        return countries;
    }

    @Override
    public void save(Country country) {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
        try {
            session.save(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction!= null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Country country) {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
        try {
            session.merge(country);
            transaction.commit();

        } catch (Exception e) {
            if (transaction!= null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Country country) {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction transaction = session.beginTransaction();
        try {
            session.delete(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    public List<String> getAllCountriesNamesList(List<Country> getAllCountryObjectsList) {

        List<String> listOfCountryNames = new ArrayList<>();
        for (Country c : getAllCountryObjectsList){
            listOfCountryNames.add(c.getCountryName());
        }
        return listOfCountryNames;
    }

    @Override
    public void deleteAll() {

        List<Country> countryList = getAll();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (Country country : countryList) {

                session.delete(country);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }
}
