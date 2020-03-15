package com.sda.dao.implementation;

import com.sda.entity.Advertisement;
import com.sda.entity.Customer;
import com.sda.entity.User;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author StanislavR
 */
public class AdvertisementDaoTest {
    @Test
    public void shouldSaveAdvertisement() {

        AdvertisementDao advertisementDao = new AdvertisementDao();
        CustomerDao customerDao = new CustomerDao();


        Advertisement advertisement1 = new Advertisement();
        advertisement1.setSubject("Cleaning Apartment 24/7");
        advertisement1.setPrice(new BigDecimal("32.45"));

        Advertisement advertisement2 = new Advertisement();
        advertisement2.setSubject("Cleaning Houses Super!");
        advertisement2.setPrice(new BigDecimal("100.5"));

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);

        Customer customer = new Customer();
        customer.setLogin("aa");
        customer.setPassword("qwerty");
        customer.setFullName("Anton");

        customer.setUserAdvertisements(Arrays.asList(advertisement1,advertisement2));

        int adsSize = customer.getUserAdvertisements().size();

        System.out.println(adsSize);

        customerDao.save(customer);


    }
}
