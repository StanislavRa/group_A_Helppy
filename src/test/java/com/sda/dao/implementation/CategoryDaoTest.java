package com.sda.dao.implementation;

import com.sda.entity.Category;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * @author StanislavR
 */
public class CategoryDaoTest {
    Logger log = Logger.getLogger(AddressDaoTest.class.getName());

    @Test
    public void shouldSaveCategoryWithSubcategory() {

        log.info("...shouldSaveCategoryWithSubcategory...");

        CategoryDao categoryDao = new CategoryDao();

        Category rentSuperCategory = new Category(null,"Rent");

        categoryDao.save(rentSuperCategory);
        Category rentSubCategory1 = new Category(rentSuperCategory,"Car Rent");
        categoryDao.save(rentSubCategory1);
        Category rentSubCategory2 = new Category(rentSuperCategory,"Apartment Rent");
        categoryDao.save(rentSubCategory2);

        Assert.assertEquals("Rent",categoryDao.get(2L).getSuperCategory().getName());
        Assert.assertEquals(2, categoryDao.get(1L).getSubCategories().size());
        Assert.assertEquals("Car Rent",categoryDao.get(1L).getSubCategories().get(0).getName());



    }
}
