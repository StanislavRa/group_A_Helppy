package com.sda.dao.implementation;

import com.sda.entity.Address;
import com.sda.entity.City;
import com.sda.entity.Country;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;


/**
 * @author StanislavR
 */

public class AddressDaoTest {

    Logger log = Logger.getLogger(AddressDaoTest.class.getName());

    String connectionToDatabaseCreate  = "oleksHibernateCreateTest.cfg.xml";
    String connectionToDatabaseValidate  = "oleksHibernateValidateTest.cfg.xml";

    AddressDao addressDao = new AddressDao(connectionToDatabaseCreate);
    CountryDao countryDao = new CountryDao(connectionToDatabaseCreate);
    CityDao cityDao = new CityDao(connectionToDatabaseCreate);

    @Test
    public void shouldSaveAddress() {

        log.info("...shouldSaveAddress...");

        Country countryTest1 = new Country("Estonia");
        countryDao.save(countryTest1);

        City cityTest1 = new City("Tallinn");
        cityDao.save(cityTest1);

        Address addressTest1 = new Address(countryTest1, cityTest1);
        addressDao.save(addressTest1);

        Assert.assertNotNull(addressDao.get(1L));
    }

    @Test
    public void shouldGetAddressById() {

        log.info("...shouldGetAddressById...");

        Country countryTest1 = new Country("Estonia");
        City cityTest1 = new City("Tallinn");
        Address addressTest1 = new Address(countryTest1, cityTest1);
        addressDao.save(addressTest1);

        Address shouldGetAddressById = addressDao.get(1L);

        Assert.assertEquals(shouldGetAddressById, addressTest1);
    }

    @Test
    public void shouldGetAllAddresses() {

        log.info("...shouldGetAllAddresses...");

        Country countryTest1 = new Country("Estonia");
        City cityTest1 = new City("Tallinn");
        Address addressTest1 = new Address(countryTest1, cityTest1);
        addressDao.save(addressTest1);

        City cityTest2 = new City("Tartu");
        Address addressTest2 = new Address(countryTest1, cityTest2);
        addressDao.save(addressTest2);

        List<Address> getAllAddresses =  addressDao.getAll();

        Assert.assertEquals(2, getAllAddresses.size());

    }

    @Test
    public void shouldUpdateAddressCity() {

        log.info("...shouldUpdateAddressCity...");

        Country countryTest1 = new Country("Estonia");
        City cityTest1 = new City("Tallinn");
        Address addressTest1 = new Address(countryTest1, cityTest1);
        addressDao.save(addressTest1);

        Address address = addressDao.get(1L);

        City cityTest2 = new City("Tallinn");

        address.setCityName(cityTest2);

        addressDao.update(address);

        Address updatedAddressCity = addressDao.get(1L);

        Assert.assertEquals(cityTest2,updatedAddressCity.getCityName());
    }

    @Test
    public void shouldDeleteAddress() {

        log.info("...shouldDeleteAddress...");

        Country countryTest1 = new Country("Estonia");
        City cityTest1 = new City("Tallinn");
        Address addressTest1 = new Address(countryTest1, cityTest1);
        addressDao.save(addressTest1);

        City cityTest2 = new City("Tallinn");
        Address addressTest2 = new Address(countryTest1, cityTest2);
        addressDao.save(addressTest2);

        Address shouldBeSavedAddress = addressDao.get(2L);
        Assert.assertNotNull(shouldBeSavedAddress);

        addressDao.delete(shouldBeSavedAddress);

        Address shouldBeDeletedAddress = addressDao.get(2L);
        Assert.assertNull(shouldBeDeletedAddress);
    }

    @Test
    public void shouldSaveAddressWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveAddressWithCreatedAndUpdatedTimeStamp...");

        Country countryTest1 = new Country("Estonia");
        City cityTest1 = new City("Tallinn");
        Address addressTest1 = new Address(countryTest1, cityTest1);
        addressDao.save(addressTest1);

        Address shouldGetAddressById = addressDao.get(1L);

        Assert.assertNotNull(shouldGetAddressById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAddressById.getUPDATED_ON().toString());

    }

    @Test
    public void shouldGetEqualObjects() {

        log.info("...shouldGetEqualObjects...");

        Country countryTest1 = new Country("Estonia");
        City cityTest1 = new City("Tallinn");
        Address addressTest1 = new Address(countryTest1, cityTest1);
        addressDao.save(addressTest1);

        City cityTest2 = new City("Tallinn");
        Address addressTest2 = new Address(countryTest1, cityTest2);
        addressDao.save(addressTest2);

        Address addressGet1 = addressDao.get(1L);
        Address addressGet2 = addressDao.get(2L);

        Assert.assertEquals(addressGet1, addressGet2);
    }
}
