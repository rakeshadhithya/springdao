package com.rakesh.springdao.service;

import java.sql.SQLException;
import java.util.List;

import com.rakesh.springdao.entity.Employee;

public interface EmployeeService {
    public Employee save(Employee employee) throws SQLException;
	public Employee get(int id) throws SQLException;
	public List<Employee> getAll() throws SQLException;
	public Employee update(int id, Employee employee) throws SQLException;
	public int delete(int id) throws SQLException;
}
