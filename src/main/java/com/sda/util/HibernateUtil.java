package com.sda.util;

import com.sda.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory factory;

    private HibernateUtil() {
    }

    public static synchronized SessionFactory getSessionFactory() {

        if (factory == null) {
            factory = new Configuration()
                    .addAnnotatedClass(Advertisement.class)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Country.class)
                    .addAnnotatedClass(City.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Customer.class)
                    .buildSessionFactory();
        }
        return factory;
    }
}
