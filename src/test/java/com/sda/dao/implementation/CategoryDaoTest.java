package com.sda.dao.implementation;

import com.sda.entity.Category;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author StanislavR
 */

public class CategoryDaoTest {
    Logger log = Logger.getLogger(CategoryDaoTest.class.getName());

    String connectionToDatabaseCreate  = "hibernateUnitTest.cfg.xml";

    CategoryDao categoryDao = new CategoryDao(connectionToDatabaseCreate);

    @Test
    public void shouldSaveCategoryWithSubcategory() {

        log.info("...shouldSaveCategoryWithSubcategory...");

        Category rentSuperCategory = new Category(null, "Rent");
        categoryDao.save(rentSuperCategory);

        Category rentSubCategory1 = new Category(rentSuperCategory,"Car Rent");
        categoryDao.save(rentSubCategory1);

        Category rentSubCategory2 = new Category(rentSuperCategory,"Apartment Rent");
        categoryDao.save(rentSubCategory2);

        Assert.assertEquals("Rent",categoryDao.get(2L).getSuperCategory().getName());
        Assert.assertEquals(2, categoryDao.get(1L).getSubCategories().size());
        Assert.assertEquals("Car Rent",categoryDao.get(1L).getSubCategories().get(0).getName());
    }

    @Test
    public void shouldGetCategoryById() {

        log.info("...shouldGetCategoryById...");

        Category rentSuperCategory = new Category(null, "Rent");
        categoryDao.save(rentSuperCategory);

        Category rentSubCategory1 = new Category(rentSuperCategory,"Car Rent");
        categoryDao.save(rentSubCategory1);

        Category shouldGetCategoryById = categoryDao.get(1L);

        Assert.assertEquals(shouldGetCategoryById,rentSuperCategory );

    }

    @Test
    public void shouldGetAllCategory() {

        log.info("...shouldGetAllSuperCategory...");

        Category rentSuperCategory = new Category(null, "Rent");
        categoryDao.save(rentSuperCategory);

        Category rentSubCategory1 = new Category(rentSuperCategory,"Car Rent");
        categoryDao.save(rentSubCategory1);

        Category rentSubCategory3 = new Category(rentSuperCategory,"Bike Rent");
        categoryDao.save(rentSubCategory3);

        List<Category> getAllCategory =  categoryDao.getAll();

        Assert.assertEquals(3, getAllCategory.size());

    }

    @Test
    public void shouldUpdateCategoryName() {

        log.info("...shouldUpdateCategoryName...");

        Category rentSuperCategory = new Category(null, "Rent");
        categoryDao.save(rentSuperCategory);

        Category category = categoryDao.get(1L);

        String newCategoryName = "Renting";

        category.setName(newCategoryName);

        categoryDao.update(category);

        Category updatedCategory = categoryDao.get(1L);

        Assert.assertEquals(newCategoryName,updatedCategory.getName());
    }

    @Test
    public void shouldDeleteCategory() {

        log.info("...shouldDeleteCategory...");

        Category rentSuperCategory = new Category(null, "Rent");
        categoryDao.save(rentSuperCategory);

        Category rentSubCategory1 = new Category(rentSuperCategory,"Car Rent");
        categoryDao.save(rentSubCategory1);

        Category rentSubCategory3 = new Category(rentSuperCategory,"Bike Rent");
        categoryDao.save(rentSubCategory3);


        Category shouldBeSavedCategory = categoryDao.get(2L);
        Assert.assertNotNull(shouldBeSavedCategory);

        categoryDao.delete(shouldBeSavedCategory);

        Category shouldBeDeletedCategory = categoryDao.get(2L);
        Assert.assertNull(shouldBeDeletedCategory);
    }

    @Test
    public void shouldSaveCategoryWithCreatedAndUpdatedTimeStamp() {

        log.info("...shouldSaveCategoryWithCreatedAndUpdatedTimeStamp...");

        Category rentSuperCategory = new Category(null, "Rent");
        categoryDao.save(rentSuperCategory);

        categoryDao.save(rentSuperCategory);

        Category shouldGetCategoryById = categoryDao.get(1L);

        Assert.assertNotNull(shouldGetCategoryById.getCREATED_ON().toString());
        Assert.assertNotNull(shouldGetCategoryById.getUPDATED_ON().toString());
    }
}
