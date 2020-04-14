package com.sda.dao.implementation;

import com.sda.dao.Dao;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
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

    public void deleteAll() {

        List<Advertisement> advertisementList = getAll();

        openTransactionAndSession();
        Session session = getSession();
        try {
            // open session with a transaction
            for (Advertisement advertisement : advertisementList) {

                session.delete(advertisement);
            }
            closeTransactionAndSession();

            // close session with a transaction

        } catch (Exception e) {

            if (getTransaction() != null) {
                getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Advertisement> getAllActiveList() {
        openTransactionAndSession();
        Session session = getSession();
        String state = Advertisement.ServiceState.ACTIVE.toString();

        Query<Advertisement> getAllActiveAdvertisementList = session.createNamedQuery("Advertisement_GetAllByState",
                                                                                       Advertisement.class);
        getAllActiveAdvertisementList.setParameter("state", state);

        return getAllActiveAdvertisementList.getResultList();
    }

    public List<Advertisement> getAllInactiveList() {
        openTransactionAndSession();
        Session session = getSession();
        String state = Advertisement.ServiceState.INACTIVE.toString();

        Query<Advertisement> getAllInactiveAdvertisementList = session.createNamedQuery("Advertisement_GetAllByState",
                                                                                         Advertisement.class);
        getAllInactiveAdvertisementList.setParameter("state", state);

        return getAllInactiveAdvertisementList.getResultList();
    }

    public List<String> getAllServiceTypes() {
        List<String> listOfServiceTypes = new ArrayList<>();
        listOfServiceTypes.add(Advertisement.ServiceType.OFFER.name());
        listOfServiceTypes.add(Advertisement.ServiceType.REQUEST.name());
        return listOfServiceTypes;
    }

    public List<String> getAllServiceStates() {
        List<String> listOfServiceStates = new ArrayList<>();
        listOfServiceStates.add(Advertisement.ServiceState.INACTIVE.name());
        listOfServiceStates.add(Advertisement.ServiceState.ACTIVE.name());
        return listOfServiceStates;
    }
}
