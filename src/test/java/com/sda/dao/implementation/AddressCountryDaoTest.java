package com.sda.dao.implementation;

import com.sda.entity.AddressCountry;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;


/**
 * @author StanislavR
 */

public class AddressCountryDaoTest {

    Logger log = Logger.getLogger(AddressCountryDaoTest.class.getName());

    String connectionToDatabaseCreate  = "oleksHibernateCreateTest.cfg.xml";
    String connectionToDatabaseValidate  = "oleksHibernateValidateTest.cfg.xml";

    AddressCountryDao addressCountryDao = new AddressCountryDao(connectionToDatabaseCreate);

    @Test
    public void shouldSaveAddressCountry() {

        log.info("...shouldSaveAddressCountry...");

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        addressCountryDao.save(addressCountryTest1);

        Assert.assertNotNull(addressCountryDao.get(1L));
    }

    @Test
    public void shouldGetAddressCountryById() {

        log.info("...shouldGetAddressCountryById...");

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        addressCountryDao.save(addressCountryTest1);

        AddressCountry shouldGetAddressCountryById = addressCountryDao.get(1L);

        Assert.assertEquals(shouldGetAddressCountryById, addressCountryTest1);
    }

    @Test
    public void shouldGetAllAddressesCountries() {

        log.info("...shouldGetAllAddressesCountries...");

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        addressCountryDao.save(addressCountryTest1);

        AddressCountry addressCityTest2 = new AddressCountry("Sweden");
        addressCountryDao.save(addressCityTest2);

        List<AddressCountry> getAllAddressesCountries =  addressCountryDao.getAll();

        Assert.assertEquals(2, getAllAddressesCountries.size());

    }

    @Test
    public void shouldUpdateAddressCountry() {

        log.info("...shouldUpdateAddressCountry...");

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        addressCountryDao.save(addressCountryTest1);

        AddressCountry addressCountryTest2 = new AddressCountry ("Latvia");

        addressCountryTest1.setCountry("Latvia");

        addressCountryDao.update(addressCountryTest1);

        AddressCountry updatedAddressCountry = addressCountryDao.get(1L);

        Assert.assertEquals(addressCountryTest2.getCountry(),updatedAddressCountry.getCountry());
    }

    @Test
    public void shouldDeleteAddressCountry() {

        log.info("...shouldDeleteAddressCountry...");

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        addressCountryDao.save(addressCountryTest1);

        AddressCountry addressCountryTest2 = new AddressCountry("Sweden");
        addressCountryDao.save(addressCountryTest2);

        addressCountryDao.delete(addressCountryTest2);

        AddressCountry shouldBeDeletedAddressCountry = addressCountryDao.get(2L);
        Assert.assertNull(shouldBeDeletedAddressCountry);
    }

    @Test
    public void shouldSaveAddressCountryWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveAddressCountryWithCreatedAndUpdatedTimeStamp...");

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        addressCountryDao.save(addressCountryTest1);

        AddressCountry shouldGetAddressCountryById = addressCountryDao.get(1L);

        Assert.assertNotNull(shouldGetAddressCountryById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAddressCountryById.getUPDATED_ON().toString());

    }
}
