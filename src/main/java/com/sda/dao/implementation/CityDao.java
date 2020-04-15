package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.City;
import com.sda.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CityDao extends SessionUtil implements Dao<City> {

    public CityDao(String hibernateConfigurationFilePath) {
        super(hibernateConfigurationFilePath);
    }

    @Override
    public City get(Long id) {

        openTransactionAndSession();
        return getSession().get(City.class, id);
    }

    @Override
    public List<City> getAll() {

        openTransactionAndSession();
        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<City> cq = cb.createQuery(City.class);
        Root<City> rootEntry = cq.from(City.class);
        CriteriaQuery<City> all = cq.select(rootEntry);

        TypedQuery<City> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(City city) {

        try {
            openTransactionAndSession();
            Session session = getSession();
            session.save(city);

            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(City city) {

        try {
            openTransactionAndSession();
            Session session = getSession();
            session.merge(city);

            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(City city) {

        try {
            Session session = getSession();
            session.delete(city);

            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<String> getAllCitiesByCountryList(String country) {
        openTransactionAndSession();
        Session session = getSession();

        Query<City> getAllCitiesByCountryList = session.createNamedQuery("City_GetAllByCountry", City.class);
        getAllCitiesByCountryList.setParameter("country", country);

        List<String> listOfCityNames = new ArrayList<>();
        for (City a : getAllCitiesByCountryList.getResultList()) {
            listOfCityNames.add(a.getCityName());
        }
        return listOfCityNames;
    }

    public void deleteAll() {

        List<City> citiesList = getAll();

        Session session = getSession();
        try {
            for (City city : citiesList) {

                session.delete(city);
            }
            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
