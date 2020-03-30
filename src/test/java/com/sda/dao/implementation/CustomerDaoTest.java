package com.sda.dao.implementation;

import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import com.sda.entity.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author StanislavR
 */


public class CustomerDaoTest {

    Logger log = Logger.getLogger(AddressDaoTest.class.getName());
    CustomerDao customerDao = new CustomerDao("oleksHibernateTest.cfg.xml");
    AdvertisementDao advertisementDao = new AdvertisementDao("oleksHibernateTest.cfg.xml");
    AddressDao addressDao = new AddressDao("oleksHibernateTest.cfg.xml");


    @Test
    public void shouldSaveCustomer() {

        log.info("...shouldSaveCustomer...");

        Customer customerOlga = new Customer();
        customerOlga.setLogin("customer1");
        customerOlga.setPassword("pass");
        customerOlga.setFullName("Olga");

        customerDao.save(customerOlga);

        Customer shouldBeSavedCustomer = customerDao.get(1L);
        Assert.assertNotNull(shouldBeSavedCustomer);

    }


    @Test
    public void shouldGetCustomerById() throws ParseException {

        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        customerDao.save(customer);

        //create address
        Address addressTest1 = new Address("Estonia", "Tallinn", "Parnu mnt");
        addressDao.save(addressTest1);

        Customer shouldGetCustomerById = customerDao.get(1L);

        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString1);

        Advertisement advertisement1 = new Advertisement(
                "Clean Fast",
                "blablabla",
                "2.5",
                startDate1,
                endDate1,
                "OFFER",
                new Category("CLEANING"),
                shouldGetCustomerById);
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);

        advertisementDao.save(advertisement1);


        Assert.assertEquals(shouldGetCustomerById, customer);
    }


    @Test
    public void shouldUpdateCustomerPassword() {

        log.info("...shouldUpdateCustomerPassword...");

        Customer customerOlga = new Customer();
        customerOlga.setLogin("customer1");
        customerOlga.setPassword("pass");
        customerOlga.setFullName("Olga");

        customerDao.save(customerOlga);

        Customer customer = customerDao.get(1L);

        String newCustomerPassword = "qwerty";


        customer.setPassword(newCustomerPassword);

        customerDao.update(customer);

        Customer updatedCustomer = customerDao.get(1L);

        Assert.assertEquals(newCustomerPassword,updatedCustomer.getPassword());
    }

    @Test
    public void shouldGetAllCustomers() {

        log.info("...shouldGetAllCustomers...");


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

        customerDao.save(customerAnton);
        customerDao.save(customerSasha);
        customerDao.save(customerVasja);

        List<Customer> getAllCustomers =  customerDao.getAll();

        Assert.assertEquals(3, getAllCustomers.size());
    }

    @Test
    public void shouldDeleteCustomer() {

        log.info("...shouldDeleteCustomer...");

        Customer customerTest1 = new Customer("login", "password", "C J");
        customerDao.save(customerTest1);

        Customer customerTest2 = new Customer("login1", "password", "C J");
        customerDao.save(customerTest2);

        Customer shouldBeSavedCustomer = customerDao.get(2L);
        Assert.assertNotNull(shouldBeSavedCustomer);

        customerDao.delete(shouldBeSavedCustomer);

        Customer shouldBeDeletedCustomer = customerDao.get(2L);
        Assert.assertNull(shouldBeDeletedCustomer);
    }
}
