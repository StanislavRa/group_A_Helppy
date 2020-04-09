package com.sda.dao.implementation;

import com.sda.entity.City;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;


/**
 * @author StanislavR
 */

public class CityDaoTest {

    Logger log = Logger.getLogger(CityDaoTest.class.getName());

    String connectionToDatabaseCreate  = "oleksHibernateCreateTest.cfg.xml";
    String connectionToDatabaseValidate  = "oleksHibernateValidateTest.cfg.xml";

    CityDao cityDao = new CityDao(connectionToDatabaseCreate);

    @Test
    public void shouldSaveAddressCity() {

        log.info("...shouldSaveAddressCity...");


        City cityTest1 = new City("Tallinn");
        cityDao.save(cityTest1);

        Assert.assertNotNull(cityDao.get(1L));
    }

    @Test
    public void shouldGetAddressCityById() {

        log.info("...shouldGetAddressCityById...");

        City cityTest1 = new City("Tallinn");
        cityDao.save(cityTest1);

        City shouldGetCityById = cityDao.get(1L);

        Assert.assertEquals(shouldGetCityById, cityTest1);
    }

    @Test
    public void shouldGetAllAddresses() {

        log.info("...shouldGetAllAddresses...");


        City cityTest1 = new City("Tallinn");
        cityDao.save(cityTest1);

        City cityTest2 = new City("Tartu");
        cityDao.save(cityTest2);

        List<City> getAllAddressesCities =  cityDao.getAll();

        Assert.assertEquals(2, getAllAddressesCities.size());

    }

    @Test
    public void shouldUpdateAddressCity() {

        log.info("...shouldUpdateAddressCity...");

        City cityTest1 = new City("Tallinn");
        cityDao.save(cityTest1);

        City cityTest2 = new City("Tartu");

        City shouldGetCityById = cityDao.get(1L);

        shouldGetCityById.setCityName("Tartu");

        cityDao.update(shouldGetCityById);

        City updatedCity = cityDao.get(1L);

        Assert.assertEquals(cityTest2.getCityName(), updatedCity.getCityName());
    }

    @Test
    public void shouldDeleteAddress() {

        log.info("...shouldDeleteAddress...");

        City cityTest1 = new City("Tallinn");
           cityDao.save(cityTest1);

        City cityTest2 = new City("Tallinn");
        cityDao.save(cityTest2);

        City shouldBeSavedAddress = cityDao.get(2L);
        Assert.assertNotNull(shouldBeSavedAddress);

        cityDao.delete(shouldBeSavedAddress);

        City shouldBeDeletedAddress = cityDao.get(2L);
        Assert.assertNull(shouldBeDeletedAddress);
    }

    @Test
    public void shouldSaveAddressCityWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveAddressCityWithCreatedAndUpdatedTimeStamp...");

        City cityTest1 = new City("Tallinn");
        cityDao.save(cityTest1);

        City shouldGetAddressById = cityDao.get(1L);

        Assert.assertNotNull(shouldGetAddressById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAddressById.getUPDATED_ON().toString());

    }
}
