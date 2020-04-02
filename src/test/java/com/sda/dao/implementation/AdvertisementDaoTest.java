package com.sda.dao.implementation;

import com.sda.controller.AdDetailsViewController;
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
 * @author StanislavR
 */

public class AdvertisementDaoTest {

    Logger log = Logger.getLogger(AddressDaoTest.class.getName());
    CustomerDao customerDao = new CustomerDao("hibernateDemi.cfg.xml");
    AdvertisementDao advertisementDao = new AdvertisementDao("hibernateDemi.cfg.xml");
    CategoryDao categoryDao = new CategoryDao("hibernateDemi.cfg.xml");
    AddressDao addressDao = new AddressDao("hibernateDemi.cfg.xml");

    @Test
    public void shouldAddAdvertisement() throws ParseException {
        // create new customer
        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        customerDao.save(customer);
        Assert.assertNotNull(customerDao.get(1L));

        // create new address
        Address addressTest1 = new Address("Estonia", "Tallinn", "Parnu mnt");
        addressDao.save(addressTest1);

        // create new advertisement
        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString1);

        Advertisement advertisement1 = new Advertisement(
                "Clean Fast",
                "One, Two, One Two Three Four!!!",
                "2.5",
                startDate1,
                endDate1,
                "OFFER",
                new Category("CLEANING"),
                customer);
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);

        // assign address to advertisement
        advertisement1.setAddress(addressTest1);

        // assign customer to advertisement
        advertisement1.setCustomer(customer);

        advertisementDao.save(advertisement1);

        Assert.assertNotNull(advertisementDao.get(1L));
    }

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
        Address addressTest1 = new Address("Estonia", "Tallinn", "Parnu mnt");
        addressDao.save(addressTest1);

        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString1);

//        String startDateString2 = "21/02/2005";
//        Date startDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString2);
//
//        String endDateString2 = "13/01/2001";
//        Date endDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString2);

        Advertisement advertisement1 = new Advertisement(
                "CCCCLEAN",
                "Cleaning is gOOOd",
                "2.5",
                startDate1,
                endDate1,
                "OFFER",
                new Category("Cleannnn"),
                customer);
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);

        advertisement1.setAddress(addressTest1);

//        Advertisement advertisement2 = new Advertisement(
//                "Car Rent",
//                "ohohoho",
//                "103.3",
//                startDate2,
//                endDate2,
//                "REQUEST",
//                new Category("RENTING"),
//                customer);
//        advertisement2.setServiceState(Advertisement.ServiceState.INACTIVE);

        advertisementDao.save(advertisement1);
//        advertisementDao.save(advertisement2);

        Assert.assertNotNull(advertisementDao.get(1L));
//        Assert.assertNotNull(advertisementDao.get(2L));
    }


    @Test
    public void shouldGetAdvertisementById() throws ParseException {

        log.info("...shouldGetAdvertisementById...");

        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        customerDao.save(customer);
        Category rentSuperCategory = new Category(null, "Rent");
        categoryDao.save(rentSuperCategory);

        Category rentSubCategory1 = new Category(rentSuperCategory,"Car Rent");
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
                "OFFER",
                rentSubCategory1,
                customer);

        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);

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
                "OFFER",
                new Category("CLEANING"),
                customer);
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);

        Advertisement advertisement2 = new Advertisement(
                "Car Rent",
                "ohohoho",
                "103.3",
                startDate2,
                endDate2,
                "REQUEST",
                new Category("RENTING"),
                customer);
        advertisement2.setServiceState(Advertisement.ServiceState.INACTIVE);

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);

        List<Advertisement> getAllAdvertisements =  advertisementDao.getAll();

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
                customer);
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);


        advertisementDao.save(advertisement1);
        Assert.assertNotNull(advertisementDao.get(1L));

        Advertisement shouldUpdateAdvertisementSubject = advertisementDao.get(1L);

        String newAdvertisementSubject = "Renting";

        shouldUpdateAdvertisementSubject.setSubject(newAdvertisementSubject);

        advertisementDao.update(shouldUpdateAdvertisementSubject);

        Advertisement updatedAdvertisement = advertisementDao.get(1L);

        Assert.assertEquals(newAdvertisementSubject,updatedAdvertisement.getSubject());
    }


    @Test
    public void shouldSaveAdvertisementWithCreatedAndUpdatedTimeStamp() throws ParseException {

        log.info("...shouldSaveAdvertisementWithCreatedAndUpdatedTimeStamp...");
        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        customerDao.save(customer);

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
                customer);
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);


        advertisementDao.save(advertisement1);

        Advertisement shouldGetAdvertisementById = advertisementDao.get(1L);

        Assert.assertNotNull(shouldGetAdvertisementById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAdvertisementById.getUPDATED_ON().toString());

    }

    @Test
    public void shouldSaveAdvertisementAsListToCustomer() throws ParseException {

        log.info("...shouldSaveAdvertisementAsListToCustomer...");

        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

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
                "OFFER",
                new Category("CLEANING"),
                customer);
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);

        Advertisement advertisement2 = new Advertisement(
                "Car Rent",
                "ohohoho",
                "103.3",
                startDate2,
                endDate2,
                "REQUEST",
                new Category("RENTING"),
                customer);
        advertisement2.setServiceState(Advertisement.ServiceState.INACTIVE);
        customer.setUserAdvertisements(Arrays.asList(advertisement1, advertisement2));

        customerDao.save(customer);

        Assert.assertEquals(2,
                customerDao.getByLogin(
                        customer.getLogin()).getUserAdvertisements().size());
    }


}
