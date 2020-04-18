package com.sda.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static SessionFactory factory;
//to disallow creating objects by other classes.

    private HibernateUtil() {
    }
//making the Hibernate SessionFactory object as singleton

    public static synchronized SessionFactory getSessionFactory(String configFile) {

        if (factory == null) {
            factory = new Configuration().configure(configFile).
                    buildSessionFactory();
        }
        return factory;
    }
}
