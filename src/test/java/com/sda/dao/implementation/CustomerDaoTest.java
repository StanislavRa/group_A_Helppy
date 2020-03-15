package com.sda.dao.implementation;

import com.sda.entity.Customer;
import com.sda.entity.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author StanislavR
 */
public class CustomerDaoTest {
    @Test
    public void shouldSaveCustomer() {

        CustomerDao customerDao = new CustomerDao();
        Customer customer = new Customer();
        customer.setLogin("customer");
        customer.setPassword("pass");
        customer.setFullName("Olga");
        customerDao.save(customer);
        Assert.assertEquals("Olga", customerDao.get(1L).getFullName());
        Assert.assertEquals("pass", customerDao.get(1L).getPassword());
    }
}
