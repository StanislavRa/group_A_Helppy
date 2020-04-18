package com.sda.dao.implementation;

import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import com.sda.entity.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

/**
 * Work only one-by-one
 */

public class AdvertisementDaoTest {

    String connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";

    Logger log = Logger.getLogger(AdvertisementDaoTest.class.getName());

    CustomerDao customerDao;
    AdvertisementDao advertisementDao;
    CategoryDao categoryDao;
    AddressDao addressDao;

    Customer customer;
    Category rentSuperCategory;
    Category rentSubCategory;
    Advertisement advertisement1;
    Advertisement advertisement2;

    @Before
    public void setUp() throws ParseException {

        addressDao = new AddressDao(connectionToDatabaseCreate);
        customerDao = new CustomerDao(connectionToDatabaseCreate);
        advertisementDao = new AdvertisementDao(connectionToDatabaseCreate);
        categoryDao = new CategoryDao(connectionToDatabaseCreate);

        customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        rentSuperCategory = new Category(null, "Rent");

        rentSubCategory = new Category(rentSuperCategory, "Car Rent");

        advertisement1 = new Advertisement(
                "Clean Fast",
                "blablabla",
                "2.5",
                new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998"),
                new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998"),
                Advertisement.ServiceType.OFFER,
                rentSuperCategory,
                customer,
                new Address("Estonia", "Tallinn"));

        advertisement2 = new Advertisement(
                "Rent Car",
                "dudududu",
                "100",
                new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2008"),
                new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2009"),
                Advertisement.ServiceType.REQUEST,
                rentSubCategory,
                customer,
                new Address("USA", "LA"));

        customerDao.save(customer);
        categoryDao.save(rentSuperCategory);
        categoryDao.save(rentSubCategory);

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);
    }

    @Test
    public void shouldSaveAdvertisement() {

        log.info("...shouldSaveAdvertisement...");

        Assert.assertNotNull(customerDao.get(1L));
        Assert.assertNotNull(advertisementDao.get(1L));
        Assert.assertNotNull(advertisementDao.get(2L));
    }


    @Test
    public void shouldGetAdvertisementById() {

        log.info("...shouldGetAdvertisementById...");

        Advertisement shouldGetAdvertisementById1 = advertisementDao.get(1L);
        Advertisement shouldGetAdvertisementById2 = advertisementDao.get(2L);

        Assert.assertEquals(advertisement1, shouldGetAdvertisementById1);
        Assert.assertEquals(advertisement2, shouldGetAdvertisementById2);
    }

    @Test
    public void shouldGetAllAdvertisements() {

        log.info("...shouldGetAllAdvertisements...");

        List<Advertisement> getAllAdvertisements = advertisementDao.getAll();

        Assert.assertEquals(2, getAllAdvertisements.size());
    }

    @Test
    public void shouldUpdateAdvertisementSubject() {

        log.info("...shouldUpdateAdvertisementSubject...");

        Advertisement shouldUpdateAdvertisementSubject = advertisementDao.get(1L);
        String newAdvertisementSubject = "Renting";
        shouldUpdateAdvertisementSubject.setSubject(newAdvertisementSubject);
        advertisementDao.update(shouldUpdateAdvertisementSubject);
        Advertisement updatedAdvertisement = advertisementDao.get(1L);

        Assert.assertEquals(newAdvertisementSubject, updatedAdvertisement.getSubject());
    }


    @Test
    public void shouldSaveAdvertisementWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveAdvertisementWithCreatedAndUpdatedTimeStamp...");

        Advertisement shouldGetAdvertisementById = advertisementDao.get(1L);

        Assert.assertNotNull(shouldGetAdvertisementById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAdvertisementById.getUPDATED_ON().toString());
    }

    @Test
    public void shouldSaveAdvertisementAsListToCustomer() {

        log.info("...shouldSaveAdvertisementAsListToCustomer...");

        Assert.assertEquals(2,
                customerDao.getByLogin(
                        customer.getLogin()).getUserAdvertisements().size());
    }

    @Test
    public void shouldDeleteAdvertisement() {

        log.info("...shouldDeleteAdvertisement...");

        Advertisement shouldBeSavedAd = advertisementDao.get(2L);

        advertisementDao.delete(shouldBeSavedAd);

        Address shouldBeDeletedAd = addressDao.get(2L);
        Assert.assertNull(shouldBeDeletedAd);
    }
}
