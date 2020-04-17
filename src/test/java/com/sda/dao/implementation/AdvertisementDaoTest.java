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

public class AdvertisementDaoTest {

    Logger log = Logger.getLogger(AdvertisementDaoTest.class.getName());
    String connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";

    CustomerDao customerDao = new CustomerDao(connectionToDatabaseCreate);
    AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseCreate);
    CategoryDao categoryDao = new CategoryDao(connectionToDatabaseCreate);
    AddressDao addressDao = new AddressDao(connectionToDatabaseCreate);
    CountryDao countryDao = new CountryDao(connectionToDatabaseCreate);
    CityDao cityDao = new CityDao(connectionToDatabaseCreate);

    @Test
    public void shouldSaveAdvertisement() throws ParseException {

        log.info("...shouldSaveAdvertisement...");

        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        customerDao.save(customer);
        Assert.assertNotNull(customerDao.get(1L));

        //create address
        String countryTest1 = new String("USA");
        String cityTest1 = new String("LA");
        Address addressTest1 = new Address(countryTest1, cityTest1);
        Address addressTest2 = new Address(countryTest1, cityTest1);

        Category category = new Category("Rent");
        categoryDao.save(category);

        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString1);

        String startDateString2 = "21/02/2005";
        Date startDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString2);

        String endDateString2 = "13/01/2001";
        Date endDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString2);

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

        Assert.assertNotNull(advertisementDao.get(1L));
        Assert.assertNotNull(advertisementDao.get(2L));
    }


    @Test
    public void shouldGetAdvertisementById() throws ParseException {

        log.info("...shouldGetAdvertisementById...");

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

        Category rentSuperCategory = new Category(null, "Rent");
        categoryDao.save(rentSuperCategory);

        Category rentSubCategory1 = new Category(rentSuperCategory, "Car Rent");
        categoryDao.save(rentSubCategory1);


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
                rentSubCategory1,
                customer,
                addressTest1);

        advertisementDao.save(advertisement1);

        Advertisement shouldGetAdvertisementById = advertisementDao.get(1L);

        Assert.assertEquals(advertisement1, shouldGetAdvertisementById);
    }

    @Test
    public void shouldGetAllAdvertisements() throws ParseException {

        log.info("...shouldGetAllAdvertisements...");

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

        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString1);

        String startDateString2 = "21/02/2005";
        Date startDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString2);

        String endDateString2 = "13/01/2001";
        Date endDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString2);

        Advertisement advertisement1 = new Advertisement(
                "Clean Fast",
                "blablabla",
                "2.5",
                startDate1,
                endDate1,
                Advertisement.ServiceType.OFFER,
                new Category("CLEANING"),
                customer,
                addressTest1);

        Advertisement advertisement2 = new Advertisement(
                "Car Rent",
                "ohohoho",
                "103.3",
                startDate2,
                endDate2,
                Advertisement.ServiceType.REQUEST,
                new Category("RENTING"),
                customer,
                addressTest1);

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);

        List<Advertisement> getAllAdvertisements = advertisementDao.getAll();

        Assert.assertEquals(2, getAllAdvertisements.size());
    }

    @Test
    public void shouldUpdateAdvertisementSubject() throws ParseException {

        log.info("...shouldUpdateAdvertisementSubject...");

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
                customer,
                addressTest1);

        advertisementDao.save(advertisement1);
        Assert.assertNotNull(advertisementDao.get(1L));

        Advertisement shouldUpdateAdvertisementSubject = advertisementDao.get(1L);

        String newAdvertisementSubject = "Renting";

        shouldUpdateAdvertisementSubject.setSubject(newAdvertisementSubject);

        advertisementDao.update(shouldUpdateAdvertisementSubject);

        Advertisement updatedAdvertisement = advertisementDao.get(1L);

        Assert.assertEquals(newAdvertisementSubject, updatedAdvertisement.getSubject());
    }


    @Test
    public void shouldSaveAdvertisementWithCreatedAndUpdatedTimeStamp() throws ParseException {

        log.info("...shouldSaveAdvertisementWithCreatedAndUpdatedTimeStamp...");
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
                customer,
                addressTest1);

        advertisementDao.save(advertisement1);

        Advertisement shouldGetAdvertisementById = advertisementDao.get(1L);

        Assert.assertNotNull(shouldGetAdvertisementById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAdvertisementById.getUPDATED_ON().toString());
    }

    @Test
    public void shouldSaveAdvertisementAsListToCustomer() throws ParseException {

        log.info("...shouldSaveAdvertisementAsListToCustomer...");

        //create customer
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
                new Category("CLEANING"),
                customer,
                addressTest1);

        Advertisement advertisement2 = new Advertisement(
                "Car Rent",
                "ohohoho",
                "103.3",
                startDate2,
                endDate2,
                Advertisement.ServiceType.REQUEST,
                new Category("RENTING"),
                customer,
                addressTest1);

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);

        customer.setUserAdvertisements(Arrays.asList(advertisement1, advertisement2));

        Assert.assertEquals(2,
                customerDao.getByLogin(
                        customer.getLogin()).getUserAdvertisements().size());
    }

    @Test
    public void shouldDeleteAdvertisement() throws ParseException {

        log.info("...shouldDeleteAdvertisement...");

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

        Advertisement shouldBeSavedAd = advertisementDao.get(2L);

        advertisementDao.delete(shouldBeSavedAd);

        Address shouldBeDeletedAd = addressDao.get(2L);
        Assert.assertNull(shouldBeDeletedAd);

    }
}
