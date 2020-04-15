package com.sda.dao.implementation;

import com.sda.entity.Country;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;


/**
 * Work only one-by-one
 */

public class CountryDaoTest {

    Logger log = Logger.getLogger(CountryDaoTest.class.getName());

    String connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";

    CountryDao countryDao = new CountryDao(connectionToDatabaseCreate);

    @Test
    public void shouldSaveAddressCountry() {

        log.info("...shouldSaveAddressCountry...");

        Country countryTest1 = new Country("Estonia");
        countryDao.save(countryTest1);

        Assert.assertNotNull(countryDao.get(1L));
    }

    @Test
    public void shouldGetAddressCountryById() {

        log.info("...shouldGetAddressCountryById...");

        Country countryTest1 = new Country("Estonia");
        countryDao.save(countryTest1);

        Country shouldGetCountryById = countryDao.get(1L);

        Assert.assertEquals(shouldGetCountryById, countryTest1);
    }

    @Test
    public void shouldGetAllAddressesCountries() {

        log.info("...shouldGetAllAddressesCountries...");

        Country countryTest1 = new Country("Estonia");
        countryDao.save(countryTest1);

        Country addressCityTest2 = new Country("Sweden");
        countryDao.save(addressCityTest2);

        List<Country> getAllAddressesCountries = countryDao.getAll();

        Assert.assertEquals(2, getAllAddressesCountries.size());

    }

    @Test
    public void shouldUpdateAddressCountry() {

        log.info("...shouldUpdateAddressCountry...");

        Country countryTest1 = new Country("Estonia");
        countryDao.save(countryTest1);

        Country countryTest2 = new Country("Latvia");

        countryTest1.setCountryName("Latvia");

        countryDao.update(countryTest1);

        Country updatedCountry = countryDao.get(1L);

        Assert.assertEquals(countryTest2.getCountryName(), updatedCountry.getCountryName());
    }

    @Test
    public void shouldDeleteAddressCountry() {

        log.info("...shouldDeleteAddressCountry...");

        Country countryTest1 = new Country("Estonia");
        countryDao.save(countryTest1);

        Country countryTest2 = new Country("Sweden");
        countryDao.save(countryTest2);

        countryDao.delete(countryTest2);

        Country shouldBeDeletedCountry = countryDao.get(2L);
        Assert.assertNull(shouldBeDeletedCountry);
    }

    @Test
    public void shouldSaveAddressCountryWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveAddressCountryWithCreatedAndUpdatedTimeStamp...");

        Country countryTest1 = new Country("Estonia");
        countryDao.save(countryTest1);

        Country shouldGetCountryById = countryDao.get(1L);

        Assert.assertNotNull(shouldGetCountryById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetCountryById.getUPDATED_ON().toString());

    }
}
