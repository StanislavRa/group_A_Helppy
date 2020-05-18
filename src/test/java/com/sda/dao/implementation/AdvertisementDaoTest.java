package com.sda.dao.implementation;

import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import com.sda.entity.Customer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import static com.sda.util.Constants.DATE_FORMAT;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdvertisementDaoTest {

    private static AddressDao addressDao;
    private static CustomerDao customerDao;
    private static AdvertisementDao advertisementDao;

    private static Customer customer;
    private static Advertisement advertisement1;
    private static Advertisement advertisement2;
    
    Logger log = Logger.getLogger(AdvertisementDaoTest.class.getName());

    @BeforeClass
    public static void setUp() throws ParseException {


        addressDao = new AddressDao();
        customerDao = new CustomerDao();
        advertisementDao = new AdvertisementDao();
        CategoryDao categoryDao = new CategoryDao();

        customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        Category rentSuperCategory = new Category(null, "Rent");

        Category rentSubCategory = new Category(rentSuperCategory, "Car Rent");

        advertisement1 = new Advertisement(
                "Clean Fast",
                "blablabla",
                "2.5",
                new SimpleDateFormat(DATE_FORMAT).parse("31/12/1998"),
                new SimpleDateFormat(DATE_FORMAT).parse("31/12/1998"),
                Advertisement.ServiceType.OFFER,
                rentSuperCategory,
                customer,
                new Address("Estonia", "Tallinn"));

        advertisement2 = new Advertisement(
                "Rent Car",
                "dudududu",
                "100",
                new SimpleDateFormat(DATE_FORMAT).parse("31/12/2008"),
                new SimpleDateFormat(DATE_FORMAT).parse("31/12/2009"),
                Advertisement.ServiceType.REQUEST,
                rentSubCategory,
                customer,
                new Address("USA", "LA"));
        Advertisement advertisementToBeDeleted = new Advertisement(
                "Rent Bike",
                "lallaala",
                "26.0",
                new SimpleDateFormat(DATE_FORMAT).parse("31/12/1996"),
                new SimpleDateFormat(DATE_FORMAT).parse("31/12/2006"),
                Advertisement.ServiceType.REQUEST,
                rentSubCategory,
                null,
                new Address("France", "Paris"));

        customerDao.save(customer);
        categoryDao.save(rentSuperCategory);
        categoryDao.save(rentSubCategory);

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);
        advertisementDao.save(advertisementToBeDeleted);
    }

    @Test
    public void should1SaveAdvertisement() {

        log.info("...shouldSaveAdvertisement...");

        Assert.assertNotNull(customerDao.get(1L));
        Assert.assertNotNull(advertisementDao.get(1L));
        Assert.assertNotNull(advertisementDao.get(2L));
    }

    @Test
    public void should2SaveAdvertisementWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveAdvertisementWithCreatedAndUpdatedTimeStamp...");

        Advertisement shouldGetAdvertisementById = advertisementDao.get(1L);

        Assert.assertNotNull(shouldGetAdvertisementById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAdvertisementById.getUPDATED_ON().toString());
    }

    @Test
    public void should3SaveAdvertisementAsListToCustomer() {

        log.info("...shouldSaveAdvertisementAsListToCustomer...");

        Assert.assertEquals(2,
                customerDao.getByLogin(
                        customer.getLogin()).getUserAdvertisements().size());
    }

    @Test
    public void should4GetAdvertisementById() {

        log.info("...shouldGetAdvertisementById...");

        Advertisement shouldGetAdvertisementById1 = advertisementDao.get(1L);
        Advertisement shouldGetAdvertisementById2 = advertisementDao.get(2L);

        Assert.assertEquals(advertisement1, shouldGetAdvertisementById1);
        Assert.assertEquals(advertisement2, shouldGetAdvertisementById2);
    }

    @Test
    public void should5GetAllAdvertisements() {

        log.info("...shouldGetAllAdvertisements...");

        List<Advertisement> getAllAdvertisements = advertisementDao.getAll();

        Assert.assertEquals(3, getAllAdvertisements.size());
    }

    @Test
    public void should6UpdateAdvertisementSubject() {

        log.info("...shouldUpdateAdvertisementSubject...");

        Advertisement shouldUpdateAdvertisementSubject = advertisementDao.get(1L);
        String newAdvertisementSubject = "Renting";
        shouldUpdateAdvertisementSubject.setSubject(newAdvertisementSubject);
        advertisementDao.update(shouldUpdateAdvertisementSubject);
        Advertisement updatedAdvertisement = advertisementDao.get(1L);

        Assert.assertEquals(newAdvertisementSubject, updatedAdvertisement.getSubject());
    }

    @Test
    public void should7DeleteAdvertisement() {

        log.info("...shouldDeleteAdvertisement...");

        Advertisement shouldBeSavedAd = advertisementDao.get(3L);

        advertisementDao.delete(shouldBeSavedAd);

        Address shouldBeDeletedAd = addressDao.get(3L);
        Assert.assertNull(shouldBeDeletedAd);
    }
}
