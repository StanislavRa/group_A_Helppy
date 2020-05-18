package com.sda.util;

import com.sda.dao.implementation.*;
import com.sda.entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sda.util.Constants.DATE_FORMAT;

public class DummyData {
    private final CustomerDao customerDao;
    private final AdvertisementDao advertisementDao;
    private final CategoryDao categoryDao;
    private final CityDao cityDao;
    private final CountryDao countryDao;
    private final AddressDao addressDao;
    private final List<Address> addressList;
    public DummyData() throws ParseException {
        cityDao = new CityDao();
        countryDao = new CountryDao();
        addressDao = new AddressDao();
        categoryDao = new CategoryDao();
        customerDao = new CustomerDao();
        advertisementDao = new AdvertisementDao();
        List<Country> countryList = Arrays.asList(
                new Country("Estonia"),
                new Country("Finland"),
                new Country("Sweden"));
        List<City> estonianCityList = Arrays.asList(
                new City("Tallinn"),
                new City("Narva"),
                new City("Tartu"));
        List<City> finnishCityList = Arrays.asList(
                new City("Helsinki"),
                new City("Tampere"),
                new City("Turku"));
        List<City> swedishCityList = Arrays.asList(
                new City("Stockholm"),
                new City("Upsala"),
                new City("Malmö"));
        addressList = new ArrayList<>();
        List<Category> superCategoryList = Arrays.asList(
                new Category("Rent"),
                new Category("Clean")
        );
        List<Category> rentSubCategoryList = Arrays.asList(
                new Category("Car Rent"),
                new Category("Ship Rent")
        );
        List<Category> cleanSubCategoryList = Arrays.asList(
                new Category("Office Cleaning"),
                new Category("Apartment Cleaning")
        );
        List<Customer> customerList = Arrays.asList(
                new Customer("Demi", "0000", "Demiko Avaliani"),
                new Customer("Mariam", "1111", "Mariam Dgebuadze"),
                new Customer("Oleks", "2222", "Oleksandr Shpakovski"),
                new Customer("Stan", "3333", "Stanislav Ratšinski")
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
        List<Advertisement> advertisementList = Arrays.asList(
                new Advertisement(
                        "Clean Fast",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor" +
                                " incididunt ut labore et dolore magna aliqua. Fringilla urna porttitor rhoncus dolor" +
                                " purus non enim. Vitae et leo duis ut diam. Massa tincidunt dui ut ornare lectus sit" +
                                " amet. Id diam maecenas ultricies mi eget mauris. Aliquet nibh praesent tristique" +
                                " magna.",
                        "2.5",
                        // Define a constant instead of duplication DATE_FORMAT 10 times.
                        new SimpleDateFormat(DATE_FORMAT).parse("31/12/1998"),
                        new SimpleDateFormat(DATE_FORMAT).parse("31/12/2021"),
                        Advertisement.ServiceType.OFFER,
                        superCategoryList.get(1),
                        customerList.get(1),
                        addressList.get(1)),
                new Advertisement(
                        "Car Rent",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor" +
                                " incididunt ut labore et dolore magna aliqua. Erat velit scelerisque in dictum non." +
                                " Ligula ullamcorper malesuada proin libero nunc. Condimentum lacinia quis vel eros." +
                                " Facilisis mauris sit amet massa vitae tortor condimentum." +
                                " Purus faucibus ornare suspendisse sed nisi lacus sed viverra tellus." +
                                " Lacus sed turpis tincidunt id aliquet." +
                                " Ullamcorper malesuada proin libero nunc consequat interdum varius sit.",
                        "103.3",
                        new SimpleDateFormat(DATE_FORMAT).parse("21/02/2005"),
                        new SimpleDateFormat(DATE_FORMAT).parse("31/12/2020"),
                        Advertisement.ServiceType.REQUEST,
                        superCategoryList.get(0),
                        customerList.get(0),
                        addressList.get(0)),
                new Advertisement(
                        "Clean Your Office!",
                        "some dummy description3",
                        "152",
                        new SimpleDateFormat(DATE_FORMAT).parse("13/01/2001"),
                        new SimpleDateFormat(DATE_FORMAT).parse("17/04/2020"),
                        Advertisement.ServiceType.REQUEST,
                        superCategoryList.get(1),
                        customerList.get(2),
                        addressList.get(2)),
                new Advertisement(
                        "Rent Equipment from Us!!",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor" +
                                " incididunt ut labore et dolore magna aliqua." +
                                " Cras fermentum odio eu feugiat pretium nibh ipsum." +
                                " Nec dui nunc mattis enim ut." +
                                " Sit amet luctus venenatis lectus magna fringilla urna porttitor." +
                                " Eu augue ut lectus arcu bibendum at varius vel pharetra.",
                        "152",
                        new SimpleDateFormat(DATE_FORMAT).parse("13/01/2019"),
                        new SimpleDateFormat(DATE_FORMAT).parse("31/12/2022"),
                        Advertisement.ServiceType.OFFER,
                        superCategoryList.get(0),
                        customerList.get(3),
                        addressList.get(3)),
                new Advertisement(
                        "Clean your home!!",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                                " Sit amet porttitor eget dolor morbi non." +
                                " Tincidunt eget nullam non nisi est." +
                                " Non nisi est sit amet facilisis magna etiam tempor." +
                                " Consequat mauris nunc congue nisi." +
                                " Tristique magna sit amet purus gravida quis blandit turpis cursus.",
                        "152",
                        new SimpleDateFormat(DATE_FORMAT).parse("13/01/2019"),
                        new SimpleDateFormat(DATE_FORMAT).parse("17/04/2022"),
                        Advertisement.ServiceType.REQUEST,
                        superCategoryList.get(1),
                        customerList.get(2),
                        addressList.get(4))
        );
        saveAdvertisementsToDB(advertisementList);
    }
    /* You could simply use a Map<Country,City> instead of two separate lists. Opting an optimized data structure is a
     *  sign of smart architecture*/
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
    // This method is never used. Try to avoid redundancy
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
    // This method is never used. Try to avoid redundancy
    private Customer settingAdvertisementToCustomer(Advertisement advertisement, Customer customer) {
        List<Advertisement> ads = customer.getUserAdvertisements();
        ads.add(advertisement);
        customer.setUserAdvertisements(ads);
        return customer;
    }
}
