package org.smart4j.chapter3.service;

import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.helper.DatabaseHelper;
import org.smart4j.chapter3.model.Customer;
import org.smart4j.framework.util.Logger;
import org.smart4j.framework.annotation.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    //改造前
    public List<Customer> getCustomerList_before_refactor(String keyword) {
        List<Customer> customerList = new ArrayList<Customer>();
        Connection conn = DatabaseHelper.getConnection();
        if (conn != null) {
            try {
                String sql = "select * from customer a where a.name like '%" + keyword + "%'";
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setId(resultSet.getLong("id"));
                    customer.setName(resultSet.getString("name"));
                    customer.setContract(resultSet.getString("contract"));

                    customerList.add(customer);
                }
                return customerList;
            } catch (SQLException e) {
                Logger.error(this, "execute sql failure", e);
            } finally {
                DatabaseHelper.closeConnection();
            }
        }
        return null;
    }

    //改造后
    public List<Customer> getCustomerList(String keyword) {
        String sql = "select * from customer a where a.name like ?";
        List<Customer> customerList = DatabaseHelper.queryEntityList(Customer.class, sql, keyword);
        return customerList;
    }

    @Transaction
    public List<Customer> getCustomerList() {
        String sql = "select * from customer a";
        List<Customer> customerList = DatabaseHelper.queryEntityList(Customer.class, sql);
        return customerList;
    }

    public Customer getCustomer(Long id) {
        String sql = "select * from customer a where a.id = ?";
        return DatabaseHelper.queryEntity(Customer.class, sql, id);
    }

    @Transaction
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }

    @Transaction
    public boolean updateCustomer(Long id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    @Transaction
    public boolean deleteCustomer(Long id) {
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }
}
