package com.sda.dao.implementation;

import com.sda.entity.Address;
import com.sda.entity.AddressCity;
import com.sda.entity.AddressCountry;
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
    AddressCountryDao addressCountryDao = new AddressCountryDao(connectionToDatabaseCreate);
    AddressCityDao addressCityDao = new AddressCityDao(connectionToDatabaseCreate);

    @Test
    public void shouldSaveAddress() {

        log.info("...shouldSaveAddress...");

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        addressCountryDao.save(addressCountryTest1);

        AddressCity addressCityTest1 = new AddressCity("Tallinn");
        addressCityDao.save(addressCityTest1);

        Address addressTest1 = new Address(addressCountryTest1, addressCityTest1);
        addressDao.save(addressTest1);

        Assert.assertNotNull(addressDao.get(1L));
    }

    @Test
    public void shouldGetAddressById() {

        log.info("...shouldGetAddressById...");

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        AddressCity addressCityTest1 = new AddressCity("Tallinn");
        Address addressTest1 = new Address(addressCountryTest1, addressCityTest1);
        addressDao.save(addressTest1);

        Address shouldGetAddressById = addressDao.get(1L);

        Assert.assertEquals(shouldGetAddressById, addressTest1);
    }

    @Test
    public void shouldGetAllAddresses() {

        log.info("...shouldGetAllAddresses...");

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        AddressCity addressCityTest1 = new AddressCity("Tallinn");
        Address addressTest1 = new Address(addressCountryTest1, addressCityTest1);
        addressDao.save(addressTest1);

        AddressCity addressCityTest2 = new AddressCity("Tartu");
        Address addressTest2 = new Address(addressCountryTest1, addressCityTest2);
        addressDao.save(addressTest2);

        List<Address> getAllAddresses =  addressDao.getAll();

        Assert.assertEquals(2, getAllAddresses.size());

    }

    @Test
    public void shouldUpdateAddressCity() {

        log.info("...shouldUpdateAddressCity...");

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        AddressCity addressCityTest1 = new AddressCity("Tallinn");
        Address addressTest1 = new Address(addressCountryTest1, addressCityTest1);
        addressDao.save(addressTest1);

        Address address = addressDao.get(1L);

        AddressCity addressCityTest2 = new AddressCity("Tallinn");

        address.setCity(addressCityTest2);

        addressDao.update(address);

        Address updatedAddressCity = addressDao.get(1L);

        Assert.assertEquals(addressCityTest2,updatedAddressCity.getCity());
    }

    @Test
    public void shouldDeleteAddress() {

        log.info("...shouldDeleteAddress...");

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        AddressCity addressCityTest1 = new AddressCity("Tallinn");
        Address addressTest1 = new Address(addressCountryTest1, addressCityTest1);
        addressDao.save(addressTest1);

        AddressCity addressCityTest2 = new AddressCity("Tallinn");
        Address addressTest2 = new Address(addressCountryTest1, addressCityTest2);
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

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        AddressCity addressCityTest1 = new AddressCity("Tallinn");
        Address addressTest1 = new Address(addressCountryTest1, addressCityTest1);
        addressDao.save(addressTest1);

        Address shouldGetAddressById = addressDao.get(1L);

        Assert.assertNotNull(shouldGetAddressById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAddressById.getUPDATED_ON().toString());

    }

    @Test
    public void shouldGetEqualObjects() {

        log.info("...shouldGetEqualObjects...");

        AddressCountry addressCountryTest1 = new AddressCountry("Estonia");
        AddressCity addressCityTest1 = new AddressCity("Tallinn");
        Address addressTest1 = new Address(addressCountryTest1, addressCityTest1);
        addressDao.save(addressTest1);

        AddressCity addressCityTest2 = new AddressCity("Tallinn");
        Address addressTest2 = new Address(addressCountryTest1, addressCityTest2);
        addressDao.save(addressTest2);

        Address addressGet1 = addressDao.get(1L);
        Address addressGet2 = addressDao.get(2L);

        Assert.assertEquals(addressGet1, addressGet2);
    }
}
