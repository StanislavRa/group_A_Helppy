package com.sda.dao.implementation;

import com.sda.entity.Address;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.logging.Logger;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddressDaoTest {


    static AddressDao addressDao;

    static String countryTest1;
    static String cityTest1;
    static String countryTest2;
    static String cityTest2;

    static Address addressTest1;
    static Address addressTest2;
    static Address addressForUpdate;

    Logger log = Logger.getLogger(AddressDaoTest.class.getName());

    @BeforeClass
    public static void beforeClass() {
        
        countryTest1 = "USA";
        cityTest1 = "NY";

        countryTest2 = "UK";
        cityTest2 = "London";

        addressDao = new AddressDao();
        addressTest1 = new Address(countryTest1, cityTest1);
        addressTest2 = new Address(countryTest2, cityTest2);
        addressForUpdate = new Address(countryTest2, cityTest2);

        addressDao.save(addressTest1);
        addressDao.save(addressTest2);
        addressDao.save(addressForUpdate);
    }

    @Test
    public void should1SaveAddress() {

        log.info("...shouldSaveAddress...");

        Assert.assertNotNull(addressDao.get(1L));
    }

    @Test
    public void should2GetAddressById() {

        log.info("...shouldGetAddressById...");

        Address shouldGetAddressById = addressDao.get(1L);

        Assert.assertEquals(shouldGetAddressById, addressTest1);

    }

    @Test
    public void should3SaveAddressWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveAddressWithCreatedAndUpdatedTimeStamp...");

        Address shouldGetAddressById = addressDao.get(1L);

        Assert.assertNotNull(shouldGetAddressById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAddressById.getUPDATED_ON().toString());
    }

    @Test
    public void should4GetAllAddresses() {

        log.info("...shouldGetAllAddresses...");

        List<Address> getAllAddresses = addressDao.getAll();

        Assert.assertEquals(3, getAllAddresses.size());
    }

    @Test
    public void should5GetEqualObjects() {

        log.info("...shouldGetEqualObjects...");

        Address addressGet1 = addressDao.get(1L);

        Assert.assertEquals(addressGet1, new Address("USA", "NY"));
    }

    @Test
    public void should6UpdateAddressCity() {

        log.info("...shouldUpdateAddressCity...");

        Address updatableCity = addressDao.get(3L);

        updatableCity.setCity("Narva");

        addressDao.update(updatableCity);

        Address updatedAddressCity = addressDao.get(3L);

        Assert.assertEquals("Narva", updatedAddressCity.getCity());
    }


    @Test
    public void should7DeleteAddress() {

        log.info("...shouldDeleteAddress...");

        Address shouldBeSavedAddress = addressDao.get(2L);
        Assert.assertNotNull(shouldBeSavedAddress);

        addressDao.delete(shouldBeSavedAddress);

        Address shouldBeDeletedAddress = addressDao.get(2L);
        Assert.assertNull(shouldBeDeletedAddress);

    }
}
