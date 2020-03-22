package com.sda.dao.implementation;

import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import com.sda.entity.Customer;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author StanislavR
 */
public class AdvertisementDaoTest {
    @Test
    public void shouldSaveAdvertisement() throws ParseException {

        CustomerDao customerDao = new CustomerDao();

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
