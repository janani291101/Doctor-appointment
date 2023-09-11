package com.appointment.dao;

import java.sql.SQLException;
import java.util.List;

import com.appointmentexception.appointmentNotFoundException;
import com.customerregistration.customer;
import com.doctorappoint.appointment;


public interface appointmentDao {
List<appointment> displayAllappointment() throws SQLException;
	
	appointment findById(int appointmentId) throws appointmentNotFoundException, SQLException; 
	
	boolean insert(appointment appointment) throws SQLException;
	boolean deleteById(int appointmentId) throws SQLException;
	boolean update(appointment appointment) throws SQLException;
}
