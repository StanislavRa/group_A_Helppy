package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.City;
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

public class CityDao implements Dao<City> {

    private static final Logger logger = LoggerFactory.getLogger(CityDao.class);

    @Override
    public City get(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        City city = session.get(City.class, id);
        transaction.commit();
        return city;
    }

    @Override
    public List<City> getAll() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<City> cq = cb.createQuery(City.class);
        Root<City> rootEntry = cq.from(City.class);
        CriteriaQuery<City> all = cq.select(rootEntry);
        TypedQuery<City> allQuery = session.createQuery(all);
        List<City> cities = allQuery.getResultList();
        transaction.commit();
        return cities;
    }

    @Override
    public void save(City city) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(city);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(City city) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(city);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(City city) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(city);
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

        List<City> citiesList = getAll();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (City city : citiesList) {

                session.delete(city);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    public List<String> getAllCityNamesByCountry(String country) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<City> getAllCitiesByCountryList = session.createNamedQuery("City_GetAllByCountry", City.class);
        getAllCitiesByCountryList.setParameter("country", country);
        List<String> listOfCityNames = new ArrayList<>();
        for (City a : getAllCitiesByCountryList.getResultList()) {
            listOfCityNames.add(a.getCityName());
        }
        transaction.commit();
        return listOfCityNames;
    }
}
