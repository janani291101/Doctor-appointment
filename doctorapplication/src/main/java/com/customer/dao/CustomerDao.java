package com.customer.dao;

import java.sql.SQLException;
import java.util.List;

import com.exception.CustomerNotFoundException;
import com.customerregistration.customer;
import com.doctorappoint.appointment;

public interface CustomerDao { 
List<customer> displayAllCustomer() throws SQLException;
customer getCustomerById(int customerId);
	
	customer findById(int CustomerId) throws CustomerNotFoundException, SQLException; 
	
	
	boolean deleteById(int customerId) throws SQLException;
	boolean insert(customer customer) throws SQLException;
	boolean update(customer customer) throws SQLException;
}
