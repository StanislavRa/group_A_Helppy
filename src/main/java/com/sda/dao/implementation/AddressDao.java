package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Address;
import com.sda.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Session session = openSession();
        Transaction transaction = session.beginTransaction();

        try {

            session.save(address);

            transaction.commit();

            session.close();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Address address) {

        try {
            openTransactionAndSession();
            Session session = getSession();
            session.merge(address);

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
            Session session = getSession();
            session.delete(address);

            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteAll() {

        List<Address> addressList = getAll();

        Session session = getSession();
        try {
            for (Address address : addressList) {

                session.delete(address);
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
