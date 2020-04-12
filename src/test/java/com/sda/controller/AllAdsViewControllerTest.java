package com.sda.controller;

import com.sda.dao.implementation.CityDao;
import com.sda.entity.Advertisement;
import com.sda.util.DummyData;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AllAdsViewControllerTest {


    @Test
    public void shouldGetListOfCityNames() throws ParseException {

        new DummyData();
         final String DB_SETTINGS = "hibernateCreate.cfg.xml";
         CityDao cityDao = new CityDao(DB_SETTINGS);

        AllAdsViewController allAdsViewControllerTest = new AllAdsViewController();
        String estoniaCountry = "Estonia";
        List<String> getAllAdvertisementsByCountry = allAdsViewControllerTest.getListOfCityNamesByCountry(estoniaCountry,cityDao);

        Assert.assertEquals(3, getAllAdvertisementsByCountry.size());

    }

    @Test
    public void shouldFindActiveAdvertisementByCategory() throws ParseException {

        new DummyData();
        AllAdsViewController allAdsViewControllerTest = new AllAdsViewController();


        String tallinnCity = "Rent";
        List<Advertisement> getAllAdvertisementsByCategory = allAdsViewControllerTest.findActiveAdvertisementByCategory(tallinnCity);

        Assert.assertEquals(2, getAllAdvertisementsByCategory.size());
    }


    @Test
    public void shouldFindActiveAdvertisementByCity() throws ParseException {

        new DummyData();
        AllAdsViewController allAdsViewControllerTest = new AllAdsViewController();
        String tallinnCity = "Tallinn";
        List<Advertisement> getAllAdvertisementsByCity = allAdsViewControllerTest.findActiveAdvertisementByCity(tallinnCity);

        Assert.assertEquals(1, getAllAdvertisementsByCity.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByCountry() throws ParseException {

        new DummyData();
        AllAdsViewController allAdsViewControllerTest = new AllAdsViewController();
        String estoniaCountry = "Estonia";
        List<Advertisement> getAllAdvertisementsByCountry = allAdsViewControllerTest.findActiveAdvertisementByCountry(estoniaCountry);

        Assert.assertEquals(2, getAllAdvertisementsByCountry.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByPrice() throws ParseException {

        new DummyData();
        AllAdsViewController allAdsViewControllerTest = new AllAdsViewController();

        List<Advertisement> getAllAdvertisementsByPrice = allAdsViewControllerTest.findActiveAdvertisementByPrice(
                new BigDecimal(151),
                new BigDecimal(170));

        Assert.assertEquals(2, getAllAdvertisementsByPrice.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByDate() throws ParseException {

        new DummyData();
        AllAdsViewController allAdsViewControllerTest = new AllAdsViewController();

        String startDateString2 = "11/01/2019";
        Date startDateTest = new SimpleDateFormat("dd/MM/yyyy").parse(startDateString2);

        String endDateString2 = "01/01/2023";
        Date endDaterTest = new SimpleDateFormat("dd/MM/yyyy").parse(endDateString2);


        List<Advertisement> getAllAdvertisementsByDate = allAdsViewControllerTest.findActiveAdvertisementByDate(
                startDateTest, endDaterTest);

        Assert.assertEquals(2, getAllAdvertisementsByDate.size());
    }

    @Test
    public void shouldFindActiveAdvertisementByServiceType() throws ParseException {

        new DummyData();
        AllAdsViewController allAdsViewControllerTest = new AllAdsViewController();

        List<Advertisement> getAllAdvertisementsByDate =  allAdsViewControllerTest.findActiveAdvertisementByServiceType(
                Advertisement.ServiceType.OFFER.toString());
        Assert.assertEquals(2, getAllAdvertisementsByDate.size());
    }
}
