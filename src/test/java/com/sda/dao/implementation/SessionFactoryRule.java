package com.sda.dao.implementation;

import com.sda.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import java.util.Properties;

/**
 * @author StanislavR
 */
public class SessionFactoryRule implements MethodRule {

    private SessionFactory sessionFactory;
    private Transaction transaction;
    private Session session;
    @Override
    public Statement apply(final Statement statement, FrameworkMethod method,
                           Object test) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                sessionFactory = createSessionFactory();
                createSession();
                beginTransaction();
                try {
                    statement.evaluate();
                } finally {
                    shutdown();
                }
            }
        };
    }
    private void shutdown() {
        try {
            try {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                session.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            sessionFactory.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private SessionFactory createSessionFactory() {
        try {

            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            properties.put(Environment.URL, "jdbc:mysql://trescoronas.ee:3306/test?createDatabaseIfNotExist=true");
            properties.put(Environment.USER, "root");
            properties.put(Environment.PASS, "zahFiew0Rili4caighuNop5teegue6ae");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "create-drop");

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(Address.class);
            configuration.addAnnotatedClass(Advertisement.class);
            configuration.addAnnotatedClass(Category.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return sessionFactory;
    }
    public Session createSession() {
        session = sessionFactory.openSession();
        return session;
    }
    public void commit() {
        transaction.commit();
    }
    public void beginTransaction() {
        transaction = session.beginTransaction();
    }
    public Session getSession() {
        return session;
    }
}
