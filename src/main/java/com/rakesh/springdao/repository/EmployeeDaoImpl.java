package com.rakesh.springdao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.rakesh.springdao.entity.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public EmployeeDaoImpl(){}
	
	
	private int getTotalRecords() throws SQLException {
		int totalRecords = 0;
		String query = "select count(id) from employees";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				totalRecords = resultSet.getInt(1);
			}
		}
		catch(Exception e) {
			System.out.println("Counting total records failed");
			e.printStackTrace();
		}
		finally {
			if(connection!=null) connection.close();
			if(preparedStatement!=null) preparedStatement.close();
			if(resultSet!=null) resultSet.close();
		}
		
		return totalRecords;
	}
	

	@Override
	public Employee get(int id) throws SQLException {

		String query = "select * from employees where id=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Employee foundEmployee = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				foundEmployee = new Employee();
				foundEmployee.setId(resultSet.getInt(1));
				foundEmployee.setName(resultSet.getString(2));
				foundEmployee.setDepartment(resultSet.getString(3));
				foundEmployee.setSalary(resultSet.getString(4));
			}
		}
		catch(Exception e) {
			System.out.println("finding the employeee operation failed");
			e.printStackTrace();
		}
		finally {
			if(connection!=null) connection.close();
			if(preparedStatement!=null) preparedStatement.close();
			if(resultSet!=null) resultSet.close();
		}
		
		return foundEmployee;
	}
	
	@Override
	public List<Employee> getAll() throws SQLException {

		String query = "select * from employees";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Employee> employeesList = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			employeesList = new ArrayList<>();
			while(resultSet.next()) {
				employeesList.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
			}
		}
		catch(Exception e) {
			System.out.println("getting all employees failed");
			e.printStackTrace();
		}
		finally {
			if(connection!=null) connection.close();
			if(preparedStatement!=null) preparedStatement.close();
			if(resultSet!=null) resultSet.close();
		}
		return employeesList;
	}



	@Override
	public Employee save(Employee employee) throws SQLException {

		//its better to take id, else you should get id based on remaining all parameters
		//or you have to generate based on total records 
		String query = "insert into employees values(?, ?,?,?)";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Employee savedEmployee = null;

		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			
			int id = getTotalRecords() + 1;
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, employee.getName());
			preparedStatement.setString(3, employee.getDepartment());
			preparedStatement.setString(4, employee.getSalary());
			
			Integer rowsEffected = preparedStatement.executeUpdate();
			if(rowsEffected>0) {
				savedEmployee = new Employee(id, employee.getName(), employee.getDepartment(), employee.getSalary());
			}
			if(rowsEffected==0) {
				throw new SQLException();
			}
		}
		catch(Exception e) {
			System.out.println("Insertion Failed");
			e.printStackTrace();
		}
		finally {
			if(connection!=null) connection.close();
			if(preparedStatement!=null) preparedStatement.close();
		}
		
		return savedEmployee;
	}


	@Override
	public Employee update(int id, Employee employee) throws SQLException {
		String query = "update employees set name=?, department=?, salary=? where id="+ id +"";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Employee updatedEmployee = null;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,employee.getName());
			preparedStatement.setString(2, employee.getDepartment());
			preparedStatement.setString(3, employee.getSalary());
			
			int rowsEffected = preparedStatement.executeUpdate();
			if(rowsEffected>0) {
				updatedEmployee = new Employee(id, employee.getName(), employee.getDepartment(), employee.getSalary());	
			}
			else {
				throw new SQLException();
			}
		}
		catch(Exception e) {
			System.out.println("update operation failed");
			e.printStackTrace();
		}
		finally {
			if(connection!=null) connection.close();
			if(preparedStatement!=null) preparedStatement.close();
		}
		
		return updatedEmployee;
	}
	

	@Override
	public int delete(int id) throws SQLException {
		String query = "delete from employees where id=?";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int rowsEffected = 0;
		
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			
			rowsEffected = preparedStatement.executeUpdate();
			if(rowsEffected==0) {
				throw new SQLException();
			}
		}
		catch(Exception e) {
			System.out.println("delete operation failed");
			e.printStackTrace();
		}
		finally {
			if(connection!=null) connection.close();
			if(preparedStatement!=null) preparedStatement.close();
		}

		return rowsEffected;
	}



}
