package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Address;
import com.sda.util.SessionUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * @author StanislavR
 */
public class AddressDao extends SessionUtil implements Dao<Address> {

    @Override
    public Address get(Long id) {
        openTransactionAndSession();
        return getSession().get(Address.class, id);
    }

    @Override
    public List<Address> getAll() {
        return null;
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

    }

    @Override
    public void delete(Address address) {

    }
}
