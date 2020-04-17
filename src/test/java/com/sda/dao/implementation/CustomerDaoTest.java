package com.sda.dao.implementation;

import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import com.sda.entity.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Work only one-by-one
 */

public class CustomerDaoTest {

    Logger log = Logger.getLogger(CustomerDaoTest.class.getName());

    String connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";

    CustomerDao customerDao = new CustomerDao(connectionToDatabaseCreate);
    AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseCreate);
    AddressDao addressDao = new AddressDao(connectionToDatabaseCreate);

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
        String countryTest1 = new String("Estonia");
        String cityTest1 = new String("Tallinn");
        Address addressTest1 = new Address(countryTest1, cityTest1);
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
                Advertisement.ServiceType.OFFER,
                new Category("CLEANING"),
                shouldGetCustomerById,
                addressTest1);

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

        Assert.assertEquals(newCustomerPassword, updatedCustomer.getPassword());
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

        List<Customer> getAllCustomers = customerDao.getAll();

        Assert.assertEquals(3, getAllCustomers.size());
    }

    @Test
    public void shouldDeleteCustomer() throws ParseException {

        log.info("...shouldDeleteCustomer...");

        CategoryDao categoryDao = new CategoryDao(connectionToDatabaseCreate);

        //create customer
        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");
        customerDao.save(customer);

        //create address
        String countryTest1 = new String("Estonia");
        String countryTest2 = new String("USA");
        String cityTest1 = new String("Tallinn");
        String cityTest2 = new String("LA");
        Address addressTest1 = new Address(countryTest1, cityTest1);
        Address addressTest2 = new Address(countryTest2, cityTest2);
        // addressDao.save(addressTest1);

        Category category = new Category("Rent");
        categoryDao.save(category);

        //create dates
        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString1);

        String startDateString2 = "21/02/2005";
        Date startDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString2);

        String endDateString2 = "13/01/2001";
        Date endDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString2);

        //create advertisement
        Advertisement advertisement1 = new Advertisement(
                "Clean Fast",
                "blablabla",
                "2.5",
                startDate1,
                endDate1,
                Advertisement.ServiceType.OFFER,
                category,
                customer,
                addressTest1);

        Advertisement advertisement2 = new Advertisement(
                "Car Rent",
                "ohohoho",
                "103.3",
                startDate2,
                endDate2,
                Advertisement.ServiceType.REQUEST,
                category,
                customer,
                addressTest2);

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);

        customer.setUserAdvertisements(Arrays.asList(advertisement1, advertisement2));

        Customer shouldBeSavedCustomer = customerDao.get(1L);

/*        Assert.assertNotNull(shouldBeSavedAd);
        shouldBeSavedAd.setCustomer(null);
        advertisementDao.update(shouldBeSavedAd);


        Advertisement shouldBeUpdatedAd = advertisementDao.get(2L);*/
        customerDao.delete(shouldBeSavedCustomer);

        Customer shouldBeDeletedCustomer = customerDao.get(1L);
        Assert.assertNull(shouldBeDeletedCustomer);
    }
}
