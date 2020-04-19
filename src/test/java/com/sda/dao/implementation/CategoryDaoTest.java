package com.sda.dao.implementation;

import com.sda.entity.Category;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.logging.Logger;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryDaoTest {
    private static CategoryDao categoryDao;
    private static Category rentSuperCategory;
    private static Category rentSuperCategoryToBeDeleted;
    private static Category rentSuperCategoryToBeUpdated;
    private static Category rentSubCategory1;
    private static Category rentSubCategory2;
    private static String connectionToDatabaseCreate;

    Logger log = Logger.getLogger(AdvertisementDaoTest.class.getName());

    @BeforeClass
    public static void setUp() {

        connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";

        categoryDao = new CategoryDao(connectionToDatabaseCreate);

        rentSuperCategory = new Category(null, "Rent");
        rentSuperCategoryToBeDeleted = new Category(null, "Clean");
        rentSuperCategoryToBeUpdated = new Category(null, "Baking");

        rentSubCategory1 = new Category(rentSuperCategory, "Car Rent");
        rentSubCategory2 = new Category(rentSuperCategory, "Apartment Rent");

        categoryDao.save(rentSuperCategory);
        categoryDao.save(rentSubCategory1);
        categoryDao.save(rentSubCategory2);
        categoryDao.save(rentSuperCategoryToBeUpdated);
        categoryDao.save(rentSuperCategoryToBeDeleted);
    }

    @Test
    public void should1SaveCategoryWithSubcategory() {

        log.info("...shouldSaveCategoryWithSubcategory...");

        Assert.assertEquals("Rent", categoryDao.get(2L).getSuperCategory().getName());
        Assert.assertEquals(2, categoryDao.get(1L).getSubCategories().size());
        Assert.assertEquals("Car Rent", categoryDao.get(1L).getSubCategories().get(0).getName());
    }

    @Test
    public void should2SaveCategoryWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveCategoryWithCreatedAndUpdatedTimeStamp...");

        Category shouldGetCategoryById = categoryDao.get(1L);

        Assert.assertNotNull(shouldGetCategoryById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetCategoryById.getUPDATED_ON().toString());
    }

    @Test
    public void should3GetCategoryById() {

        log.info("...shouldGetCategoryById...");

        Category shouldGetCategoryById = categoryDao.get(1L);

        Assert.assertEquals(shouldGetCategoryById, rentSuperCategory);
    }

    @Test
    public void should4GetAllCategory() {

        log.info("...shouldGetAllSuperCategory...");

        List<Category> getAllCategory = categoryDao.getAll();

        Assert.assertEquals(5, getAllCategory.size());
    }

    @Test
    public void should5UpdateCategoryName() {

        log.info("...shouldUpdateCategoryName...");

        Category category = categoryDao.get(4L);

        String newCategoryName = "Renting";

        category.setName(newCategoryName);

        categoryDao.update(category);

        Category updatedCategory = categoryDao.get(4L);

        Assert.assertEquals(newCategoryName, updatedCategory.getName());
    }

    @Test
    public void should6DeleteCategory() {

        log.info("...shouldDeleteCategory...");

        Category shouldBeSavedCategory = categoryDao.get(5L);
        Assert.assertNotNull(shouldBeSavedCategory);

        categoryDao.delete(shouldBeSavedCategory);

        Category shouldBeDeletedCategory = categoryDao.get(5L);
        Assert.assertNull(shouldBeDeletedCategory);
    }

}
