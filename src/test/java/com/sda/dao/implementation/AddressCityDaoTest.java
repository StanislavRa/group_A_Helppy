package com.sda.dao.implementation;

import com.sda.entity.AddressCity;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;


/**
 * @author StanislavR
 */

public class AddressCityDaoTest {

    Logger log = Logger.getLogger(AddressCityDaoTest.class.getName());

    String connectionToDatabaseCreate  = "oleksHibernateCreateTest.cfg.xml";
    String connectionToDatabaseValidate  = "oleksHibernateValidateTest.cfg.xml";

    AddressCityDao addressCityDao = new AddressCityDao(connectionToDatabaseCreate);

    @Test
    public void shouldSaveAddressCity() {

        log.info("...shouldSaveAddressCity...");


        AddressCity addressCityTest1 = new AddressCity("Tallinn");
        addressCityDao.save(addressCityTest1);

        Assert.assertNotNull(addressCityDao.get(1L));
    }

    @Test
    public void shouldGetAddressCityById() {

        log.info("...shouldGetAddressCityById...");

        AddressCity addressCityTest1 = new AddressCity("Tallinn");
        addressCityDao.save(addressCityTest1);

        AddressCity shouldGetAddressCityById = addressCityDao.get(1L);

        Assert.assertEquals(shouldGetAddressCityById, addressCityTest1);
    }

    @Test
    public void shouldGetAllAddresses() {

        log.info("...shouldGetAllAddresses...");


        AddressCity addressCityTest1 = new AddressCity("Tallinn");
        addressCityDao.save(addressCityTest1);

        AddressCity addressCityTest2 = new AddressCity("Tartu");
        addressCityDao.save(addressCityTest2);

        List<AddressCity> getAllAddressesCities =  addressCityDao.getAll();

        Assert.assertEquals(2, getAllAddressesCities.size());

    }

    @Test
    public void shouldUpdateAddressCity() {

        log.info("...shouldUpdateAddressCity...");

        AddressCity addressCityTest1 = new AddressCity("Tallinn");
        addressCityDao.save(addressCityTest1);

        AddressCity addressCityTest2 = new AddressCity("Tartu");

        AddressCity shouldGetAddressCityById = addressCityDao.get(1L);

        shouldGetAddressCityById.setCity("Tartu");

        addressCityDao.update(shouldGetAddressCityById);

        AddressCity updatedAddressCity = addressCityDao.get(1L);

        Assert.assertEquals(addressCityTest2.getCity(),updatedAddressCity.getCity());
    }

    @Test
    public void shouldDeleteAddress() {

        log.info("...shouldDeleteAddress...");


        AddressCity addressCityTest1 = new AddressCity("Tallinn");
           addressCityDao.save(addressCityTest1);

        AddressCity addressCityTest2 = new AddressCity("Tallinn");
        addressCityDao.save(addressCityTest2);

        AddressCity shouldBeSavedAddress = addressCityDao.get(2L);
        Assert.assertNotNull(shouldBeSavedAddress);

        addressCityDao.delete(shouldBeSavedAddress);

        AddressCity shouldBeDeletedAddress = addressCityDao.get(2L);
        Assert.assertNull(shouldBeDeletedAddress);
    }

    @Test
    public void shouldSaveAddressCityWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveAddressCityWithCreatedAndUpdatedTimeStamp...");

        AddressCity addressCityTest1 = new AddressCity("Tallinn");
        addressCityDao.save(addressCityTest1);

        AddressCity shouldGetAddressById = addressCityDao.get(1L);

        Assert.assertNotNull(shouldGetAddressById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAddressById.getUPDATED_ON().toString());

    }
}
