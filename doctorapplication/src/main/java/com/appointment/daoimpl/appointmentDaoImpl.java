package com.appointment.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.appointmentexception.appointmentNotFoundException;
import com.customerregistration.customer;
import com.doctorappoint.appointment;
import com.appointment.dao.appointmentDao;
import com.database.sql;

public class appointmentDaoImpl implements appointmentDao {
	private final static String SELECT_ALL = "SELECT * FROM appointment";
	private final static String SELECT_BY_ID = "SELECT * FROM appointment WHERE id=?";
	private final static String INSERT = "insert into appointment(id,doctorName,mobileNumber,appointmentdate,appointmenttime,appointmentstatus) values(?,?,?,?,?,?)";
	private Connection connection = sql.getConnection();

	@Override
	public List<appointment> displayAllappointment() throws SQLException {
		List<appointment> appointments = new ArrayList<>();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_ALL);
		while (rs.next()) {
			appointment appointment = new appointment();
			appointment.setId(rs.getInt("id"));
			appointment.setdoctorName(rs.getString("doctorName"));
			appointment.setmobileNumber(rs.getString("mobileNumber"));
			appointment.setappointmentdate(rs.getString("appointmentdate"));
			appointment.setappointmenttime(rs.getString("appointmenttime"));
			appointment.setappointmentstatus(rs.getString("appointmentstatus"));
			appointments.add(appointment);
		}
		rs.close();
		stmt.close();
		return appointments;
	}

	@Override
	public appointment findById(int appointmentId) throws appointmentNotFoundException, SQLException {
		appointment appointment =null;
		PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID);
		stmt.setInt(1, appointmentId);
		ResultSet rs = stmt.executeQuery();
		System.out.println("Rs  : "  + rs);
		if(rs.next()) {
			appointment = new appointment();
			appointment.setId(rs.getInt("id"));
			appointment.setdoctorName(rs.getString("doctorName"));
			appointment.setmobileNumber(rs.getString("mobileNumber"));
			appointment.setappointmentdate(rs.getString("appointmentdate"));
			appointment.setappointmenttime(rs.getString("appointmenttime"));
			appointment.setappointmentstatus(rs.getString("appointmentstatus"));
		}else {
			throw new appointmentNotFoundException("Appointment Not Found With Id: " + appointmentId);
		}
		rs.close();
		stmt.close();
		return appointment;
	}

	@Override
	public boolean insert(appointment appointment) throws SQLException {
		boolean result=false;
		PreparedStatement stmt = connection.prepareStatement(INSERT);
		stmt.setInt(1,appointment.getId() );
		stmt.setString(2,appointment.getdoctorName() );
		stmt.setString(3,appointment.getmobileNumber() );
		stmt.setString(4,appointment.getappointmentdate() );
		stmt.setString(5,appointment.getappointmenttime() );
		stmt.setString(6,appointment.getappointmentstatus() );
		if(stmt.executeUpdate()>0) {
			result = true;
		}
		stmt.close();
		return result;
	}

	@Override
	public boolean deleteById(int appointmentId) throws SQLException {
		try (Connection connection = sql.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM appointment WHERE id = ?")) {

		        preparedStatement.setInt(1, appointmentId);

		        int rowsDeleted = preparedStatement.executeUpdate();
		        return rowsDeleted > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
	@Override
	public boolean update(appointment appointment) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    try {
	        connection = sql.getConnection();
	        String sql = "UPDATE appointment SET doctorName=?, mobileNumber=?, appointmentdate=?, appointmenttime=? WHERE id=?";
	        preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, appointment.getdoctorName());
	        preparedStatement.setString(2, appointment.getmobileNumber());
	        preparedStatement.setString(3, appointment.getappointmentdate());
	        preparedStatement.setString(4, appointment.getappointmenttime());
	        preparedStatement.setInt(5, appointment.getId());
	        
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


}
		
