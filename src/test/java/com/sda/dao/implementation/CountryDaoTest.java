package com.sda.dao.implementation;

import com.sda.entity.Country;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;


/**
 * Work only one-by-one
 */

public class CountryDaoTest {

    String connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";

    Logger log = Logger.getLogger(AdvertisementDaoTest.class.getName());

    CountryDao countryDao;

    Country countryTest1;
    Country countryTest2;

    @Before
    public void setUp() {

        countryDao = new CountryDao(connectionToDatabaseCreate);

        countryTest1 = new Country("Estonia");
        countryTest2 = new Country("France");

        countryDao.save(countryTest1);
        countryDao.save(countryTest2);
    }

    @Test
    public void shouldSaveCountry() {

        log.info("...shouldSaveCountry...");

        Assert.assertNotNull(countryDao.get(1L));
    }

    @Test
    public void shouldGetCountryById() {

        log.info("...shouldGetCountryById...");

        Country shouldGetCountryById = countryDao.get(1L);

        Assert.assertEquals(shouldGetCountryById, countryTest1);
    }

    @Test
    public void shouldGetAllCountries() {

        log.info("...shouldGetAllCountries...");

        List<Country> getAllAddressesCountries = countryDao.getAll();

        Assert.assertEquals(2, getAllAddressesCountries.size());
    }

    @Test
    public void shouldUpdateCountry() {

        log.info("...shouldUpdateCountry...");

        countryTest1.setCountryName("Latvia");

        countryDao.update(countryTest1);

        Country updatedCountry = countryDao.get(1L);

        Assert.assertEquals(countryTest2.getCountryName(), updatedCountry.getCountryName());
    }

    @Test
    public void shouldDeleteCountry() {

        log.info("...shouldDeleteCountry...");

        Country shouldBeSavedCountry = countryDao.get(2L);
        Assert.assertNotNull(shouldBeSavedCountry);

        countryDao.delete(shouldBeSavedCountry);

        Country shouldBeDeletedCountry = countryDao.get(2L);
        Assert.assertNull(shouldBeDeletedCountry);
    }

    @Test
    public void shouldSaveCountryWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveCountryWithCreatedAndUpdatedTimeStamp...");

        Country shouldGetCountryById = countryDao.get(1L);

        Assert.assertNotNull(shouldGetCountryById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetCountryById.getUPDATED_ON().toString());
    }
}
