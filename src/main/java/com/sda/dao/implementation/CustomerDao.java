package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Customer;
import com.sda.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerDao extends SessionUtil implements Dao<Customer> {

    @Override
    public Customer get(Long id) {

        openTransactionAndSession();
        return getSession().get(Customer.class, id);
    }

    @Override
    public List<Customer> getAll() {

        openTransactionAndSession();
        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);

        TypedQuery<Customer> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(Customer customer) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.save(customer);

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
    public void update(Customer customer) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.merge(customer);

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
    public void delete(Customer customer) {

        try {
            // open session with a transaction
            //openTransactionAndSession();
            Session session = getSession();
            session.delete(customer);

            // close session with a transaction
            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }


    public Customer getByLogin(String login) {

        Customer customer = null;

        // open session with a transaction
        openTransactionAndSession();
        Session session = getSession();

        Query<Customer> query = session.createNamedQuery("Customer_GetByLogin", Customer.class);
        query.setParameter("login", login);
        if (!query.list().isEmpty()) {
            customer = query.getSingleResult();
        }

        return customer;
    }

    public Customer getByFullName(String fullName) {

        // open session with a transaction
        openTransactionAndSession();
        Session session = getSession();

        Query<Customer> query = session.createNamedQuery("Customer_GetByFullName", Customer.class);
        query.setParameter("fullName", fullName);

        return query.getSingleResult();
    }
}
