package com.sda.util;

import com.sda.dao.implementation.*;
import com.sda.entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DummyData {

    String connectionToDatabaseCreate = "hibernateCreate.cfg.xml";

    CustomerDao customerDao;
    AdvertisementDao advertisementDao;
    CategoryDao categoryDao;
    CityDao cityDao;
    CountryDao countryDao;
    AddressDao addressDao;

    List<Country> countryList;

    List<City> estonianCityList;
    List<City> finnishCityList;
    List<City> swedishCityList;

    List<Address> addressList;

    List<Category> superCategoryList;
    List<Category> rentSubCategoryList;
    List<Category> cleanSubCategoryList;

    List<Customer> customerList;

    List<Advertisement> advertisementList;

    public DummyData() throws ParseException {

        cityDao = new CityDao(connectionToDatabaseCreate);
        countryDao = new CountryDao(connectionToDatabaseCreate);
        addressDao = new AddressDao(connectionToDatabaseCreate);
        categoryDao = new CategoryDao(connectionToDatabaseCreate);
        customerDao = new CustomerDao(connectionToDatabaseCreate);
        advertisementDao = new AdvertisementDao(connectionToDatabaseCreate);

        countryList = Arrays.asList(
                new Country("Estonia"),
                new Country("Finland"),
                new Country("Sweden"));
        estonianCityList = Arrays.asList(
                new City("Tallinn"),
                new City("Narva"),
                new City("Tartu"));
        finnishCityList = Arrays.asList(
                new City("Helsinki"),
                new City("Tampere"),
                new City("Turku"));
        swedishCityList = Arrays.asList(
                new City("Stockholm"),
                new City("Upsala"),
                new City("Malmö"));

        addressList = new ArrayList<>();

        superCategoryList = Arrays.asList(
                new Category("Rent"),
                new Category("Clean")
        );

        rentSubCategoryList = Arrays.asList(
                new Category("Car Rent"),
                new Category("Ship Rent")
        );

        cleanSubCategoryList = Arrays.asList(
                new Category("Office Cleaning"),
                new Category("Apartment Cleaning")
        );

        customerList = Arrays.asList(
                new Customer("Pjotr", "123456", "Petr III"),
                new Customer("Dima", "qwe", "Dmitry Peskov"),
                new Customer("Olga", "0000", "Olga Demina"),
                new Customer("John", "1111", "John Smith")
        );

        settingCityListToCountry(estonianCityList, countryList.get(0));
        settingCityListToCountry(finnishCityList, countryList.get(1));
        settingCityListToCountry(swedishCityList, countryList.get(2));

        settingAddressesFromCitiesList(estonianCityList);
        settingAddressesFromCitiesList(finnishCityList);
        settingAddressesFromCitiesList(swedishCityList);

        settingSuperCategoryToSubCategory(rentSubCategoryList, superCategoryList.get(0));
        settingSuperCategoryToSubCategory(cleanSubCategoryList, superCategoryList.get(1));

        saveCountriesToDB(countryList);
        saveCitiesToDB(estonianCityList);
        saveCitiesToDB(finnishCityList);
        saveCitiesToDB(swedishCityList);

        saveCategoriesToDB(superCategoryList);
        saveCategoriesToDB(rentSubCategoryList);
        saveCategoriesToDB(cleanSubCategoryList);

        saveCustomersToDB(customerList);

        advertisementList = Arrays.asList(
                new Advertisement(
                        "Clean Fast",
                        "blablabla",
                        "2.5",
                        new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998"),
                        new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2021"),
                        Advertisement.ServiceType.OFFER,
                        superCategoryList.get(1),
                        customerList.get(1),
                        addressList.get(1)),
                new Advertisement(
                        "Car Rent",
                        "some dummy description2",
                        "103.3",
                        new SimpleDateFormat("dd/MM/yyyy").parse("21/02/2005"),
                        new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2020"),
                        Advertisement.ServiceType.REQUEST,
                        superCategoryList.get(0),
                        customerList.get(0),
                        addressList.get(0)),
                new Advertisement(
                        "Clean Your Office!",
                        "some dummy description3",
                        "152",
                        new SimpleDateFormat("dd/MM/yyyy").parse("13/01/2001"),
                        new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2019"),
                        Advertisement.ServiceType.REQUEST,
                        superCategoryList.get(1),
                        customerList.get(2),
                        addressList.get(2)),
                new Advertisement(
                        "Rent Equipment from Us!!",
                        "some dummy description4",
                        "152",
                        new SimpleDateFormat("dd/MM/yyyy").parse("13/01/2019"),
                        new SimpleDateFormat("dd/MM/yyyy").parse("31/12/2022"),
                        Advertisement.ServiceType.OFFER,
                        superCategoryList.get(0),
                        customerList.get(3),
                        addressList.get(3)),
                new Advertisement(
                        "Clean your home!!",
                        "some dummy description5",
                        "152",
                        new SimpleDateFormat("dd/MM/yyyy").parse("13/01/2019"),
                        new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2022"),
                        Advertisement.ServiceType.REQUEST,
                        superCategoryList.get(1),
                        customerList.get(2),
                        addressList.get(4))
        );

        saveAdvertisementsToDB(advertisementList);
    }

    private void settingCityListToCountry(List<City> cityList, Country country) {
        for (City city : cityList) {
            city.setCountry(country);
        }
    }

    private void settingAddressesFromCitiesList(List<City> cityList) {
        for (City city : cityList) {
            addressList.add(new Address(city.getCountry().getCountryName(), city.getCityName()));
        }
    }

    private void settingSuperCategoryToSubCategory(List<Category> subCategoryList, Category superCategory) {
        for (Category category : subCategoryList) {
            category.setSuperCategory(superCategory);
        }
    }

    private void saveCountriesToDB(List<Country> countryList) {
        for (Country country : countryList) {
            countryDao.save(country);
        }
    }

    private void saveCitiesToDB(List<City> cityList) {
        for (City city : cityList) cityDao.save(city);
    }


    private void saveAddressesToDB(List<Address> addressList) {
        for (Address address : addressList) {
            addressDao.save(address);
        }
    }

    private void saveCategoriesToDB(List<Category> categoryList) {
        for (Category category : categoryList) {
            categoryDao.save(category);
        }
    }

    private void saveCustomersToDB(List<Customer> customerList) {
        for (Customer customer : customerList) {
            customerDao.save(customer);
        }
    }

    private void saveAdvertisementsToDB(List<Advertisement> advertisementList) {
        for (Advertisement advertisement : advertisementList) {
            advertisementDao.save(advertisement);
        }
    }

    private Customer settingAdvertisementToCustomer(Advertisement advertisement, Customer customer) {
        List<Advertisement> ads = customer.getUserAdvertisements();
        ads.add(advertisement);
        customer.setUserAdvertisements(ads);

        return customer;
    }
}
