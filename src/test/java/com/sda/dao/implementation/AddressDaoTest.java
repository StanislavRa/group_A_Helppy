package com.sda.dao.implementation;

import com.sda.entity.Address;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;


/**
 * @author StanislavR
 */

public class AddressDaoTest {

    Logger log = Logger.getLogger(AddressDaoTest.class.getName());
    AddressDao addressDao = new AddressDao("hibernateTest.cfg.xml");

    @Test
    public void shouldSaveAddress() {

        log.info("...shouldSaveAddress...");

        Address addressTest1 = new Address("Estonia", "Tallinn", "Parnu mnt");
        addressDao.save(addressTest1);

        Assert.assertNotNull(addressDao.get(1L));
    }

    @Test
    public void shouldGetAddressById() {

        log.info("...shouldGetAddressById...");

        Address addressTest1 = new Address("Estonia", "Tallinn", "Parnu mnt");
        addressDao.save(addressTest1);

        Address shouldGetAddressById = addressDao.get(1L);

        Assert.assertEquals(shouldGetAddressById, addressTest1);
    }

    @Test
    public void shouldGetAllAddresses() {

        log.info("...shouldGetAllAddresses...");

        Address addressTest1 = new Address("Estonia", "Tallinn", "Parnu mnt");
        addressDao.save(addressTest1);

        Address addressTest2 = new Address("Estonia", "Tallinn", "Lardi");
        addressDao.save(addressTest2);

        List<Address> getAllAddresses =  addressDao.getAll();

        Assert.assertEquals(2, getAllAddresses.size());

    }

    @Test
    public void shouldUpdateAddressCity() {

        log.info("...shouldUpdateAddressCity...");

        Address addressTest1 = new Address("Estonia", "Tallinn", "Parnu mnt");
        addressDao.save(addressTest1);

        Address address = addressDao.get(1L);

        String newCityName = "Tartu";

        address.setCity(newCityName);

        addressDao.update(address);

        Address updatedAddressCity = addressDao.get(1L);

        Assert.assertEquals(newCityName,updatedAddressCity.getCity());
    }

    @Test
    public void shouldDeleteAddress() {

        log.info("...shouldDeleteAddress...");


        Address addressTest1 = new Address("Estonia", "Tallinn", "Parnu mnt");
        addressDao.save(addressTest1);

        Address addressTest2 = new Address("Estonia", "Tallinn", "Lardi");
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

        Address addressTest1 = new Address("Estonia", "Tallinn", "Parnu mnt");
        addressDao.save(addressTest1);


        addressDao.save(addressTest1);

        Assert.assertNotNull(addressTest1.getCREATED_ON());
        Assert.assertNotNull(addressTest1.getUPDATED_ON());

    }

    @Test
    public void shouldGetEqualObjects() {

        log.info("...shouldGetEqualObjects...");


        Address addressTest1 = new Address("Estonia", "Tallinn", "Parnu mnt");
        addressDao.save(addressTest1);

        Address addressTest2 = new Address("Estonia", "Tallinn", "Parnu mnt");
        addressDao.save(addressTest1);

        Address addressGet1 = addressDao.get(1L);
        Address addressGet2 = addressDao.get(2L);

        Assert.assertEquals(addressGet1, addressGet2);
    }
}
