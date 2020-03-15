package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.util.SessionUtil;
import org.hibernate.Session;

import java.util.List;

/**
 * @author StanislavR
 */
public class AdvertisementDao extends SessionUtil implements Dao<Advertisement> {
    @Override
    public Advertisement get(Long id) {
        openTransactionSession();
        return getSession().get(Advertisement.class, id);
    }

    @Override
    public List<Advertisement> getAll() {
        return null;
    }

    @Override
    public void save(Advertisement advertisement) {
        try  {
            // open session with a transaction
            openTransactionSession();
            Session session = getSession();
            session.save(advertisement);

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
    public void update(Advertisement advertisement) {

    }

    @Override
    public void delete(Advertisement advertisement) {

    }
}
