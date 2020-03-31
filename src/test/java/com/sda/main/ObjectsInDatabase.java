package com.sda.main;

import com.sda.dao.implementation.AddressDao;
import com.sda.dao.implementation.AdvertisementDao;
import com.sda.dao.implementation.CategoryDao;
import com.sda.dao.implementation.CustomerDao;
import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import com.sda.entity.Customer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjectsInDatabase {

    public static void main(String[] args) throws ParseException {


        //create Dao controllers
         CustomerDao customerDao = new CustomerDao("oleksHibernateTest.cfg.xml");
         AdvertisementDao advertisementDao = new AdvertisementDao("oleksHibernateTest.cfg.xml");
         CategoryDao categoryDao = new CategoryDao("oleksHibernateTest.cfg.xml");
         AddressDao addressDao = new AddressDao("oleksHibernateTest.cfg.xml");

        //create address
        Address addressTallinn = new Address("Tallinn");
        addressDao.save(addressTallinn);
        Address addressTartu = new Address("Tartu");
        addressDao.save(addressTartu);
        Address addressParnu = new Address("Parnu");
        addressDao.save(addressParnu);

        //create dates
        String startDateString1 = "31/12/1998";
        Date startDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString1);

        String endDateString1 = "31/12/1998";
        Date endDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString1);

        String startDateString2 = "21/02/2005";
        Date startDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString2);

        String endDateString2 = "13/01/2001";
        Date endDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString2);

        //Create customer
        Customer customer1 = new Customer("Pjotr", "123456", "Petr III");
        customerDao.save(customer1);
        Customer customer2 = new Customer("myName", "123456", "Petr Petr");
        customerDao.save(customer1);

        //create category
        Category rentSuperCategory = new Category(null, "Rent");
        categoryDao.save(rentSuperCategory);

        Category rentSubCategory1 = new Category(rentSuperCategory, "Car Rent");
        categoryDao.save(rentSubCategory1);

        Category rentSubCategory2 = new Category(rentSuperCategory, "Ship Rent");
        categoryDao.save(rentSubCategory2);

        //create advertisement
        Advertisement advertisement1 = new Advertisement(
                "Clean Fast",
                "blablabla",
                "2.5",
                startDate1,
                endDate1,
                "OFFER",
                rentSubCategory1,
                customer1);
        advertisement1.setServiceState(Advertisement.ServiceState.INACTIVE);
        advertisement1.setAddress(addressTallinn);

        Advertisement advertisement2 = new Advertisement(
                "Car Rent",
                "ohohoho",
                "103.3",
                startDate2,
                endDate2,
                "REQUEST",
                rentSubCategory1,
                customer2);
        advertisement2.setServiceState(Advertisement.ServiceState.INACTIVE);
        advertisement2.setAddress(addressTallinn);

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);
    }
}
