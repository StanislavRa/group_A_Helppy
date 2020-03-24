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
 * @author StanislavR
 */

public class AdvertisementDaoTest {
    Logger log = Logger.getLogger(AddressDaoTest.class.getName());
    CustomerDao customerDao = new CustomerDao("hibernateTest.cfg.xml");
    AdvertisementDao advertisementDao = new AdvertisementDao("hibernateTest.cfg.xml");


    @Test
    public void shouldSaveAdvertisement() throws ParseException {

        log.info("...shouldSaveAdvertisement...");

        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        customerDao.save(customer);
        Assert.assertNotNull(customerDao.get(1L));

        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String startDateString2 = "21/02/2005";
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString2);

        String endDateString2 = "13/01/2001";
        Date endDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        Advertisement advertisement1 = new Advertisement(
                "Clean Fast",
                "blablabla",
                "2.5",
                startDate,
                endDate1,
                "OFFER",
                new Category("CLEANING"),
                customer);
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);

        Advertisement advertisement2 = new Advertisement(
                "Car Rent",
                "ohohoho",
                "103.3",
                startDate,
                endDate1,
                "REQUEST",
                new Category("RENTING"),
                customer);
        advertisement2.setServiceState(Advertisement.ServiceState.INACTIVE);

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);

        Assert.assertNotNull(advertisementDao.get(1L));
        Assert.assertNotNull(advertisementDao.get(2L));
    }

/*
    //Not working, still be in process
    @Test
    public void shouldGetAdvertisementById() throws ParseException {

        log.info("...shouldGetAdvertisementById...");

        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);


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

        Assert.assertEquals(shouldGetAdvertisementById, advertisement1);

    }*/


    @Test
    public void shouldGetAllAdvertisements() throws ParseException {

        log.info("...shouldGetAllAdvertisements...");

        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        customerDao.save(customer);
        Assert.assertNotNull(customerDao.get(1L));

        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String startDateString2 = "21/02/2005";
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString2);

        String endDateString2 = "13/01/2001";
        Date endDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        Advertisement advertisement1 = new Advertisement(
                "Clean Fast",
                "blablabla",
                "2.5",
                startDate,
                endDate1,
                "OFFER",
                new Category("CLEANING"),
                customer);
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);

        Advertisement advertisement2 = new Advertisement(
                "Car Rent",
                "ohohoho",
                "103.3",
                startDate,
                endDate1,
                "REQUEST",
                new Category("RENTING"),
                customer);
        advertisement2.setServiceState(Advertisement.ServiceState.INACTIVE);

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);

        List<Advertisement> getAllAdvertisements =  advertisementDao.getAll();

        Assert.assertEquals(2, getAllAdvertisements.size());
    }
/*
    //Not working, still be in process
    @Test
    public void shouldUpdateAdvertisementSubject() throws ParseException {

        log.info("...shouldUpdateAdvertisementSubject...");

        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);


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
*/

    @Test
    public void shouldSaveAdvertisementWithCreatedAndUpdatedTimeStamp() throws ParseException {

        log.info("...shouldSaveAdvertisementWithCreatedAndUpdatedTimeStamp...");
        Customer customer = new Customer();
        customer.setLogin("Pjotr");
        customer.setPassword("123456");
        customer.setFullName("Petr III");

        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);


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

        Assert.assertNotNull(advertisement1.getCREATED_ON());
        Assert.assertNotNull(advertisement1.getUPDATED_ON());

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
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String startDateString2 = "21/02/2005";
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString2);

        String endDateString2 = "13/01/2001";
        Date endDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        Advertisement advertisement1 = new Advertisement(
                "Clean Fast",
                "blablabla",
                "2.5",
                startDate,
                endDate1,
                "OFFER",
                new Category("CLEANING"),
                customer);
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);

        Advertisement advertisement2 = new Advertisement(
                "Car Rent",
                "ohohoho",
                "103.3",
                startDate,
                endDate1,
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
