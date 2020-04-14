package com.sda.dao.implementation;

import com.sda.entity.Address;
import com.sda.entity.City;
import com.sda.entity.Country;
import org.junit.*;

import java.util.List;
import java.util.logging.Logger;


/**
 * @author StanislavR
 */

public class AddressDaoTest {

    static String connectionToDatabaseCreate  = "hibernateUnitTest.cfg.xml";

     AddressDao addressDao = new AddressDao(connectionToDatabaseCreate);

     String countryTest1 = "USA";
     String cityTest1 = "NY";
     String countryTest2 = "UK";
     String cityTest2 = "London";

      Address addressTest1 = new Address(countryTest1, cityTest1);
      Address addressTest2= new Address(countryTest2, cityTest2);

    Logger log = Logger.getLogger(AddressDaoTest.class.getName());

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
       addressDao.deleteAll();
        System.out.println("After");
    }

    @Test
    public void shouldSaveAddress() {

        String countryTest1 = "USA";
        String cityTest1 = "NY";
        String countryTest2 = "UK";
        String cityTest2 = "London";

        Address addressTest1 = new Address(countryTest1, cityTest1);
        Address addressTest2= new Address(countryTest2, cityTest2);
        log.info("...shouldSaveAddress...");

        addressDao.save(addressTest1);

        Assert.assertNotNull(addressDao.get(1L));

    }

    @Test
    public void shouldGetAddressById() {
        String countryTest3 = "USA";
        String cityTest3 = "NY";
        String countryTest4 = "UK";
        String cityTest5 = "London";

        Address addressTest1 = new Address(countryTest3, cityTest3);
        Address addressTest2= new Address(countryTest4, cityTest5);

        log.info("...shouldGetAddressById...");

        addressDao.save(addressTest1);

        Address shouldGetAddressById = addressDao.get(1L);

        Assert.assertEquals(shouldGetAddressById, addressTest1);




    }

    @Test
    public void shouldGetAllAddresses() {


        log.info("...shouldGetAllAddresses...");

        addressDao.save(addressTest1);
        addressDao.save(addressTest2);

        List<Address> getAllAddresses =  addressDao.getAll();

        Assert.assertEquals(2, getAllAddresses.size());
    }

    @Test
    public void shouldUpdateAddressCity() {


        log.info("...shouldUpdateAddressCity...");

        addressDao.save(addressTest1);

        addressTest1.setCity(cityTest2);

        addressDao.update(addressTest1);

        Address updatedAddressCity = addressDao.get(1L);

        Assert.assertEquals(cityTest2,updatedAddressCity.getCity());

    }

    @Test
    public void shouldDeleteAddress() {


        log.info("...shouldDeleteAddress...");

        addressDao.save(addressTest1);
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

        addressDao.save(addressTest1);

        Address shouldGetAddressById = addressDao.get(1L);

        Assert.assertNotNull(shouldGetAddressById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetAddressById.getUPDATED_ON().toString());


    }

    @Test
    public void shouldGetEqualObjects() {


        log.info("...shouldGetEqualObjects...");

        addressDao.save(addressTest1);
        addressDao.save(addressTest1);

        Address addressGet1 = addressDao.get(1L);
        Address addressGet2 = addressDao.get(2L);

        Assert.assertEquals(addressGet1, addressGet2);

    }
}
