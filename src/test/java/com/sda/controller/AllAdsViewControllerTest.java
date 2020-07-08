package com.sda.controller;

import com.sda.dao.implementation.AdvertisementDao;
import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static com.sda.util.Constants.DATE_FORMAT;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AllAdsViewControllerTest {

    @Mock
    AdvertisementDao advertisementDao;
    Logger log = Logger.getLogger(AllAdsViewControllerTest.class.getName());

    @Before
    public void setUp() throws ParseException {
        MockitoAnnotations.initMocks(this);

        when(advertisementDao.getAllActiveAds()).thenReturn(
                Arrays.asList(
                        Advertisement.builder()
                                .subject("Clean Fast")
                                .description("blablabla")
                                .price(new BigDecimal("2.5"))
                                .startDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/1998"))
                                .endDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/2021"))
                                .serviceType(Advertisement.ServiceType.OFFER)
                                .category(new Category("Rent"))
                                .address(new Address("Estonia", "Tallinn"))
                                .build(),
                        Advertisement.builder()
                                .subject("Rent Car")
                                .description("some dummy description2")
                                .price(new BigDecimal("103"))
                                .startDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/2005"))
                                .endDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/2029"))
                                .serviceType(Advertisement.ServiceType.OFFER)
                                .category(new Category("Rent"))
                                .address(new Address("Latvia", "Riga"))
                                .build(),
                        Advertisement.builder()
                                .subject("Pet Hotel")
                                .description("some dummy description2")
                                .price(new BigDecimal("99"))
                                .startDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/2005"))
                                .endDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/2030"))
                                .serviceType(Advertisement.ServiceType.REQUEST)
                                .category(new Category("Pet Care"))
                                .address(new Address("Latvia", "Daugavpils"))
                                .build()
                ));
    }

    @Test
    public void shouldFindActiveAdvertisementByCategory() {
        log.info("...shouldFindActiveAdvertisementByCategory...");
        String searchedCategory = "Rent";
        List<Advertisement> getAllAdvertisementsByCategory = new AllAdsViewController()
                .findActiveAdvertisementByCategory(searchedCategory, advertisementDao);
        assertEquals(2, getAllAdvertisementsByCategory.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByCity() {
        log.info("...shouldFindActiveAdvertisementByCity...");
        String searchedCity = "Tallinn";
        List<Advertisement> getAllAdvertisementsByCity = new AllAdsViewController()
                .findActiveAdvertisementByCity(searchedCity, advertisementDao);
        assertEquals(1, getAllAdvertisementsByCity.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByCountry() {
        log.info("...shouldFindActiveAdvertisementByCountry...");
        String searchedCountry = "Latvia";
        List<Advertisement> getAllAdvertisementsByCountry = new AllAdsViewController()
                .findActiveAdvertisementByCountry(searchedCountry, advertisementDao);
        assertEquals(2, getAllAdvertisementsByCountry.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByPrice() {
        log.info("...shouldFindActiveAdvertisementByPrice...");
        String lowestPrice = "28";
        String highestPrice = "200";
        List<Advertisement> getAllAdvertisementsByPrice = new AllAdsViewController()
                .findActiveAdvertisementByPrice(new BigDecimal(lowestPrice),
                        new BigDecimal(highestPrice), advertisementDao);
        assertEquals(2, getAllAdvertisementsByPrice.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByDate() throws ParseException {
        log.info("...shouldFindActiveAdvertisementByDate...");
        String startDateString2 = "31/12/2004";
        Date startDateTest = new SimpleDateFormat(DATE_FORMAT).parse(startDateString2);
        String endDateString2 = "31/12/2600";
        Date endDaterTest = new SimpleDateFormat(DATE_FORMAT).parse(endDateString2);
        List<Advertisement> getAllAdvertisementsByDate = new AllAdsViewController()
                .findActiveAdvertisementByDate(startDateTest, endDaterTest, advertisementDao);
        assertEquals(2, getAllAdvertisementsByDate.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByServiceType() {
        log.info("...shouldFindActiveAdvertisementByServiceType...");
        String searchedServiceType = "Request";
        List<Advertisement> getAllAdvertisementsByDate = new AllAdsViewController()
                .findActiveAdvertisementByServiceType(searchedServiceType, advertisementDao);
        assertEquals(1, getAllAdvertisementsByDate.size());
    }
}
