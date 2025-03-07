package com.rakesh.springdao.service;

import java.sql.SQLException;
import java.util.List;

import com.rakesh.springdao.entity.Employee;
import com.rakesh.springdao.repository.EmployeeDao;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao){
        this.employeeDao = employeeDao;
    }


    @Override
    public Employee get(int id) throws SQLException {
        return employeeDao.get(id);
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        return employeeDao.getAll();
    }

    @Override
    public Employee save(Employee employee) throws SQLException {
        return employeeDao.save(employee);
    }

    @Override
    public Employee update(int id, Employee employee) throws SQLException {
        return employeeDao.update(id, employee);
    }

    @Override
    public int delete(int id) throws SQLException {
        return employeeDao.delete(id);
    }
    
}
