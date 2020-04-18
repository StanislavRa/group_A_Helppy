package com.sda.dao.implementation;

import com.sda.entity.Category;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Work only one-by-one
 */

public class CategoryDaoTest {
    String connectionToDatabaseCreate = "hibernateUnitTest.cfg.xml";

    Logger log = Logger.getLogger(AdvertisementDaoTest.class.getName());

    CategoryDao categoryDao;

    Category rentSuperCategory;
    Category rentSubCategory1;
    Category rentSubCategory2;

    @Before
    public void setUp() {

        categoryDao = new CategoryDao(connectionToDatabaseCreate);

        rentSuperCategory = new Category(null, "Rent");

        rentSubCategory1 = new Category(rentSuperCategory, "Car Rent");
        rentSubCategory2 = new Category(rentSuperCategory, "Apartment Rent");

        categoryDao.save(rentSuperCategory);
        categoryDao.save(rentSubCategory1);
        categoryDao.save(rentSubCategory2);
    }

    @Test
    public void shouldSaveCategoryWithSubcategory() {

        log.info("...shouldSaveCategoryWithSubcategory...");

        Assert.assertEquals("Rent", categoryDao.get(2L).getSuperCategory().getName());
        Assert.assertEquals(2, categoryDao.get(1L).getSubCategories().size());
        Assert.assertEquals("Car Rent", categoryDao.get(1L).getSubCategories().get(0).getName());
    }

    @Test
    public void shouldGetCategoryById() {

        log.info("...shouldGetCategoryById...");

        Category shouldGetCategoryById = categoryDao.get(1L);

        Assert.assertEquals(shouldGetCategoryById, rentSuperCategory);
    }

    @Test
    public void shouldGetAllCategory() {

        log.info("...shouldGetAllSuperCategory...");

        List<Category> getAllCategory = categoryDao.getAll();

        Assert.assertEquals(3, getAllCategory.size());
    }

    @Test
    public void shouldUpdateCategoryName() {

        log.info("...shouldUpdateCategoryName...");

        Category category = categoryDao.get(1L);

        String newCategoryName = "Renting";

        category.setName(newCategoryName);

        categoryDao.update(category);

        Category updatedCategory = categoryDao.get(1L);

        Assert.assertEquals(newCategoryName, updatedCategory.getName());
    }

    @Test
    public void shouldDeleteCategory() {

        log.info("...shouldDeleteCategory...");

        Category shouldBeSavedCategory = categoryDao.get(2L);
        Assert.assertNotNull(shouldBeSavedCategory);

        categoryDao.delete(shouldBeSavedCategory);

        Category shouldBeDeletedCategory = categoryDao.get(2L);
        Assert.assertNull(shouldBeDeletedCategory);
    }

    @Test
    public void shouldSaveCategoryWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveCategoryWithCreatedAndUpdatedTimeStamp...");

        Category shouldGetCategoryById = categoryDao.get(1L);

        Assert.assertNotNull(shouldGetCategoryById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetCategoryById.getUPDATED_ON().toString());
    }
}
