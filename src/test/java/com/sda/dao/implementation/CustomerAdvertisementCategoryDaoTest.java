package com.sda.dao.implementation;

import com.sda.entity.Address;
import com.sda.entity.Advertisement;
import com.sda.entity.Category;
import com.sda.entity.Customer;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import static com.sda.util.Constants.DATE_FORMAT;
import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerAdvertisementCategoryDaoTest {

    private static CustomerDao customerDao;
    private static AdvertisementDao advertisementDao;
    private static CategoryDao categoryDao;
    private static Customer customer;
    private static Customer customerToBeUpdated;
    private static Customer customerToBeDeleted;
    private static Category rentSuperCategory;
    private static Category rentSubCategory;
    private static Category rentSuperCategoryToBeUpdated;
    private static Category rentSuperCategoryToBeDeleted;
    private static Advertisement advertisement;
    private static Advertisement advertisementToBeUpdated;
    private static Advertisement advertisementToBeDeleted;
    Logger log = Logger.getLogger(CustomerAdvertisementCategoryDaoTest.class.getName());

    @BeforeClass
    public static void setUp() throws ParseException {

        customerDao = new CustomerDao();
        advertisementDao = new AdvertisementDao();
        categoryDao = new CategoryDao();
        customer = new Customer();
        customer.setLogin("Alibaba");
        customer.setPassword("123456");
        customer.setFullName("Ali Muhammed");
        customerToBeUpdated = new Customer();
        customerToBeUpdated.setLogin("StanLogin");
        customerToBeUpdated.setPassword("StanPass");
        customerToBeUpdated.setFullName("Stan Ra");
        customerToBeDeleted = new Customer();
        customerToBeDeleted.setLogin("AlexLogin");
        customerToBeDeleted.setPassword("AlexPass");
        customerToBeDeleted.setFullName("Alex Mighty");
        rentSuperCategory = new Category(null, "Pet Care");
        rentSubCategory = new Category(rentSuperCategory, "Cat Care");
        rentSuperCategoryToBeUpdated = new Category(null, "Baking");
        rentSuperCategoryToBeDeleted = new Category(null, "Clean");
        advertisement = Advertisement.builder()
                .subject("Can take care of your cat")
                .description("blablabla")
                .price(new BigDecimal("2.5"))
                .startDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/1998"))
                .endDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/2021"))
                .serviceType(Advertisement.ServiceType.OFFER)
                .category(rentSuperCategory)
                .address(new Address("Estonia", "Tallinn"))
                .customer(customer)
                .build();
        advertisementToBeUpdated = Advertisement.builder()
                .subject("Can take care of your small cats")
                .description("some dummy description2")
                .price(new BigDecimal("103"))
                .startDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/2008"))
                .endDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/2009"))
                .serviceType(Advertisement.ServiceType.OFFER)
                .category(rentSuperCategory)
                .address(new Address("USA", "LA"))
                .customer(customer)
                .build();
        advertisementToBeDeleted = Advertisement.builder()
                .subject("Can take care of cats")
                .description("lallaala")
                .price(new BigDecimal("26.0"))
                .startDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/1996"))
                .endDate(new SimpleDateFormat(DATE_FORMAT).parse("31/12/2006"))
                .serviceType(Advertisement.ServiceType.OFFER)
                .category(rentSuperCategory)
                .address(new Address("France", "Paris"))
                .customer(null)
                .build();
        categoryDao.save(rentSuperCategory);
        categoryDao.save(rentSubCategory);
        categoryDao.save(rentSuperCategoryToBeUpdated);
        categoryDao.save(rentSuperCategoryToBeDeleted);
        customerDao.save(customer);
        customerDao.save(customerToBeUpdated);
        customerDao.save(customerToBeDeleted);
        advertisementDao.save(advertisement);
        advertisementDao.save(advertisementToBeUpdated);
        advertisementDao.save(advertisementToBeDeleted);
    }

    @Test
    public void should1SaveCategoryWithSubcategory() {
        log.info("...shouldSaveCategoryWithSubcategory...");
        assertEquals("Pet Care", categoryDao.get(2L).getSuperCategory().getName());
        assertEquals(1, categoryDao.get(1L).getSubCategories().size());
        assertEquals("Cat Care", categoryDao.get(1L).getSubCategories().get(0).getName());
    }

    @Test
    public void should2SaveCategoryWithCreatedAndUpdatedTimeStamp() {
        log.info("...shouldSaveCategoryWithCreatedAndUpdatedTimeStamp...");
        Category shouldGetCategoryById = categoryDao.get(1L);
        assertNotNull(shouldGetCategoryById.getCreatedOn().toString());
        assertNotNull(shouldGetCategoryById.getUpdatedOn().toString());
    }

    @Test
    public void should3GetCategoryById() {
        log.info("...shouldGetCategoryById...");
        Category shouldGetCategoryById = categoryDao.get(1L);
        assertNotNull(shouldGetCategoryById);
    }

    @Test
    public void should4GetAllCategory() {
        log.info("...shouldGetAllSuperCategory...");
        List<Category> getAllCategory = categoryDao.getAll();
        assertEquals(4, getAllCategory.size());
    }

    @Test
    public void should5UpdateCategoryName() {
        log.info("...shouldUpdateCategoryName...");
        Category category = categoryDao.get(4L);
        String newCategoryName = "Renting";
        category.setName(newCategoryName);
        categoryDao.update(category);
        Category updatedCategory = categoryDao.get(4L);
        assertEquals(newCategoryName, updatedCategory.getName());
    }

    @Test
    public void should6DeleteCategory() {
        log.info("...shouldDeleteCategory...");
        Category shouldBeSavedCategory = categoryDao.get(4L);
        assertNotNull(shouldBeSavedCategory);
        categoryDao.delete(shouldBeSavedCategory);
        Category shouldBeDeletedCategory = categoryDao.get(4L);
        assertNull(shouldBeDeletedCategory);
    }

    @Test
    public void should1SaveCustomer() {
        log.info("...shouldSaveCustomer...");
        Customer shouldBeSavedCustomer = customerDao.get(1L);
        assertNotNull(shouldBeSavedCustomer);
    }

    @Test
    public void should2GetCustomerById() {
        log.info("...shouldGetCustomerById...");
        Customer shouldGetCustomerById = customerDao.get(1L);
        assertNotNull(shouldGetCustomerById);
    }

    @Test
    public void should3GetAllCustomers() {
        log.info("...shouldGetAllCustomers...");
        List<Customer> getAllCustomers = customerDao.getAll();
        assertEquals(3, getAllCustomers.size());
    }

    @Test
    public void should4UpdateCustomerPassword() {
        log.info("...shouldUpdateCustomerPassword...");
        Customer customer = customerDao.get(3L);
        String newCustomerPassword = "qwerty";
        customer.setPassword(newCustomerPassword);
        customerDao.update(customer);
        Customer updatedCustomer = customerDao.get(3L);
        assertEquals(newCustomerPassword, updatedCustomer.getPassword());
    }


    @Test
    public void should5DeleteCustomer() {
        log.info("...shouldDeleteCustomer...");
        Customer shouldBeSavedCustomer = customerDao.get(4L);
        customerDao.delete(shouldBeSavedCustomer);
        Customer shouldBeDeletedCustomer = customerDao.get(4L);
        assertNull(shouldBeDeletedCustomer);
    }

    @Test
    public void should1SaveAdvertisement() {
        log.info("...shouldSaveAdvertisement...");
        assertNotNull(advertisementDao.get(1L));
        assertNotNull(advertisementDao.get(2L));
        assertNotNull(advertisementDao.get(3L));
    }

    @Test
    public void should2SaveAdvertisementWithCreatedAndUpdatedTimeStamp() {
        log.info("...shouldSaveAdvertisementWithCreatedAndUpdatedTimeStamp...");
        Advertisement shouldGetAdvertisementById = advertisementDao.get(1L);
        assertNotNull(shouldGetAdvertisementById.getCreatedOn().toString());
        assertNotNull(shouldGetAdvertisementById.getUpdatedOn().toString());
    }

    @Test
    public void should3SaveAdvertisementAsListToCustomer() {
        log.info("...shouldSaveAdvertisementAsListToCustomer...");
        assertEquals(2,
                customerDao.getByLogin(
                        customer.getLogin()).getUserAdvertisements().size());
    }

    @Test
    public void should4GetAdvertisementById() {
        log.info("...shouldGetAdvertisementById...");
        Advertisement shouldGetAdvertisementById1 = advertisementDao.get(1L);
        assertNotNull(shouldGetAdvertisementById1);
    }

    @Test
    public void should5GetAllAdvertisements() {
        log.info("...shouldGetAllAdvertisements...");
        List<Advertisement> getAllAdvertisements = advertisementDao.getAll();
        assertEquals(3, getAllAdvertisements.size());
    }

    @Test
    public void should6UpdateAdvertisementSubject() {
        log.info("...shouldUpdateAdvertisementSubject...");
        Advertisement shouldUpdateAdvertisementSubject = advertisementDao.get(1L);
        String newAdvertisementSubject = "Renting";
        shouldUpdateAdvertisementSubject.setSubject(newAdvertisementSubject);
        advertisementDao.update(shouldUpdateAdvertisementSubject);
        Advertisement updatedAdvertisement = advertisementDao.get(1L);
        assertEquals(newAdvertisementSubject, updatedAdvertisement.getSubject());
    }

    @Test
    public void should7DeleteAdvertisement() {
        log.info("...shouldDeleteAdvertisement...");
        Advertisement shouldBeSavedAd = advertisementDao.get(3L);
        advertisementDao.delete(shouldBeSavedAd);
        Advertisement shouldBeDeletedAd = advertisementDao.get(3L);
        assertNull(shouldBeDeletedAd);
    }
}
