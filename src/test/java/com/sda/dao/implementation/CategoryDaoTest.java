package com.sda.dao.implementation;

import com.sda.entity.Category;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
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

        //Category rentSuperCategory = new Category(null,"Caring");

        Category rentSuperCategory = new Category("Photo");
       Category rentSubCategory1 = new Category(rentSuperCategory,"Nanny");

        Category rentSubCategory2 = new Category(rentSuperCategory,"Pet Caring");


        rentSuperCategory.setSubCategories(Arrays.asList(rentSubCategory1, rentSubCategory2));
        categoryDao.save(rentSuperCategory);


        Assert.assertEquals(2, categoryDao.get(1L).getSubCategories().size());
    }

    @Test
    public void shouldUpdateCategoryName() {

        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.get(1L);

        String newCategoryName = "Renting";

        category.setName(newCategoryName);

        categoryDao.update(category);

        Category updatedCategory = categoryDao.get(1L);

        Assert.assertEquals(newCategoryName,updatedCategory.getName());
    }

    @Test
    public void shouldDeleteCategory() {

        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.get(6L);

        categoryDao.delete(category);

        Category deletedCategory = categoryDao.get(6L);

        Assert.assertNull(deletedCategory);

    }
}
