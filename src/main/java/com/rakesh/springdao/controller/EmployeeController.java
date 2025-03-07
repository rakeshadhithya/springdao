package com.rakesh.springdao.controller;

import java.sql.SQLException;
import java.util.List;

import com.rakesh.springdao.entity.Employee;
import com.rakesh.springdao.service.EmployeeService;

public class EmployeeController {
    
    EmployeeService employeeService; 

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    
    public void get(int id) throws SQLException {
        Employee employee = employeeService.get(id);
        if(employee != null) {
            System.out.printf("%-15s %-15s %-15s %-15s %n ", "Employee ID", "Name", "Department", "Salary");
            System.out.printf("%-15s %-15s %-15s %-15s", employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary());
        }
        else{
            System.out.println("Getting employee by id failed, try agian! ");
        }
    }

    public void getAll() throws SQLException {

        List<Employee> employeeList = employeeService.getAll();
        if(employeeList != null) {
            System.out.printf("%-15s %-15s %-15s %-15s %n", "Employee ID", "Name", "Department", "Salary");
            for(int i=0; i<employeeList.size(); i++) {
                Employee employee = employeeList.get(i);
                System.out.printf("%-15s %-15s %-15s %-15s %n", employee.getId(), employee.getName(), employee.getDepartment(), employee.getSalary());
            }
        }
        else {
            System.out.println("Getting employees record failed, try again!");
        }
    }


    public void save(Employee employee) throws SQLException {
        Employee employeeSaved = employeeService.save(employee);
        if(employee != null) {
            System.out.println("Employee added successfully!");
            System.out.printf("%-15s %-15s %-15s %-15s %n ", "Employee ID", "Name", "Department", "Salary");
            System.out.printf("%-15s %-15s %-15s %-15s", employeeSaved.getId(), employeeSaved.getName(), employeeSaved.getDepartment(), employeeSaved.getSalary());
        }
        else{
            System.out.println("Employee saving failed, try agian!");
        }
    }


    public void update(int id, Employee employee) throws SQLException {
        Employee employeeUpdated = employeeService.update(id, employee);
        if(employee != null) {
            System.out.println("employee updated successfully!");
            System.out.printf("%-15s %-15s %-15s %-15s %n ", "Employee ID", "Name", "Department", "Salary");
            System.out.printf("%-15s %-15s %-15s %-15s", employeeUpdated.getId(), employeeUpdated.getName(), employeeUpdated.getDepartment(), employeeUpdated.getSalary());
        }
        else{
            System.out.println("updation operation failed, try again!");
        }
    }

    public void delete(int id) throws SQLException {
        int rowsEffected = employeeService.delete(id);
        if (rowsEffected>0)
            System.out.println("Employee deleted successfully");
        else
            System.out.println("Deltion operation failed, try again!");
    }
}
