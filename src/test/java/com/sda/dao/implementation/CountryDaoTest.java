package com.sda.dao.implementation;

import com.sda.entity.Country;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.logging.Logger;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CountryDaoTest {

    static String connectionToDatabaseCreate;

    Logger log = Logger.getLogger(AdvertisementDaoTest.class.getName());

    static CountryDao countryDao;

    static Country countryTest1;
    static Country countryTest2;
    static Country countryTestToBeUpdated;
    static Country countryTestToBeDeleted;

    @BeforeClass
    public static void setUp() {

        connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";

        countryDao = new CountryDao(connectionToDatabaseCreate);

        countryTest1 = new Country("Estonia");
        countryTest2 = new Country("France");
        countryTestToBeUpdated = new Country("USA");
        countryTestToBeDeleted = new Country("UK");

        countryDao.save(countryTest1);
        countryDao.save(countryTest2);
        countryDao.save(countryTestToBeUpdated);
        countryDao.save(countryTestToBeDeleted);
    }

    @Test
    public void should1SaveCountry() {

        log.info("...shouldSaveCountry...");

        Assert.assertNotNull(countryDao.get(1L));
    }

    @Test
    public void should2SaveCountryWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveCountryWithCreatedAndUpdatedTimeStamp...");

        Country shouldGetCountryById = countryDao.get(1L);

        Assert.assertNotNull(shouldGetCountryById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetCountryById.getUPDATED_ON().toString());
    }

    @Test
    public void should3GetCountryById() {

        log.info("...shouldGetCountryById...");

        Country shouldGetCountryById = countryDao.get(1L);

        Assert.assertEquals(shouldGetCountryById, countryTest1);
    }

    @Test
    public void should4GetAllCountries() {

        log.info("...shouldGetAllCountries...");

        List<Country> getAllAddressesCountries = countryDao.getAll();

        Assert.assertEquals(4, getAllAddressesCountries.size());
    }

    @Test
    public void should5UpdateCountry() {

        log.info("...shouldUpdateCountry...");

        countryTest1.setCountryName("Latvia");

        countryDao.update(countryTest1);

        Country updatedCountry = countryDao.get(3L);

        Assert.assertEquals(countryTestToBeUpdated.getCountryName(), updatedCountry.getCountryName());
    }

    @Test
    public void should6DeleteCountry() {

        log.info("...shouldDeleteCountry...");

        Country shouldBeSavedCountry = countryDao.get(4L);
        Assert.assertNotNull(shouldBeSavedCountry);

        countryDao.delete(shouldBeSavedCountry);

        Country shouldBeDeletedCountry = countryDao.get(4L);
        Assert.assertNull(shouldBeDeletedCountry);
    }
}
