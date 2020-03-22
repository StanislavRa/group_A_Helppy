package com.sda.dao.implementation;

import com.sda.entity.Customer;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author StanislavR
 */
public class CustomerDaoTest2 {
    @Rule
    public final SessionFactoryRule sf = new SessionFactoryRule();
    @Test
    public void shouldGetCustomerByLoginAndPass() {
        CustomerDao customerDao = new CustomerDao();

        Session session = sf.getSession();

        Customer customer = new Customer();
        String login = "JohnLove88";
        String password = "qwerty123456";
        String fullName = "John Smith";

        customer.setLogin(login);
        customer.setPassword(password);
        customer.setFullName(fullName);

        session.save(customer);
        sf.commit();

        Assert.assertEquals(fullName,customerDao.getByLogin(login).getFullName());
    }
}
