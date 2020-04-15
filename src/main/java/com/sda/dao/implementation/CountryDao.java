package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.City;
import com.sda.entity.Country;
import com.sda.util.SessionUtil;
import org.hibernate.Session;

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
            openTransactionAndSession();
            Session session = getSession();
            session.save(country);

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
            openTransactionAndSession();
            Session session = getSession();
            session.merge(country);

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
            Session session = getSession();
            session.delete(country);

            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<String> getAllCountriesNamesList(List<Country> getAllCountryObjectsList) {

        List<String> listOfCountryNames = new ArrayList<>();
        for (Country c : getAllCountryObjectsList){
            listOfCountryNames.add(c.getCountryName());
        }
        return listOfCountryNames;
    }

    public void deleteAll() {

        List<Country> countryList = getAll();

        Session session = getSession();
        try {
            for (Country country : countryList) {

                session.delete(country);
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
