package com.doctor.doctorapplication;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.customerregistration.customer;
import com.customer.dao.CustomerDao;
import com.customer.dao.impl.customerDaoImpl;
import com.exception.CustomerNotFoundException;

import com.doctorappoint.appointment;
import com.appointment.dao.appointmentDao;
import com.appointment.daoimpl.appointmentDaoImpl;
import com.appointmentexception.appointmentNotFoundException;

public class App 
{
	private static CustomerDao dao = new customerDaoImpl();
	private static appointmentDao dao1 = new appointmentDaoImpl();
	private static Scanner scanner = new Scanner(System.in);
	
	
	
    public static void main( String[] args )
    {
    	while (true) {
			System.out.println("Select your Option");
			System.out.println("1. Customer");
			System.out.println("2. Doctor");
			System.out.println("3. Appointment");
			System.out.println("0. Exit");
			int ch = Integer.parseInt(scanner.nextLine());
			switch (ch) {
			case 1:
				boolean exitCustomerMenu = false;
				while(!exitCustomerMenu) {
					System.out.println("Customer's menu");
					System.out.println("1. Customer Registration");
					System.out.println("2. Modify Customer Details");
					System.out.println("3. Delete Customer Details");
					System.out.println("4. View Single Record");
					System.out.println("5. View all Record");
					System.out.println("0. Exit");
					int customerChoice = Integer.parseInt(scanner.nextLine());
					
				switch(customerChoice) {
				case 1:
					System.out.println("Customer Registration Started");
					createcustomer();
					System.out.println("Registration Successfull");
					break;
				case 2:
					System.out.println("Modify Customer Details");
					modifycustomer();
					System.out.println("Customer Details Modified Succesfully");
					break;
				case 3:
					System.out.println("Delete Customer Details");
					deletecustomer();
					System.out.println("Successfully Deleted");
					break;
				case 4:
					System.out.println("View Single Record");
					findById();
					break;
				case 5:
					System.out.println("View all Record");
					displayAllCustomer();
					break;
				case 0:
					exitCustomerMenu = true;
					break;
				default:
					System.exit(0);
					break;
				}
				
		     }
				break;
			
			case 2:
				boolean exitAppointmentMenu = false;
				while(!exitAppointmentMenu) {
					System.out.println("appointment details");
					System.out.println("0. Booking an appointment");
					System.out.println("1. Modify appoinment details");
					System.out.println("2. Delete an appointment");
					System.out.println("3. View Single record");
					System.out.println("4. View all Records");
					System.out.println("5. exit");
					int choice = Integer.parseInt(scanner.nextLine());
					System.out.print("Enter your choice: ");
		            switch (choice) {
		                case 0:
		                    System.out.println("Booking an appointment selected.");
		                    bookingappoint();
		                    System.out.println("Booking Confirmed");
		                    break;
		                case 1:
		                    System.out.println("Modify appointment details selected.");
		                    modifyappoint();
		                    break;
		                case 2:
		                    System.out.println("Delete an appointment selected.");
		                    deleteappoint();
		                    break;
		                case 3:
		                    System.out.println("View Single record selected.");
		                    findById1();
		                    break;
		                case 4:
		                    System.out.println("View all Records selected.");
		                    displayAllappointment();
		                    break;
		                case 5:
		                	exitAppointmentMenu = true;
		                    break;
		                default:
		                    System.out.println("Invalid choice. Please try again.");
		                    break;
		            }
		            
		        }
				break;
			case 3:
			    bookingstatus();
			    break;
			    
			case 0:
				while(true) {
					System.out.println("Exit");
					break;
				}
}
    	}
    }
		 
    	
    private static void createcustomer() {
    	System.out.println("Enter ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();
    	System.out.println("Enter First Name: ");
		String firstName = scanner.nextLine();
		if (firstName.isEmpty()) {
	        throw new IllegalArgumentException("First Name cannot be empty.");
	    }
		System.out.println("Enter Last Name: ");
		String lastName = scanner.nextLine();
		System.out.println("Enter Blood Group: ");
		String bloodgroup = scanner.nextLine();
		if (bloodgroup.isEmpty()) {
	        throw new IllegalArgumentException("Blood Group cannot be empty.");
	    }
		String email;
		    do {
		        System.out.println("Enter Email (must contain '@' and end with '.com'): ");
		        email = scanner.nextLine();
		        if (!email.contains("@") || !email.endsWith(".com")) {
		            System.out.println("Invalid email format. Email must contain '@' and end with '.com'.");
		        }
		    } while (!email.contains("@") || !email.endsWith(".com"));
		 String mobileNumber;
		    do {
		        System.out.println("Enter Mobile Number (at least 10 digits): ");
		        mobileNumber = scanner.nextLine();
		        if (mobileNumber.length() < 10) {
		            System.out.println("Invalid mobile number. Please enter at least 10 digits.");
		        }
		    } while (mobileNumber.length() < 10);
		System.out.println("Create username: ");
		String username = scanner.nextLine();
		if (username.isEmpty()) {
	        throw new IllegalArgumentException("Username cannot be empty.");
	    }
		String password;
		Matcher specialCharacterMatcher;
		Matcher uppercaseLetterMatcher;
		Pattern specialCharacterPattern = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");
		Pattern uppercaseLetterPattern = Pattern.compile("[A-Z]");
		boolean registrationSuccessful = false;

		do {
		    System.out.println("Create Password (must contain at least one uppercase letter and one special character): ");
		    password = scanner.nextLine();
		    specialCharacterMatcher = specialCharacterPattern.matcher(password);
		    uppercaseLetterMatcher = uppercaseLetterPattern.matcher(password);

		    if (!specialCharacterMatcher.find() || !uppercaseLetterMatcher.find()) {
		        System.out.println("Invalid password. Password must contain at least one uppercase letter and one special character.");
		    } else {
		        registrationSuccessful = true; // Password with an uppercase letter and a special character is provided, registration is successful
		    }
		} while (!registrationSuccessful);

		customer customer = new customer(id , firstName, lastName, bloodgroup, email, mobileNumber, username, password);

		try {
		    if(dao.insert(customer)) {
		        System.out.println("Registration Successful");
		    }
		} catch (SQLException e) {
		    System.err.println(e);
		}
    }

    
    private static void modifycustomer() {
        System.out.print("Enter customer ID to modify: ");
        int customerId = Integer.parseInt(scanner.nextLine());
        try {
            customer existingCustomer = dao.findById(customerId);
            if (existingCustomer != null) {
                System.out.println("Current Customer Details:\n" + existingCustomer);

                System.out.println("Select the parameter(s) to modify:");
                System.out.println("1. First Name");
                System.out.println("2. Last Name");
                System.out.println("3. Email");
                System.out.println("4. Mobile Number");
                System.out.println("0. Exit");

                int modifyChoice = Integer.parseInt(scanner.nextLine());

                switch (modifyChoice) {
                    case 1:
                        System.out.print("Enter new First Name: ");
                        String newFirstName = scanner.nextLine();
                        existingCustomer.setFirstName(newFirstName);
                        break;
                    case 2:
                        System.out.print("Enter new Last Name: ");
                        String newLastName = scanner.nextLine();
                        existingCustomer.setLastName(newLastName);
                        break;
                    case 3:
                        System.out.print("Enter new Email: ");
                        String newEmail = scanner.nextLine();
                        existingCustomer.setemail(newEmail);
                        break;
                    case 4:
                        System.out.print("Enter new Mobile Number: ");
                        String newMobileNumber = scanner.nextLine();
                        existingCustomer.setmobileNumber(newMobileNumber);
                        break;
                    case 0:
                        System.out.println("Exiting modification.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
               
                if (dao.update(existingCustomer)) {
                    System.out.println("Customer details updated successfully.");
                } else {
                    System.out.println("Failed to update customer details.");
                }
            } else {
                System.out.println("Customer not found.");
            }
        } catch (NumberFormatException | SQLException e) {
            System.err.println(e);
        } catch (CustomerNotFoundException e) {
			e.printStackTrace();
		}
      }
    
    private static void deletecustomer() {
    	System.out.print("Enter appointment ID to delete: ");
    	int customerIdToDelete = Integer.parseInt(scanner.nextLine());

    	try {
    	    boolean deleted = dao.deleteById(customerIdToDelete);
    	    if (deleted) {
    	        System.out.println("customer with ID " + customerIdToDelete + " deleted successfully.");
    	    } else {
    	        System.out.println("customer with ID " + customerIdToDelete + " not found.");
    	    }
    	} catch (SQLException e) {
    	    System.err.println("Error deleting customer: " + e.getMessage());
    	}

}


 
	private static void findById() {
		System.out.println("\nEnter Id:");
		int CustomerId = Integer.parseInt(scanner.nextLine());
		try {
			customer findById = dao.findById(CustomerId);
			System.out.println(findById);
		} catch (CustomerNotFoundException | SQLException e) {
			System.err.println(e);
		}
	}

	private static void displayAllCustomer() {
		try {
			List<customer> displayAllCustomer = dao.displayAllCustomer();
			for (customer customer : displayAllCustomer) {
				System.out.println(customer);
                System.out.println();
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	
	private static void bookingappoint() {
		System.out.println("Enter Customer id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter doctor Name: ");
        String doctorName = scanner.nextLine();
        if (doctorName.isEmpty()) {
            throw new IllegalArgumentException("Doctor Name cannot be empty.");
        }
        String mobileNumber;
	    do {
	        System.out.println("Enter Customer Mobile Number (at least 10 digits): ");
	        mobileNumber = scanner.nextLine();
	        if (mobileNumber.length() < 10) {
	            System.out.println("Invalid mobile number. Please enter at least 10 digits.");
	        }
	    } while (mobileNumber.length() < 10);
        System.out.println("Enter appointment date for consultation: ");
        String appointmentdate = scanner.nextLine();
        if (appointmentdate.isEmpty()) {
            throw new IllegalArgumentException("Appointment Date cannot be empty.");
        }
        System.out.println("Enter appointment time for consultation: ");
		String appointmenttime = scanner.nextLine();
		if (appointmenttime.isEmpty()) {
	        throw new IllegalArgumentException("Appointment Time cannot be empty.");
	    }
		String appointmentstatus = "Booked";
		appointment appointment = new appointment(id, doctorName, mobileNumber, appointmentdate, appointmenttime, appointmentstatus);
		try {
			if(dao1.insert(appointment)) {
				System.out.println("Inserted");
				System.out.println("Appointment booked and status set to 'Booked'");
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	
	private static void modifyappoint() {
        System.out.print("Enter appointment ID to modify: ");
        int appointmentId = Integer.parseInt(scanner.nextLine());
        try {
            appointment existingAppointment = dao1.findById(appointmentId);
        
            if (existingAppointment != null) {
                System.out.println("Current Appointment Details:\n" + existingAppointment);

                System.out.println("Select the parameter(s) to modify:");
                System.out.println("1. doctorName");
                System.out.println("2. appointmentdate");
                System.out.println("3. appointmenttime");

                int modifyChoice = Integer.parseInt(scanner.nextLine());

                switch (modifyChoice) {
                    case 1:
                        System.out.print("Enter new doctorName: ");
                        String newdoctorName = scanner.nextLine();
                        existingAppointment.setdoctorName(newdoctorName);
                        break;
                    case 2:
                        System.out.print("Enter new appointmentdate: ");
                        String newappointmentdate = scanner.nextLine();
                        existingAppointment.setappointmentdate(newappointmentdate);
                        break;
                    case 3:
                        System.out.print("Enter new appointmenttime: ");
                        String newappointmenttime = scanner.nextLine();
                        existingAppointment.setappointmenttime(newappointmenttime);
                        break;
                    case 0:
                        System.out.println("Exiting modification.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
               
                if (dao1.update(existingAppointment)) {
                    System.out.println("Appointment details updated successfully.");
                } else {
                    System.out.println("Failed to update Appointment details.");
                }
            } else {
                System.out.println("Appointment details not found.");
            }
        } catch (NumberFormatException | SQLException e) {
            System.err.println(e);
        } catch (appointmentNotFoundException e) {
			e.printStackTrace();
		}
      }
	
	 private static void deleteappoint() {
	    	System.out.print("Enter appointment ID to delete: ");
	    	int appointmentIdToDelete = Integer.parseInt(scanner.nextLine());

	    	try {
	    	    boolean deleted = dao1.deleteById(appointmentIdToDelete);
	    	    if (deleted) {
	    	        System.out.println("Appointment with ID " + appointmentIdToDelete + " deleted successfully.");
	    	    } else {
	    	        System.out.println("Appointment with ID " + appointmentIdToDelete + " not found.");
	    	    }
	    	} catch (SQLException e) {
	    	    System.err.println("Error deleting appointment: " + e.getMessage());
	    	}

	}
	 
	private static void findById1() {
		System.out.println("Enter Id:");
		int appointmentId = Integer.parseInt(scanner.nextLine());
		try {
			appointment findById = dao1.findById(appointmentId);
			System.out.println(findById);
		} catch (appointmentNotFoundException | SQLException e) {
			System.err.println(e);
		}
	}

	private static void displayAllappointment() {
		try {
			List<appointment> displayAllappointment = dao1.displayAllappointment();
			for (appointment appointment : displayAllappointment) {
				if ("Booked".equals(appointment.getappointmentstatus())) {
				System.out.println(appointment);
				 System.out.println("Appointment ID: " + appointment.getId());
	                System.out.println("Doctor Name: " + appointment.getdoctorName());
	                System.out.println("Mobile Number: " + appointment.getmobileNumber());
	                System.out.println("Appointment Date: " + appointment.getappointmentdate());
	                System.out.println("Appointment Time: " + appointment.getappointmenttime());
	                System.out.println("Status: " + appointment.getappointmentstatus());

	                System.out.println(); 
	            }
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
	private static void bookingstatus() {
		System.out.println("Enter Appointment ID: ");
	    int appointmentIdToCheck = Integer.parseInt(scanner.nextLine());
	    
	    try {
	        appointment appointmentToCheck = dao1.findById(appointmentIdToCheck);
	        
	        if (appointmentToCheck != null) {
	            String appointmentStatus = appointmentToCheck.getappointmentstatus();
	            
	            System.out.println("Appointment Status for ID " + appointmentIdToCheck + ": " + appointmentStatus);
	            
	            if ("Booked".equals(appointmentStatus)) {
	                System.out.println("Booking is Confirmed");
	            } else {
	                System.out.println("Booking not Confirmed");
	            }
	        } else {
	            System.out.println("Appointment with ID " + appointmentIdToCheck + " not found.");
	        }
	    } catch (SQLException | appointmentNotFoundException e) {
	        System.err.println(e);
	    }
	}
	

    }

 

