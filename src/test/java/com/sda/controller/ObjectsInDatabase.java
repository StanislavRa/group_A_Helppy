package com.sda.controller;

import com.sda.dao.implementation.*;
import com.sda.entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjectsInDatabase {

    public static void main(String[] args) throws ParseException {


        //create Dao controllers

        String connectionToDatabaseCreate  = "oleksHibernateCreateTest.cfg.xml";
        String connectionToDatabaseValidate  = "oleksHibernateValidateTest.cfg.xml";

        CustomerDao customerDao = new CustomerDao(connectionToDatabaseCreate);
        AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseCreate);
        CategoryDao categoryDao = new CategoryDao(connectionToDatabaseCreate);
        AddressCityDao addressCityDao = new AddressCityDao(connectionToDatabaseCreate);
        AddressCountryDao addressCountryDao = new AddressCountryDao(connectionToDatabaseCreate);
        AddressDao addressDao = new AddressDao(connectionToDatabaseCreate);


        //create city address
        AddressCity addressCity1 = new AddressCity("Tallinn");
        addressCityDao.save(addressCity1);
        AddressCity addressCity2 = new AddressCity("Tartu");
        addressCityDao.save(addressCity2);
        AddressCity addressCity3 = new AddressCity("Narva");
        addressCityDao.save(addressCity3);
        AddressCity addressCity4 = new AddressCity("Helsinki");
        addressCityDao.save(addressCity4);
        AddressCity addressCity5 = new AddressCity("Tampere");
        addressCityDao.save(addressCity5);
        AddressCity addressCity6 = new AddressCity("Stockholm");
        addressCityDao.save(addressCity2);
        AddressCity addressCity7 = new AddressCity("Upsala");
        addressCityDao.save(addressCity7);

        //create country address
        AddressCountry addressCountry1 = new AddressCountry("Estonia");
        addressCountryDao.save(addressCountry1);
        AddressCountry addressCountry2 = new AddressCountry("Sweden");
        addressCountryDao.save(addressCountry2);
        AddressCountry addressCountry3 = new AddressCountry("Finland");
        addressCountryDao.save(addressCountry3);

        //create country address
        Address address1 = new Address(addressCountry1, addressCity1);
        addressDao.save(address1);
        Address address2 = new Address(addressCountry2, addressCity6);
        addressDao.save(address2);


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
                Advertisement.ServiceType.OFFER,
                rentSubCategory1,
                customer1);
        advertisement1.setServiceState(Advertisement.ServiceState.ACTIVE);
        advertisement1.setAddress(address1);


        Advertisement advertisement2 = new Advertisement(
                "Car Rent",
                "ohohoho",
                "103.3",
                startDate2,
                endDate2,
                Advertisement.ServiceType.REQUEST,
                rentSubCategory1,
                customer2);
        advertisement2.setServiceState(Advertisement.ServiceState.ACTIVE);
        advertisement2.setAddress(address2);

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);
    }
}
