package com.rakesh.springdao;

import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rakesh.springdao.controller.EmployeeController;
import com.rakesh.springdao.entity.Employee;

public class App {
	
	public static void main(String args[]) throws SQLException {
		
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		
		ApplicationContext factory = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		EmployeeController employeeController = (EmployeeController) factory.getBean("employeeController");


		System.out.println("Welcome to Employees Registry App.");
		
		while(flag) {
			
			System.out.printf("%n %n ");
			System.out.println("Choose the option below");
			System.out.println("1. show employees");
			System.out.println("2. get employee by id");
			System.out.println("3. create new employee");
			System.out.println("4. update an existing employee");
			System.out.println("5. delete an employee");
			System.out.println("6. exit");
			
			int option = sc.nextInt();
			
			switch(option) {
			case 1:{
				employeeController.getAll();
				break;
			}

			case 2:{
				System.out.println("Enter employee Id");
				int id = sc.nextInt();
				employeeController.get(id);
				break;
			}
			
			case 3:{
				System.out.println("Enter Employee name");
				String name = sc.next();
				System.out.println("Enter Employee department");
				String department = sc.next();
				System.out.println("Enter Enployee salary");
				String salary = sc.next();
				
				employeeController.save(new Employee(null, name, department, salary));
				break;
			}
			
			case 4:{
				System.out.println("Enter the id of the employee to be updated");
				int id = sc.nextInt();
				System.out.println("Enter the name to update");
				String name = sc.next();
				System.out.println("Enter the department to update");
				String department = sc.next();
				System.out.println("Enter the salary to update");
				String salary = sc.next();
				
				employeeController.update(id, new Employee(id, name, department, salary));
				break;
			}

			case 5:{
				System.out.println("Enter the id of the employee to be deleted");
				int id = sc.nextInt();
				employeeController.delete(id);
				break;
			}

			case 6:{
				System.out.println("Bye!");
				flag = false;
				sc.close();
			}
			}
			
		}
	}
}
