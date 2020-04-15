package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Advertisement;
import com.sda.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
            openTransactionAndSession();
            Session session = getSession();
            session.save(advertisement);

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
            openTransactionAndSession();
            Session session = getSession();
            session.merge(advertisement);

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
            Session session = getSession();
            session.delete(advertisement);

            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {

        List<Advertisement> advertisementList = getAll();

        Session session = getSession();
        try {
            for (Advertisement advertisement : advertisementList) {

                session.delete(advertisement);
            }
            closeTransactionAndSession();

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Advertisement> getAllActiveAds() {
        openTransactionAndSession();
        Session session = getSession();
        String state = Advertisement.ServiceState.ACTIVE.toString();

        Query<Advertisement> getAllActiveAdvertisementList = session.createNamedQuery("Advertisement_GetAllByState",
                Advertisement.class);
        getAllActiveAdvertisementList.setParameter("state", state);

        return getAllActiveAdvertisementList.getResultList();
    }

    public List<Advertisement> getAllInactiveAds() {
        openTransactionAndSession();
        Session session = getSession();
        String state = Advertisement.ServiceState.INACTIVE.toString();

        Query<Advertisement> getAllInactiveAdvertisementList = session.createNamedQuery("Advertisement_GetAllByState",
                Advertisement.class);
        getAllInactiveAdvertisementList.setParameter("state", state);

        return getAllInactiveAdvertisementList.getResultList();
    }
}