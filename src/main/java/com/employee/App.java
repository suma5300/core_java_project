package com.employee;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

import com.employee.entity.Employee;
import com.employee.services.EmployeeServices;
import com.employee.utils.ConnectionFactory;

public class App {
	
    public static void main( String[] args ){
    	Connection connection=ConnectionFactory.getMySqlConnection();
    	EmployeeServices services=new EmployeeServices(connection);
        Scanner scanner=new Scanner(System.in);
        while(true) {
        	System.out.println("Employee Application\n");
        	System.out.println("1-Add Employee Details\n2-->Display List of Employees by the Firstname\n3-->Display List of Employees with Firstname and Phone number\n4-->Update the email and phone number of a particular employee\n5-->Delete details of a particular employee by Firstname\n6-->List of employees with firstname and email address whose birthday falls on the given date\n7-->Get the list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date\n8)-->Exit\n");
        	int choice=0;
        	System.out.println("Enter choice from 1 to 8");
        	choice=Integer.parseInt(scanner.nextLine());
        	switch (choice) {
				case 1:{
					System.out.println("-1)Add Employee Details<--");
					System.out.println("Enter the first name : ");
					String firstName=scanner.nextLine();
					System.out.println("Enter the last name : ");
					String lastName=scanner.nextLine();
					System.out.println("Enter the address : ");
					String address=scanner.nextLine();
					System.out.println("Enter the mail id : ");
					String emailAddress=scanner.nextLine();
					System.out.println("Enter the phone number : ");
					long phoneNumber=Long.parseLong(scanner.nextLine());
					System.out.println("Enter the Date of Birth in (YYYY-MM-DD) Format : ");
					LocalDate dateOfBirth=LocalDate.parse(scanner.nextLine());
					LocalDate weddingDate=null;
					System.out.println("Married/UnMarried - Y/N : ");
					char isMarried=scanner.nextLine().charAt(0);
					switch (isMarried) {
						case 'Y':
						case 'y':
							System.out.println("Enter the Wedding date in (YYYY-MM-DD) Format : ");
							weddingDate = LocalDate.parse(scanner.nextLine());
							break;
						case 'N':
						case 'n':
							break;
						default:
							System.out.println("Invalid choice");
							break;
					}
					services.addEmployee(new Employee(firstName, lastName, address, emailAddress, phoneNumber, dateOfBirth, weddingDate));
					break;
				}	
				case 2:{
					System.out.println("->2)List of employees by their firstName<-");
					System.out.println("Enter the first name : ");
					String firstName=scanner.nextLine();
					services.findByName(firstName);
					break;
				}
				case 3:{
					System.out.println("->3)List of employees with FirstName and Phone Number<-");
					services.findNameAndPhoneNumber();
					break;
				}	
				case 4:{
					System.out.println("->4)Update the email and phoneNumber of a particular employee<-");
					System.out.println("Enter the mail id : ");
					String emailAddress=scanner.nextLine();
					System.out.println("Enter the new mail id : ");
					String newEmailAddress=scanner.nextLine();
					System.out.println("Enter the new phone number : ");
					long phoneNumber=Long.parseLong(scanner.nextLine());
					services.updateEmailAndPhoneNumber(emailAddress,newEmailAddress,phoneNumber);
					break;
				}
				case 5:{
					System.out.println("->5)Delete Details of a Particular employee by firstName<-");
					System.out.println("Enter the first name : ");
					String firstName=scanner.nextLine();
					System.out.println("Enter the email : ");
					String emailAddress=scanner.nextLine();
					services.deleteEmployeeByFirstName(firstName,emailAddress);
					break;
				}
				case 6:{
					System.out.println("->6)list of employees with their firstName and emailAddress  whose Birthday falls on the given date<-");
					System.out.println("Enter the Date in (YYYY-MM-DD) Format : ");
					LocalDate date=LocalDate.parse(scanner.nextLine());
					services.displayNameAndMail(date);
					break;
				}
				case 7:{
					System.out.println("->7)list of employees with their firstName and phone Number whose Wedding Anniversary falls on the given date<-");
					System.out.println("Enter the Date in (DD-MM-YYYY) Format : ");
					LocalDate date=LocalDate.parse(scanner.nextLine());
					services.findNameAndPhoneNumber(date);
					break;
				}
				case 8:{
					System.out.println("Exit");
					scanner.close();
					System.exit(0);
				}
				default:{
					System.err.println("Give a valid choice");
					break;
				}
			}
        	
        }	
        
    }
}
