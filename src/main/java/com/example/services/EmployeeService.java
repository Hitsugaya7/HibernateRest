package com.example.services;

import com.example.dao.EmployeeDao;
import com.example.dao.ProjectDao;
import com.example.model.Employee;
import com.example.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;


    public Employee getEmployeeById(int id) {
        Employee providerById = employeeDao.getEmployeeById(id);
        return providerById;
    }


    public List<Employee> allEmployees() {
        List<Employee> allProjects = employeeDao.getAllEmployees();
        return allProjects;
    }

    public List<Employee> addEmployees(String name) {
        employeeDao.addEmployee(new Employee(name));
        List<Employee> allEmployees = employeeDao.getAllEmployees();
        return allEmployees;
    }

    public List<Employee> delete(String id) {
        List<Employee> delete = employeeDao.deleteEmployee(id);
        return delete;
    }
}
