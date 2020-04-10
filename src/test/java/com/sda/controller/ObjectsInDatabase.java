package com.sda.controller;

import com.sda.dao.implementation.*;
import com.sda.entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjectsInDatabase {

    public static void main(String[] args) throws ParseException {

        //create Dao controllers
        String connectionToDatabaseCreate  = "hibernateUnitTest.cfg.xml";

        CustomerDao customerDao = new CustomerDao(connectionToDatabaseCreate);
        AdvertisementDao advertisementDao = new AdvertisementDao(connectionToDatabaseCreate);
        CategoryDao categoryDao = new CategoryDao(connectionToDatabaseCreate);
        CityDao cityDao = new CityDao(connectionToDatabaseCreate);
        CountryDao countryDao = new CountryDao(connectionToDatabaseCreate);
        AddressDao addressDao = new AddressDao(connectionToDatabaseCreate);

        //create country address
        Country country1 = new Country("Estonia");
        countryDao.save(country1);
        Country country2 = new Country("Sweden");
        countryDao.save(country2);
        Country country3 = new Country("Finland");
        countryDao.save(country3);

        //create city address
        City city1 = new City("Tallinn");
        city1.setCountryName(country1);
        cityDao.save(city1);
        City city2 = new City("Tartu");
        city2.setCountryName(country1);
        cityDao.save(city2);
        City city3 = new City("Narva");
        city3.setCountryName(country1);
        cityDao.save(city3);
        City city4 = new City("Helsinki");
        city4.setCountryName(country2);
        cityDao.save(city4);
        City city5 = new City("Tampere");
        city5.setCountryName(country2);
        cityDao.save(city5);
        City city6 = new City("Stockholm");
        city6.setCountryName(country3);
        cityDao.save(city2);
        City city7 = new City("Upsala");
        city7.setCountryName(country3);
        cityDao.save(city7);

        //create country address
        Address address1 = new Address(country1, city1);
        addressDao.save(address1);
        Address address2 = new Address(country2, city6);
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
