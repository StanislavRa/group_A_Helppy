package com.sda.dao.implementation;

import com.sda.entity.City;
import com.sda.entity.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;


/**
 * Work only one-by-one
 */

public class CityDaoTest {

    String connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";

    Logger log = Logger.getLogger(AdvertisementDaoTest.class.getName());

    CityDao cityDao;
    CountryDao countryDao;

    City cityTest1;
    City cityTest2;
    City cityTest3;
    Country countryTest1;
    Country countryTest2;

    @Before
    public void setUp() {

        cityDao = new CityDao(connectionToDatabaseCreate);
        countryDao = new CountryDao(connectionToDatabaseCreate);

        countryTest1 = new Country("Estonia");
        countryTest2 = new Country("France");

        cityTest1 = new City("Tallinn");
        cityTest2 = new City("Narva");

        cityTest1.setCountry(countryTest1);
        cityTest2.setCountry(countryTest1);

        cityTest3 = new City("Paris");

        cityTest3.setCountry(countryTest2);

        countryDao.save(countryTest1);
        countryDao.save(countryTest2);

        cityDao.save(cityTest1);
        cityDao.save(cityTest2);
        cityDao.save(cityTest3);
    }

    @Test
    public void shouldSaveCity() {

        log.info("...shouldSaveCity...");

        Assert.assertNotNull(cityDao.get(1L));
    }

    @Test
    public void shouldGetCityById() {

        log.info("...shouldGetCityById...");

        City shouldGetCityById = cityDao.get(1L);

        Assert.assertEquals(shouldGetCityById, cityTest1);
    }

    @Test
    public void shouldGetAllCities() {

        log.info("...shouldGetAllCities...");

        List<City> getAllAddressesCities = cityDao.getAll();

        Assert.assertEquals(3, getAllAddressesCities.size());
    }

    @Test
    public void shouldUpdateCity() {

        log.info("...shouldUpdateCity...");

        City shouldGetCityById = cityDao.get(1L);

        shouldGetCityById.setCityName("Tartu");

        cityDao.update(shouldGetCityById);

        City updatedCity = cityDao.get(1L);

        Assert.assertEquals("Tartu", updatedCity.getCityName());
    }

    @Test
    public void shouldDeleteCity() {

        log.info("...shouldDeleteCity...");

        City shouldBeSavedCity = cityDao.get(2L);
        Assert.assertNotNull(shouldBeSavedCity);

        cityDao.delete(shouldBeSavedCity);

        City shouldBeDeletedAddress = cityDao.get(2L);
        Assert.assertNull(shouldBeDeletedAddress);
    }

    @Test
    public void shouldSaveCityWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveCityWithCreatedAndUpdatedTimeStamp...");

        City shouldGetAddressById = cityDao.get(1L);

        Assert.assertNotNull(shouldGetAddressById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAddressById.getUPDATED_ON().toString());
    }

    @Test
    public void shouldGetAllCityNamesByCountry() {

        log.info("...shouldGetAllCityNamesByCountry...");

        List<String> getAllAddressesCities = cityDao.getAllCityNamesByCountry("Estonia");

        Assert.assertEquals(2, getAllAddressesCities.size());
    }
}
