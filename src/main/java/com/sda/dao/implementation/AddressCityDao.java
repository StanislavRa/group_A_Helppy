package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.AddressCity;
import com.sda.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AddressCityDao extends SessionUtil implements Dao<AddressCity> {

    public AddressCityDao(String hibernateConfigurationFilePath) {
        super(hibernateConfigurationFilePath);
    }

    @Override
    public AddressCity get(Long id) {

        openTransactionAndSession();
        return getSession().get(AddressCity.class, id);
    }

    @Override
    public List<AddressCity> getAll() {

        openTransactionAndSession();
        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AddressCity> cq = cb.createQuery(AddressCity.class);
        Root<AddressCity> rootEntry = cq.from(AddressCity.class);
        CriteriaQuery<AddressCity> all = cq.select(rootEntry);

        TypedQuery<AddressCity> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(AddressCity addressCity) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.save(addressCity);

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
    public void update(AddressCity addressCity) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.merge(addressCity);

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
    public void delete(AddressCity addressCity) {

        try {
            // open session with a transaction
            //openTransactionAndSession();
            Session session = getSession();
            session.delete(addressCity);

            // close session with a transaction
            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<String> getAllAddressCitiesList() {
        openTransactionAndSession();
        Session session = getSession();

        Query<AddressCity> getAllAddressCitiesList = session.createNamedQuery("AddressCity_GetAll", AddressCity.class);
        List<String> listOfCityNames = new ArrayList<>();
        for (AddressCity a : getAllAddressCitiesList.getResultList()){
            listOfCityNames.add(a.getCity());
        }
        return listOfCityNames;
    }
}
