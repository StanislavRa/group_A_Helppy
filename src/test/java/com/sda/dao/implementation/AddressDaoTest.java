package com.sda.dao.implementation;

import com.sda.entity.Address;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;


/**
 * @author StanislavR
 */
public class AddressDaoTest {

    Logger log = Logger.getLogger(AddressDaoTest.class.getName());

    @Test
    public void shouldSaveAddressWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveAddressWithCreatedAndUpdatedTimeStamp...");

        Address address = new Address();
        address.setCountry("USA");
        address.setCity("NY");
        address.setStreet("12 Avenue");

        AddressDao addressDao = new AddressDao();

        addressDao.save(address);

        Assert.assertNotNull(address.getCREATED_ON());
        Assert.assertNotNull(address.getUPDATED_ON());

    }
}
