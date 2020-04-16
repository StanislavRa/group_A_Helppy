package com.sda.controller;

import com.sda.dao.implementation.*;
import com.sda.entity.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AllAdsViewControllerTest {

    String connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";
    //String connectionToDatabaseCreate = "hibernateCreate.cfg.xml";

    //declare dao
    CountryDao countryDao;
    CityDao cityDao;
    AddressDao addressDao;
    CategoryDao categoryDao;
    CustomerDao customerDao;
    AdvertisementDao advertisementDao;

    //declare country
    Country estoniaCountry;
    Country finlandCountry;
    Country swedenCountry;

    //declare cities
    City estonianCity1;
    City estonianCity2;
    City estonianCity3;

    City finishCity1;
    City finishCity2;
    City finishCity3;

    City swedishCity1;
    City swedishCity2;
    City swedishCity3;

    //declare addresses
    Address estonianAddress1;
    Address estonianAddress2;
    Address estonianAddress3;

    Address finishAddress1;
    Address finishAddress2;

    //declare categories
    Category superCategoryRent;
    Category superCategoryClean;

    Category subCategoryRent1;
    Category subCategoryRent2;
    Category subCategoryClean1;
    Category subCategoryClean2;

    //declare customers
    Customer customer1;
    Customer customer2;
    Customer customer3;
    Customer customer4;

    //declare advertisement
    Advertisement advertisement1;
    Advertisement advertisement2;
    Advertisement advertisement3;
    Advertisement advertisement4;
    Advertisement advertisement5;

    @Before
    public void setUp() throws ParseException {

        countryDao = new CountryDao(connectionToDatabaseCreate);
        cityDao = new CityDao(connectionToDatabaseCreate);
        addressDao = new AddressDao(connectionToDatabaseCreate);
        categoryDao = new CategoryDao(connectionToDatabaseCreate);
        customerDao = new CustomerDao(connectionToDatabaseCreate);
        advertisementDao = new AdvertisementDao(connectionToDatabaseCreate);

        //create countries
        estoniaCountry = new Country("Estonia");
        finlandCountry = new Country("Finland");
        swedenCountry = new Country("Sweden");

        countryDao.save(estoniaCountry);
        countryDao.save(finlandCountry);
        countryDao.save(swedenCountry);

        //create cities
        estonianCity1 = new City("Tallinn");
        estonianCity1.setCountry(estoniaCountry);
        estonianCity2 = new City("Narva");
        estonianCity2.setCountry(estoniaCountry);
        estonianCity3 = new City("Tartu");
        estonianCity3.setCountry(estoniaCountry);

        cityDao.save(estonianCity1);
        cityDao.save(estonianCity2);
        cityDao.save(estonianCity3);

        finishCity1 = new City("Helsinki");
        finishCity1.setCountry(finlandCountry);
        finishCity2 = new City("Tampere");
        finishCity2.setCountry(finlandCountry);
        finishCity3 = new City("Turku");
        finishCity3.setCountry(finlandCountry);

        cityDao.save(finishCity1);
        cityDao.save(finishCity2);
        cityDao.save(finishCity3);

        swedishCity1 = new City("Stockholm");
        swedishCity1.setCountry(swedenCountry);
        swedishCity2 = new City("Upsala");
        swedishCity2.setCountry(swedenCountry);
        swedishCity3 = new City("Malmö");
        swedishCity3.setCountry(swedenCountry);

        cityDao.save(swedishCity1);
        cityDao.save(swedishCity2);
        cityDao.save(swedishCity3);

        //create addresses
        estonianAddress1 = new Address(estoniaCountry.getCountryName(), estonianCity2.getCityName());
        estonianAddress2 = new Address(estoniaCountry.getCountryName(), estonianCity1.getCityName());
        estonianAddress3 = new Address(estoniaCountry.getCountryName(), estonianCity3.getCityName());

        finishAddress1 = new Address(finishCity1.getCityName(), finlandCountry.getCountryName());
        finishAddress2 = new Address(finishCity2.getCityName(), finlandCountry.getCountryName());

        addressDao.save(estonianAddress1);
        addressDao.save(estonianAddress2);
        addressDao.save(estonianAddress3);
        addressDao.save(finishAddress1);
        addressDao.save(finishAddress2);

        //create category
        superCategoryRent = new Category("Rent");
        superCategoryClean = new Category("Clean");

        categoryDao.save(superCategoryRent);
        categoryDao.save(superCategoryClean);

        //create customer
        customer1 = new Customer("Pjotr", "123456", "Petr III");
        customer2 = new Customer("Dima", "qwe", "Dmitry Peskov");
        customer3 = new Customer("Olga", "0000", "Olga Demina");
        customer4 = new Customer("John", "1111", "John Smith");

        customerDao.save(customer1);
        customerDao.save(customer2);
        customerDao.save(customer3);
        customerDao.save(customer4);

        //create advertisements
        advertisement1 = new
                Advertisement(
                "Clean Fast",
                "blablabla",
                "2.5",
                new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998"),
                new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2520"),
                Advertisement.ServiceType.OFFER,
                superCategoryRent,
                customer2,
                estonianAddress2);

        advertisement2 = new Advertisement(
                "Car Rent",
                "some dummy description2",
                "103.3",
                new SimpleDateFormat("dd/MM/yyyy").parse("21/02/2005"),
                new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2520"),
                Advertisement.ServiceType.REQUEST,
                superCategoryRent,
                customer1,
                estonianAddress1);

        advertisement3 = new Advertisement(
                "Clean Your Office!",
                "some dummy description3",
                "152",
                new SimpleDateFormat("dd/MM/yyyy").parse("13/01/2001"),
                new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2019"),
                Advertisement.ServiceType.REQUEST,
                superCategoryClean,
                customer2,
                estonianAddress3);
        advertisement4 = new Advertisement(
                "Rent Equipment from Us!!",
                "some dummy description4",
                "152",
                new SimpleDateFormat("dd/MM/yyyy").parse("13/01/2019"),
                new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2022"),
                Advertisement.ServiceType.OFFER,
                superCategoryClean,
                customer3,
                finishAddress1);

        advertisement5 = new Advertisement(
                "Clean your home!!",
                "some dummy description5",
                "152",
                new SimpleDateFormat("dd/MM/yyyy").parse("13/01/2019"),
                new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2022"),
                Advertisement.ServiceType.REQUEST,
                superCategoryClean,
                customer3,
                finishAddress2);

        advertisementDao.save(advertisement1);
        advertisementDao.save(advertisement2);
        advertisementDao.save(advertisement3);
        advertisementDao.save(advertisement4);
        advertisementDao.save(advertisement5);
    }

    @After
    public void tearDown() {

        cityDao.deleteAll();
        countryDao.deleteAll();
        customerDao.deleteAll();
        categoryDao.deleteAll();

    }

    @Test
    public void shouldGetListOfCityNames() throws ParseException {

        String estoniaCountryTest = estoniaCountry.getCountryName();
        List<String> getAllAdvertisementsByCountry = new AllAdsViewController().getListOfCityNamesByCountry(
                estoniaCountryTest,
                cityDao);

        Assert.assertEquals(3, getAllAdvertisementsByCountry.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByCategory() throws ParseException {

        String superCategoryRentTest = superCategoryRent.getName();
        System.out.println(superCategoryRentTest);
        List<Advertisement> getAllAdvertisementsByCategory = new AllAdsViewController().findActiveAdvertisementByCategory(
                superCategoryRentTest);

        Assert.assertEquals(2, getAllAdvertisementsByCategory.size());
    }


    @Test
    public void shouldFindActiveAdvertisementByCity() throws ParseException {

        String tallinnCityTest = estonianCity1.getCityName();
        System.out.println(tallinnCityTest);
        List<Advertisement> getAllAdvertisementsByCity = new AllAdsViewController().findActiveAdvertisementByCity(
                tallinnCityTest);

        Assert.assertEquals(1, getAllAdvertisementsByCity.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByCountry() throws ParseException {

        String estoniaCountryTest = estoniaCountry.getCountryName();
        List<Advertisement> getAllAdvertisementsByCountry = new AllAdsViewController().findActiveAdvertisementByCountry(
                estoniaCountryTest);

        Assert.assertEquals(2, getAllAdvertisementsByCountry.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByPrice() throws ParseException {

        List<Advertisement> getAllAdvertisementsByPrice = new AllAdsViewController().findActiveAdvertisementByPrice(
                new BigDecimal(151),
                new BigDecimal(170));

        Assert.assertEquals(2, getAllAdvertisementsByPrice.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByDate() throws ParseException {

        String startDateString2 = "11/01/2019";
        Date startDateTest = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString2);

        String endDateString2 = "01/01/2023";
        Date endDaterTest = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString2);

        List<Advertisement> getAllAdvertisementsByDate = new AllAdsViewController().findActiveAdvertisementByDate(
                startDateTest, endDaterTest);

        Assert.assertEquals(2, getAllAdvertisementsByDate.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByServiceType() throws ParseException {

        List<Advertisement> getAllAdvertisementsByDate = new AllAdsViewController().findActiveAdvertisementByServiceType(
                Advertisement.ServiceType.OFFER.toString());
        Assert.assertEquals(2, getAllAdvertisementsByDate.size());
    }
}