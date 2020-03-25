package com.sda.dao.implementation;

import com.sda.entity.Customer;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import javax.transaction.Transactional;

/**
 * @author StanislavR
 */


public class CustomerDaoTest {

/*
    @Rule
    //public final SessionFactoryRule sf = new SessionFactoryRule();
*/



    @Test
    public void shouldSaveCustomer() {

        CustomerDao customerDao = new CustomerDao("/com/sda/config/hibernate.cfg.xml");

        Customer customerOlga = new Customer();
        customerOlga.setLogin("customer1");
        customerOlga.setPassword("pass");
        customerOlga.setFullName("Olga");

        customerDao.save(customerOlga);

        Session session =  customerDao.openSession();

        Customer customer = customerDao.getByLogin(customerOlga.getLogin());

        //Assert.assertEquals();
    }

    @Test
    public void shouldGetAllCustomers() {

        Customer customerAnton = new Customer();
        customerAnton.setLogin("AntonLogin");
        customerAnton.setPassword("AntonPass");
        customerAnton.setFullName("Anton Chehov");

        Customer customerVasja = new Customer();
        customerVasja.setLogin("VasjaLogin");
        customerVasja.setPassword("VasjaPass");
        customerVasja.setFullName("Vasja Petrov");

        Customer customerSasha = new Customer();
        customerSasha.setLogin("SashaLogin");
        customerSasha.setPassword("SashaPass");
        customerSasha.setFullName("Sasha Sidorov");

       /* Session session = sf.getSession();

        session.save(customerAnton);
        session.save(customerVasja);
        session.save(customerSasha);


        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> rootEntry = cq.from(Customer.class);
        CriteriaQuery<Customer> all = cq.select(rootEntry);

        TypedQuery<Customer> allQuery = session.createQuery(all);

        List<Customer> customerList = allQuery.getResultList();

        Assert.assertEquals(4, customerList.size());*/


    }
    @Test
    @Transactional

    public void shouldGetCustomerByLoginAndPass() {
        CustomerDao customerDao = new CustomerDao("hibernateTest.cfg.xml");

        Customer customer = new Customer();
        String login = "JohnLove88";
        String password = "qwerty123456";
        String fullName = "John Smith";

        customer.setLogin(login);
        customer.setPassword(password);
        customer.setFullName(fullName);

        customerDao.save(customer);

        Assert.assertEquals(fullName,customerDao.getByLogin(login).getFullName());
    }

    @Test
    public void shouldDeleteCustomer() {
        CustomerDao customerDao = new CustomerDao("hibernateTest.cfg.xml");

        Customer customer = customerDao.get(6L);

        customerDao.delete(customer);

        Assert.assertNull(customerDao.get(6L));
    }
}
