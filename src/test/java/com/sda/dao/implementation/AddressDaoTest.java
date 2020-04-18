package com.sda.dao.implementation;

import com.sda.entity.Address;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;


/**
 * Work only one-by-one
 */

public class AddressDaoTest {

    static String connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";

    AddressDao addressDao;

    String countryTest1 = "USA";
    String cityTest1 = "NY";
    String countryTest2 = "UK";
    String cityTest2 = "London";

    Address addressTest1;
    Address addressTest2;

    Logger log = Logger.getLogger(AddressDaoTest.class.getName());

    @Before
    public void setUp() {

        addressDao = new AddressDao(connectionToDatabaseCreate);
        addressTest1 = new Address(countryTest1, cityTest1);
        addressTest2 = new Address(countryTest2, cityTest2);
        addressDao.save(addressTest1);
        addressDao.save(addressTest2);
    }

    @Test
    public void shouldSaveAddress() {

        log.info("...shouldSaveAddress...");

        Assert.assertNotNull(addressDao.get(1L));
    }

    @Test
    public void shouldGetAddressById() {

        log.info("...shouldGetAddressById...");

        Address shouldGetAddressById = addressDao.get(1L);

        Assert.assertEquals(shouldGetAddressById, addressTest1);

    }

    @Test
    public void shouldGetAllAddresses() {

        log.info("...shouldGetAllAddresses...");

        List<Address> getAllAddresses = addressDao.getAll();

        Assert.assertEquals(2, getAllAddresses.size());
    }

    @Test
    public void shouldUpdateAddressCity() {

        log.info("...shouldUpdateAddressCity...");

        addressTest1.setCity(cityTest2);

        addressDao.update(addressTest1);

        Address updatedAddressCity = addressDao.get(1L);

        Assert.assertEquals(cityTest2, updatedAddressCity.getCity());
    }

    @Test
    public void shouldDeleteAddress() {

        log.info("...shouldDeleteAddress...");

        Address shouldBeSavedAddress = addressDao.get(2L);
        Assert.assertNotNull(shouldBeSavedAddress);

        addressDao.delete(shouldBeSavedAddress);

        Address shouldBeDeletedAddress = addressDao.get(2L);
        Assert.assertNull(shouldBeDeletedAddress);

    }

    @Test
    public void shouldSaveAddressWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveAddressWithCreatedAndUpdatedTimeStamp...");

        Address shouldGetAddressById = addressDao.get(1L);

        Assert.assertNotNull(shouldGetAddressById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAddressById.getUPDATED_ON().toString());
    }

    @Test
    public void shouldGetEqualObjects() {

        log.info("...shouldGetEqualObjects...");

        Address addressGet1 = addressDao.get(1L);

        Assert.assertEquals(addressGet1, new Address("USA", "NY"));

    }
}
