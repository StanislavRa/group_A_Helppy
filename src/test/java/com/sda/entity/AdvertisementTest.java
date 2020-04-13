package com.sda.entity;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author StanislavR
 */
public class AdvertisementTest {
    @Test
    public void shouldSetServiceTypeWithString() {
        Advertisement ad = new Advertisement();
        ad.setServiceType(Advertisement.ServiceType.valueOf("OFFER"));

        Assert.assertEquals(Advertisement.ServiceType.OFFER, ad.getServiceType());
    }
}
