package com.sda.dao.implementation;

import com.sda.entity.City;
import com.sda.entity.Country;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.logging.Logger;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CityDaoTest {


    Logger log = Logger.getLogger(AdvertisementDaoTest.class.getName());

    static CityDao cityDao;
    static CountryDao countryDao;

    static City cityTest1;
    static City cityTest2;
    static City cityTest3;
    static City cityTestToBeUpdated;
    static City cityTestToBeDeleted;
    static Country countryTest1;
    static Country countryTest2;
    static Country countryTest3;

    @BeforeClass
    public static void setUp() {


        cityDao = new CityDao();
        countryDao = new CountryDao();

        countryTest1 = new Country("Estonia");
        countryTest2 = new Country("France");
        countryTest3 = new Country("USA");

        cityTest1 = new City("Tallinn");
        cityTest2 = new City("Narva");

        cityTest1.setCountry(countryTest1);
        cityTest2.setCountry(countryTest1);

        cityTest3 = new City("Paris");

        cityTest3.setCountry(countryTest2);

        cityTestToBeUpdated = new City("LA");
        cityTestToBeDeleted = new City("NY");

        cityTestToBeUpdated.setCountry(countryTest3);
        cityTestToBeDeleted.setCountry(countryTest3);

        countryDao.save(countryTest1);
        countryDao.save(countryTest2);
        countryDao.save(countryTest3);

        cityDao.save(cityTest1);
        cityDao.save(cityTest2);
        cityDao.save(cityTest3);
        cityDao.save(cityTestToBeUpdated);
        cityDao.save(cityTestToBeDeleted);
    }

    @Test
    public void should1SaveCity() {

        log.info("...shouldSaveCity...");

        Assert.assertNotNull(cityDao.get(1L));
    }

    @Test
    public void should2SaveCityWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveCityWithCreatedAndUpdatedTimeStamp...");

        City shouldGetAddressById = cityDao.get(1L);

        Assert.assertNotNull(shouldGetAddressById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAddressById.getUPDATED_ON().toString());
    }

    @Test
    public void should3GetCityById() {

        log.info("...shouldGetCityById...");

        City shouldGetCityById = cityDao.get(1L);

        Assert.assertEquals(shouldGetCityById, cityTest1);
    }

    @Test
    public void should4GetAllCities() {

        log.info("...shouldGetAllCities...");

        List<City> getAllAddressesCities = cityDao.getAll();

        Assert.assertEquals(5, getAllAddressesCities.size());
    }
    @Test
    public void should5GetAllCityNamesByCountry() {

        log.info("...shouldGetAllCityNamesByCountry...");

        List<String> getAllAddressesCities = cityDao.getAllCityNamesByCountry("Estonia");

        Assert.assertEquals(2, getAllAddressesCities.size());
    }

    @Test
    public void should6UpdateCity() {

        log.info("...shouldUpdateCity...");

        City shouldGetCityById = cityDao.get(4L);

        shouldGetCityById.setCityName("Tartu");

        cityDao.update(shouldGetCityById);

        City updatedCity = cityDao.get(4L);

        Assert.assertEquals("Tartu", updatedCity.getCityName());
    }

    @Test
    public void should7DeleteCity() {

        log.info("...shouldDeleteCity...");

        City shouldBeSavedCity = cityDao.get(5L);
        Assert.assertNotNull(shouldBeSavedCity);

        cityDao.delete(shouldBeSavedCity);

        City shouldBeDeletedAddress = cityDao.get(5L);
        Assert.assertNull(shouldBeDeletedAddress);
    }
}
