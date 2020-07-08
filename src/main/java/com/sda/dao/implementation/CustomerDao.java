package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Customer;
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
import java.util.List;

public class CustomerDao implements Dao<Customer> {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDao.class);
    private final String fetchProfileName = "customer.userAdvertisements";

    @Override
    public Customer get(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.enableFetchProfile(fetchProfileName);
        Customer customer = session.get(Customer.class, id);
        transaction.commit();
        return customer;
    }

    @Override
    public List<Customer> getAll() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);
        TypedQuery<Customer> allQuery = session.createQuery(all);
        List<Customer> customers = allQuery.getResultList();
        transaction.commit();
        return customers;
    }

    @Override
    public void save(Customer customer) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Customer customer) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Customer customer) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(customer);
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

        List<Customer> customerList = getAll();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (Customer customer : customerList) {
                session.delete(customer);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    public Customer getByLogin(String login) {

        Customer customer = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.enableFetchProfile(fetchProfileName);
        Query<Customer> query = session.createNamedQuery("Customer_GetByLogin", Customer.class);
        query.setParameter("login", login);
        if (!query.list().isEmpty()) {
            customer = query.getSingleResult();
        }
        transaction.commit();
        return customer;
    }

    public Customer getByLoginAndPassword(String login, String password) {

        Customer customer = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.enableFetchProfile( "customer.userAdvertisements" );
        Query<Customer> query = session.createNamedQuery("Customer_GetByLoginAndPassword", Customer.class);
        query.setParameter("login", login).setParameter("password", password);
        if (!query.list().isEmpty()) {
            customer = query.getSingleResult();
        }
        transaction.commit();
        return customer;
    }
}
