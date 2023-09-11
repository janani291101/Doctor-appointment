package com.customer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.exception.CustomerNotFoundException;
import com.customer.dao.CustomerDao;
import com.customerregistration.customer;
import com.database.sql;

public class customerDaoImpl implements CustomerDao {
	
	private final static String SELECT_ALL = "SELECT * FROM customer";
	private final static String SELECT_BY_ID = "SELECT * FROM customer WHERE id=?";
	private final static String INSERT = "insert into customer(id,firstName,lastName,bloodgroup,email,mobileNumber,username,password) values(?,?,?,?,?,?,?,?)";
	private Connection connection = sql.getConnection();

	@Override
	public List<customer> displayAllCustomer() throws SQLException {
		List<customer> customers = new ArrayList<>();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_ALL);
		while (rs.next()) {
			customer customer = new customer();
			customer.setId(rs.getInt("id"));
			customer.setFirstName(rs.getString("firstName"));
			customer.setLastName(rs.getString("lastName"));
			customer.setbloodgroup(rs.getString("bloodgroup"));
			customer.setemail(rs.getString("email"));
			customer.setmobileNumber(rs.getString("mobileNumber"));
			customer.setusername(rs.getString("username"));
			customer.setpassword(rs.getString("password"));
			customers.add(customer);
		}
		rs.close();
		stmt.close();
		return customers;
	}

	@Override
	public customer findById(int CustomerId) throws CustomerNotFoundException, SQLException {
		customer customer = null;
		PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);
		stmt.setInt(1, CustomerId);
		ResultSet rs = stmt.executeQuery();
		System.out.println("Rs  : "  + rs);
		if(rs.next()) {
			customer = new customer();
			customer.setId(rs.getInt("id"));
			customer.setFirstName(rs.getString("firstName"));
			customer.setLastName(rs.getString("lastName"));
			customer.setbloodgroup(rs.getString("bloodgroup"));
			customer.setemail(rs.getString("email"));
			customer.setmobileNumber(rs.getString("mobileNumber"));
			customer.setusername(rs.getString("username"));
			customer.setpassword(rs.getString("password"));
		}else {
			throw new CustomerNotFoundException("Customer Not Found With Id: " + CustomerId);
		}
		rs.close();
		stmt.close();
		return customer;
	}

	@Override
	public boolean insert(customer customer) throws SQLException {
		boolean result=false;
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1,customer.getId() );
		stmt.setString(2,customer.getFirstName() );
		stmt.setString(3,customer.getLastName() );
		stmt.setString(4,customer.getbloodgroup() );
		stmt.setString(5,customer.getemail() );
		stmt.setString(6,customer.getmobileNumber() );
		stmt.setString(7,customer.getusername() );
		stmt.setString(8,customer.getpassword() );
		if(stmt.executeUpdate()>0) {
			result = true;
		}
		stmt.close();
		return result;
	}

	@Override
	public boolean update(customer customer) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    try {
	        connection = sql.getConnection();
	        String sql = "UPDATE customer SET firstName=?, lastName=?, email=?, mobileNumber=? WHERE id=?";
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, customer.getFirstName());
	        preparedStatement.setString(2, customer.getLastName());
	        preparedStatement.setString(3, customer.getemail());
	        preparedStatement.setString(4, customer.getmobileNumber());
	        preparedStatement.setInt(5, customer.getId());
	        
	        int rowsUpdated = preparedStatement.executeUpdate();
	        return rowsUpdated > 0;
	    } finally {
	        if (preparedStatement != null) {
	            preparedStatement.close();
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	}

	@Override
	public boolean deleteById(int customerId) throws SQLException {
	    try (Connection connection = sql.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE id = ?")) {

	        preparedStatement.setInt(1, customerId);

	        int rowsDeleted = preparedStatement.executeUpdate();
	        return rowsDeleted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public customer getCustomerById(int customerId) {
		return null;
	}

}
