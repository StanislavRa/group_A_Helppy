package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Advertisement;
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

public class AdvertisementDao implements Dao<Advertisement> {

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementDao.class);

    @Override
    public Advertisement get(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Advertisement advertisement = session.get(Advertisement.class, id);
        transaction.commit();
        return advertisement;
    }

    @Override
    public List<Advertisement> getAll() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Advertisement> cq = cb.createQuery(Advertisement.class);
        Root<Advertisement> rootEntry = cq.from(Advertisement.class);
        CriteriaQuery<Advertisement> all = cq.select(rootEntry);
        TypedQuery<Advertisement> allQuery = session.createQuery(all);
        List<Advertisement> advertisements = allQuery.getResultList();
        transaction.commit();
        return advertisements;
    }

    @Override
    public void save(Advertisement advertisement) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(advertisement);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Advertisement advertisement) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(advertisement);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Advertisement advertisement) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(advertisement);
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

        List<Advertisement> advertisementList = getAll();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (Advertisement advertisement : advertisementList) {
                session.delete(advertisement);
            }
            transaction.commit();
        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e.getMessage(), e);
        }
    }

    public List<Advertisement> getAllActiveAds() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<Advertisement> getAllActiveAdvertisementList = session.createNamedQuery("Advertisement_GetAllActive",
                Advertisement.class);
        List<Advertisement> advertisements = getAllActiveAdvertisementList.getResultList();
        transaction.commit();
        return advertisements;
    }
}
