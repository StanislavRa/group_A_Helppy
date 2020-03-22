package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Address;
import com.sda.util.SessionUtil;
import org.hibernate.Session;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AddressDao extends SessionUtil implements Dao<Address> {

    public AddressDao(String hibernateConfigurationFilePath) {
        super(hibernateConfigurationFilePath);
    }

    @Override
    public Address get(Long id) {

        openTransactionAndSession();
        return getSession().get(Address.class, id);
    }

    @Override
    public List<Address> getAll() {

        openTransactionAndSession();
        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Address> cq = cb.createQuery(Address.class);
        Root<Address> rootEntry = cq.from(Address.class);
        CriteriaQuery<Address> all = cq.select(rootEntry);

        TypedQuery<Address> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(Address address) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.save(address);

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
    public void update(Address address) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.merge(address);

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
    public void delete(Address address) {

        try {
            // open session with a transaction
            //openTransactionAndSession();
            Session session = getSession();
            session.delete(address);

            // close session with a transaction
            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}
