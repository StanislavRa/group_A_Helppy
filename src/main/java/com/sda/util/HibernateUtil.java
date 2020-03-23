package com.sda.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class HibernateUtil {

    private  SessionFactory sessionFactory;

    public HibernateUtil(String hibernateConfigurationFilePath) {

        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml)
            // config file.
            sessionFactory = new Configuration().configure(hibernateConfigurationFilePath).buildSessionFactory();
        } catch (Exception ex) {
            // Log the exception.
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
