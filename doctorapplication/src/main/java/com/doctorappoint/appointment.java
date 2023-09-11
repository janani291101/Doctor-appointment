package com.doctorappoint;
import java.sql.Date;
import java.sql.Time;

public class appointment {
	private int id;
	private String doctorName;
	private String mobileNumber;
    private String appointmentdate;
    private String appointmenttime;
    private String appointmentstatus;
    
    public appointment( int id, String doctorName, String mobileNumber, String appointmentdate, String appointmenttime, String appointmentstatus) {		
		this.id = id;
		this.doctorName = doctorName;
		this.mobileNumber = mobileNumber;
		this.appointmentdate = appointmentdate;
		this.appointmenttime = appointmenttime;
		this.appointmentstatus = appointmentstatus;
	}
	
	public appointment() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getdoctorName() {
		return doctorName;
	}
	public void setdoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getmobileNumber() {
		return mobileNumber;
	}
	public void setmobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getappointmentdate() {
		return appointmentdate;
	}
	public void setappointmentdate(String appointmentdate) {
		this.appointmentdate = appointmentdate;
	}
	public String getappointmenttime() {
		return appointmenttime;
	}
	public void setappointmenttime(String appointmenttime) {
		this.appointmenttime = appointmenttime;
	}
	public String getappointmentstatus() {
		return appointmentstatus;
	}
	public void setappointmentstatus(String appointmentstatus) {
		this.appointmentstatus = appointmentstatus;
	}
	@Override
	public String toString() {
		return "appointment [id=" + id + ", doctorName=" + doctorName + ", mobileNumber=" + mobileNumber + ", appointmentdate=" + appointmentdate + ", appointmenttime=" + appointmenttime + ", appointmentstatus=" + appointmentstatus 
				+ "]";
	}
	
}



