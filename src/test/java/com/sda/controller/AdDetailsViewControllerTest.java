package com.sda.controller;

import com.sda.dao.implementation.AdvertisementDao;
import com.sda.dao.implementation.CategoryDao;
import com.sda.dao.implementation.CustomerDao;
import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import com.sda.entity.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdDetailsViewControllerTest {

    @Test
    public void shouldCreateAdvertisementDao() throws ParseException {
        String connectionToDatabaseCreate  = "hibernateUnitTest.cfg.xml";
        AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseCreate);
        CustomerDao customerDao = new CustomerDao(connectionToDatabaseCreate);
        CategoryDao categoryDao = new CategoryDao(connectionToDatabaseCreate);

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
        //addressDao.save(addressTest1);

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
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);

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
        advertisement2.setServiceState(Advertisement.ServiceState.INACTIVE);

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);

        Assert.assertNotNull(advertisementDao.get(1L));
        Assert.assertNotNull(advertisementDao.get(2L));
    }
}
