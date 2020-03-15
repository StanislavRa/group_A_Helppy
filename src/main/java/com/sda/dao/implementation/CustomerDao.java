package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Customer;
import com.sda.entity.User;
import com.sda.util.SessionUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * @author StanislavR
 */
public class CustomerDao extends SessionUtil implements Dao<Customer> {
    @Override
    public Customer get(Long id) {
        openTransactionSession();
        return getSession().get(Customer.class, id);
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public void save(Customer customer) {

        try  {
            // open session with a transaction
            openTransactionSession();
            Session session = getSession();
            session.save(customer);

            // close session with a transaction
            closeTransactionSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }
}
