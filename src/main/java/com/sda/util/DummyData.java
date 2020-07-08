package com.sda.util;

import com.sda.dao.implementation.*;
import com.sda.entity.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.sda.util.Constants.DATE_FORMAT;

public class DummyData {

    private final CustomerDao customerDao;
    private final AdvertisementDao advertisementDao;
    private final CategoryDao categoryDao;
    private final CityDao cityDao;
    private final CountryDao countryDao;

    public DummyData() throws ParseException {

        cityDao = new CityDao();
        countryDao = new CountryDao();
        categoryDao = new CategoryDao();
        customerDao = new CustomerDao();
        advertisementDao = new AdvertisementDao();

        Country estonia = new Country("Estonia");
        Country finland = new Country("Finland");
        Country sweden = new Country("Sweden");

        countryDao.save(estonia);
        countryDao.save(finland);
        countryDao.save(sweden);

        City tallinn = new City("Tallinn", estonia);
        City narva = new City("Narva", estonia);
        City tartu = new City("Tartu", estonia);
        City helsinki = new City("Helsinki", finland);
        City tampere = new City("Tampere", finland);
        City turku = new City("Turku", finland);
        City stockholm = new City("Stockholm", sweden);
        City uppsala = new City("Uppsala", sweden);
        City malmo = new City("Malmö", sweden);

        cityDao.save(tallinn);
        cityDao.save(narva);
        cityDao.save(tartu);
        cityDao.save(helsinki);
        cityDao.save(tampere);
        cityDao.save(turku);
        cityDao.save(stockholm);
        cityDao.save(uppsala);
        cityDao.save(malmo);

        Category rentSuperCategory = new Category("Rent");
        Category cleanSuperCategory = new Category("Clean");

        categoryDao.save(rentSuperCategory);
        categoryDao.save(cleanSuperCategory);

        Category carRentCategory = new Category(rentSuperCategory, "Car Rent");
        Category shipRentCategory = new Category(rentSuperCategory, "Ship Rent");

        Category officeCleaningCategory = new Category(cleanSuperCategory, "Office Cleaning");
        Category apartmentCleaningCategory = new Category(cleanSuperCategory, "Apartment Cleaning");

        categoryDao.save(carRentCategory);
        categoryDao.save(shipRentCategory);
        categoryDao.save(officeCleaningCategory);
        categoryDao.save(apartmentCleaningCategory);

        Customer demi = new Customer("Demi", "0000", "Demiko Avaliani");
        Customer mariam = new Customer("Mariam", "1111", "Mariam Dgebuadze");
        Customer oleks = new Customer("Oleks", "2222", "Oleksandr Shpakovski");
        Customer stan = new Customer("Stan", "3333", "Stanislav Ratšinski");

        customerDao.save(demi);
        customerDao.save(mariam);
        customerDao.save(oleks);
        customerDao.save(stan);

        Advertisement demiAd = Advertisement.builder()
                .subject("Clean Fast")
                .description("Fringilla urna porttitor rhoncus dolor" +
                        " purus non enim. Vitae et leo duis ut diam. Massa tincidunt dui ut ornare lectus sit" +
                        " amet. Id diam maecenas ultricies mi eget mauris. Aliquet nibh praesent tristique" +
                        " magna.")
                .price(new BigDecimal("2.5"))
                .startDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/1998"))
                .endDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/2021"))
                .serviceType(Advertisement.ServiceType.OFFER)
                .category(rentSuperCategory)
                .address(new Address(estonia.getCountryName(), tallinn.getCityName()))
                .customer(demi)
                .build();
        Advertisement mariAd = Advertisement.builder()
                .subject("Car Rent")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor" +
                        " incididunt ut labore et dolore magna aliqua. Fringilla urna porttitor rhoncus dolor" +
                        " purus non enim. Vitae et leo duis ut diam. Massa tincidunt dui ut ornare lectus sit" +
                        " amet. Id diam maecenas ultricies mi eget mauris. Aliquet nibh praesent tristique" +
                        " magna.")
                .price(new BigDecimal("303.3"))
                .startDate(new SimpleDateFormat(DATE_FORMAT).parse("21/02/2005"))
                .endDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/2020"))
                .serviceType(Advertisement.ServiceType.REQUEST)
                .category(cleanSuperCategory)
                .address(new Address(estonia.getCountryName(), narva.getCityName()))
                .customer(mariam)
                .build();
        Advertisement oleksAd = Advertisement.builder()
                .subject("Clean Your Office!")
                .description("Massa tincidunt dui ut ornare lectus sit" +
                        " amet. Id diam maecenas ultricies mi eget mauris. Aliquet nibh praesent tristique" +
                        " magna.")
                .price(new BigDecimal("152"))
                .startDate(new SimpleDateFormat(DATE_FORMAT).parse("13/01/2016"))
                .endDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/2022"))
                .serviceType(Advertisement.ServiceType.OFFER)
                .category(cleanSuperCategory)
                .address(new Address(finland.getCountryName(), tampere.getCityName()))
                .customer(oleks)
                .build();
        Advertisement stanAd = Advertisement.builder()
                .subject("Rent Equipment from Us!!")
                .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor" +
                        " incididunt ut labore et dolore magna aliqua.")
                .price(new BigDecimal("200"))
                .startDate(new SimpleDateFormat(DATE_FORMAT).parse("13/01/2013"))
                .endDate(new SimpleDateFormat(DATE_FORMAT).parse("17/04/2022"))
                .serviceType(Advertisement.ServiceType.REQUEST)
                .category(rentSuperCategory)
                .address(new Address(sweden.getCountryName(), malmo.getCityName()))
                .customer(stan)
                .build();

        advertisementDao.save(demiAd);
        advertisementDao.save(mariAd);
        advertisementDao.save(oleksAd);
        advertisementDao.save(stanAd);
    }
}
