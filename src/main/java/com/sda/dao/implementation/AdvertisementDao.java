package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Advertisement;
import com.sda.util.SessionUtil;
import org.hibernate.Session;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AdvertisementDao extends SessionUtil implements Dao<Advertisement> {

    public AdvertisementDao(String hibernateConfigurationFilePath) {
        super(hibernateConfigurationFilePath);
    }

    @Override
    public Advertisement get(Long id) {
        openTransactionAndSession();
        return getSession().get(Advertisement.class, id);
    }

    @Override
    public List<Advertisement> getAll() {
        openTransactionAndSession();
        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Advertisement> cq = cb.createQuery(Advertisement.class);
        Root<Advertisement> rootEntry = cq.from(Advertisement.class);
        CriteriaQuery<Advertisement> all = cq.select(rootEntry);

        TypedQuery<Advertisement> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void save(Advertisement advertisement) {
        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.save(advertisement);

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
    public void update(Advertisement advertisement) {

        try {
            // open session with a transaction
            openTransactionAndSession();
            Session session = getSession();
            session.merge(advertisement);

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
    public void delete(Advertisement advertisement) {

        try {
            // open session with a transaction
            //openTransactionAndSession();
            Session session = getSession();
            session.delete(advertisement);

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
