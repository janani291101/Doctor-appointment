package com.customerregistration;

public class customer {
		
	    private int id;
		private String firstName;
		private String lastName;
		private String bloodgroup;
		private String email;
		private String mobileNumber;
		private String username;
		private String password;
		
		
		
		
		public customer(int id, String firstName, String lastName, String bloodgroup, String email, String mobileNumber, String username, String password) {		
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.bloodgroup = bloodgroup;
			this.email = email;
			this.mobileNumber = mobileNumber;
			this.username = username;
			this.password = password;
		}
			
		public customer() {
			// TODO Auto-generated constructor stub
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getbloodgroup() {
			return bloodgroup;
		}
		public void setbloodgroup(String bloodgroup) {
			this.bloodgroup = bloodgroup;
		}
		public String getemail() {
			return email;
		}
		public void setemail(String email) {
			this.email = email;
		}
		public String getmobileNumber() {
			return mobileNumber;
		}
		public void setmobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		public String getusername() {
			return username;
		}
		public void setusername(String username) {
			this.username = username;
		}
		public String getpassword() {
			return password;
		}
		public void setpassword(String password) {
			this.password = password;
		}
		@Override
		public String toString() {
			return "Customer{" +
	                "id=" + id +
	                ", firstName='" + firstName + '\'' +
	                ", lastName='" + lastName + '\'' +
	                ", bloodgroup='" + bloodgroup + '\'' +
	                ", email='" + email + '\'' +
	                ", mobileNumber='" + mobileNumber + '\'' +
	                ", username='" + username + '\'' +
	                ", password=" + password +
	                '}';
		}

		
	}
