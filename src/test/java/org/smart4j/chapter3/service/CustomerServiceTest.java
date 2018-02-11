package org.smart4j.chapter3.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smart4j.framework.helper.DatabaseHelper;
import org.smart4j.chapter3.model.Customer;
import org.smart4j.framework.util.Logger;

import java.io.IOException;
import java.util.List;

public class CustomerServiceTest {
    private final CustomerService customerService;

    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    @Before
    public void init() throws IOException {
        String file = "sql/customer_init.sql";
        DatabaseHelper.excuteSq1File(file);
    }

    @Test
    public void test_getCustomerList() {
        List<Customer> customerList = customerService.getCustomerList("%customer%");
        Assert.assertEquals(2, customerList.size());

        Logger.error(this, "test", new RuntimeException("test2"));
    }

    @Test
    public void test_getCustomerList_fail() {
        List<Customer> customerList = customerService.getCustomerList("%customer%");
        Assert.assertEquals(1, customerList.size());
    }
}
