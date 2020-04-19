package com.sda.dao.implementation;

import com.sda.entity.Customer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.logging.Logger;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDaoTest {

    static String connectionToDatabaseCreate;

    Logger log = Logger.getLogger(AdvertisementDaoTest.class.getName());

    static CustomerDao customerDao;

    static Customer customer1;
    static Customer customer2;
    static Customer customerToBeUpdated;
    static Customer customerToBeDeleted;

    @BeforeClass
    public static void setUp() {

        connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";

        customerDao = new CustomerDao(connectionToDatabaseCreate);

        customer1 = new Customer();
        customer1.setLogin("Pjotr");
        customer1.setPassword("123456");
        customer1.setFullName("Petr III");

        customer2 = new Customer();
        customer2.setLogin("VasjaLogin");
        customer2.setPassword("VasjaPass");
        customer2.setFullName("Vasja Petrov");

        customerToBeUpdated = new Customer();
        customerToBeUpdated.setLogin("StanLogin");
        customerToBeUpdated.setPassword("StanPass");
        customerToBeUpdated.setFullName("Stan Ra");

        customerToBeDeleted = new Customer();
        customerToBeDeleted.setLogin("AlexLogin");
        customerToBeDeleted.setPassword("AlexPass");
        customerToBeDeleted.setFullName("Alex Mighty");

        customerDao.save(customer1);
        customerDao.save(customer2);
        customerDao.save(customerToBeUpdated);
        customerDao.save(customerToBeDeleted);
    }

    @Test
    public void should1SaveCustomer() {

        log.info("...shouldSaveCustomer...");

        Customer shouldBeSavedCustomer = customerDao.get(1L);
        Assert.assertNotNull(shouldBeSavedCustomer);
    }

    @Test
    public void should2GetCustomerById() {

        log.info("...shouldGetCustomerById...");

        Customer shouldGetCustomerById = customerDao.get(1L);

        Assert.assertEquals(shouldGetCustomerById, customer1);
    }

    @Test
    public void should3GetAllCustomers() {

        log.info("...shouldGetAllCustomers...");

        List<Customer> getAllCustomers = customerDao.getAll();

        Assert.assertEquals(4, getAllCustomers.size());
    }

    @Test
    public void should4UpdateCustomerPassword() {

        log.info("...shouldUpdateCustomerPassword...");

        Customer customer = customerDao.get(3L);

        String newCustomerPassword = "qwerty";
        customer.setPassword(newCustomerPassword);

        customerDao.update(customer);
        Customer updatedCustomer = customerDao.get(3L);

        Assert.assertEquals(newCustomerPassword, updatedCustomer.getPassword());
    }


    @Test
    public void should5DeleteCustomer() {

        log.info("...shouldDeleteCustomer...");

        Customer shouldBeSavedCustomer = customerDao.get(4L);

        customerDao.delete(shouldBeSavedCustomer);

        Customer shouldBeDeletedCustomer = customerDao.get(4L);
        Assert.assertNull(shouldBeDeletedCustomer);
    }
}
