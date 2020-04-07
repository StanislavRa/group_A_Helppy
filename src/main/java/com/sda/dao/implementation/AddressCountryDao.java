package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.AddressCountry;
import com.sda.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AddressCountryDao extends SessionUtil implements Dao<AddressCountry> {

    public AddressCountryDao(String hibernateConfigurationFilePath) {
        super(hibernateConfigurationFilePath);
    }

    @Override
    public AddressCountry get(Long id) {

        openTransactionAndSession();
        return getSession().get(AddressCountry.class, id);
    }

    @Override
    public List<AddressCountry> getAll() {

        openTransactionAndSession();
        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AddressCountry> cq = cb.createQuery(AddressCountry.class);
        Root<AddressCountry> rootEntry = cq.from(AddressCountry.class);
        CriteriaQuery<AddressCountry> all = cq.select(rootEntry);

        TypedQuery<AddressCountry> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(AddressCountry addressCountry) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.save(addressCountry);

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
    public void update(AddressCountry addressCountry) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.merge(addressCountry);

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
    public void delete(AddressCountry addressCountry) {

        try {
            // open session with a transaction
            //openTransactionAndSession();
            Session session = getSession();
            session.delete(addressCountry);

            // close session with a transaction
            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<String> getAllAddressCountyList() {
        openTransactionAndSession();
        Session session = getSession();

        Query<AddressCountry> getAllAddressCountriesList = session.createNamedQuery("AddressCountry_GetAll", AddressCountry.class);
        List<String> listOfCityNames = new ArrayList<>();
        for (AddressCountry a : getAllAddressCountriesList.getResultList()){
            listOfCityNames.add(a.getCountry());
        }
        return listOfCityNames;
    }
}
