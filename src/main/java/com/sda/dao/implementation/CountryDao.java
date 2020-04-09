package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Country;
import com.sda.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CountryDao extends SessionUtil implements Dao<Country> {

    public CountryDao(String hibernateConfigurationFilePath) {
        super(hibernateConfigurationFilePath);
    }

    @Override
    public Country get(Long id) {

        openTransactionAndSession();
        return getSession().get(Country.class, id);
    }

    @Override
    public List<Country> getAll() {

        openTransactionAndSession();
        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> rootEntry = cq.from(Country.class);
        CriteriaQuery<Country> all = cq.select(rootEntry);

        TypedQuery<Country> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(Country country) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.save(country);

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
    public void update(Country country) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.merge(country);

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
    public void delete(Country country) {

        try {
            // open session with a transaction
            //openTransactionAndSession();
            Session session = getSession();
            session.delete(country);

            // close session with a transaction
            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<String> getAllCountyList() {
        openTransactionAndSession();
        Session session = getSession();

        Query<Country> getAllCountriesList = session.createNamedQuery("AddressCountry_GetAll", Country.class);
        List<String> listOfCityNames = new ArrayList<>();
        for (Country a : getAllCountriesList.getResultList()){
            listOfCityNames.add(a.getCountryName());
        }
        return listOfCityNames;
    }
}
