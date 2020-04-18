package com.sda.dao.implementation;

import com.sda.entity.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Work only one-by-one
 */

public class CustomerDaoTest {

    String connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";

    Logger log = Logger.getLogger(AdvertisementDaoTest.class.getName());

    CustomerDao customerDao;

    Customer customer1;
    Customer customer2;

    @Before
    public void setUp() {

        customerDao = new CustomerDao(connectionToDatabaseCreate);

        customer1 = new Customer();
        customer1.setLogin("Pjotr");
        customer1.setPassword("123456");
        customer1.setFullName("Petr III");

        customer2 = new Customer();
        customer2.setLogin("VasjaLogin");
        customer2.setPassword("VasjaPass");
        customer2.setFullName("Vasja Petrov");

        customerDao.save(customer1);
        customerDao.save(customer2);
    }

    @Test
    public void shouldSaveCustomer() {

        log.info("...shouldSaveCustomer...");

        Customer shouldBeSavedCustomer = customerDao.get(1L);
        Assert.assertNotNull(shouldBeSavedCustomer);
    }

    @Test
    public void shouldGetCustomerById() {

        log.info("...shouldGetCustomerById...");

        Customer shouldGetCustomerById = customerDao.get(1L);

        Assert.assertEquals(shouldGetCustomerById, customer1);
    }


    @Test
    public void shouldUpdateCustomerPassword() {

        log.info("...shouldUpdateCustomerPassword...");

        Customer customer = customerDao.get(1L);

        String newCustomerPassword = "qwerty";
        customer.setPassword(newCustomerPassword);

        customerDao.update(customer);
        Customer updatedCustomer = customerDao.get(1L);

        Assert.assertEquals(newCustomerPassword, updatedCustomer.getPassword());
    }

    @Test
    public void shouldGetAllCustomers() {

        log.info("...shouldGetAllCustomers...");

        List<Customer> getAllCustomers = customerDao.getAll();

        Assert.assertEquals(2, getAllCustomers.size());
    }

    @Test
    public void shouldDeleteCustomer() {

        log.info("...shouldDeleteCustomer...");

        Customer shouldBeSavedCustomer = customerDao.get(1L);

        customerDao.delete(shouldBeSavedCustomer);

        Customer shouldBeDeletedCustomer = customerDao.get(1L);
        Assert.assertNull(shouldBeDeletedCustomer);
    }
}
